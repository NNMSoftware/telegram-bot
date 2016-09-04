package ru.nnmsoftware.browserrequest.sender;

import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import ru.nnmsoftware.browserrequest.request.Method;
import ru.nnmsoftware.browserrequest.request.Request;
import ru.nnmsoftware.browserrequest.response.BrowserResponse;
import ru.nnmsoftware.browserrequest.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by stille on 02.09.16.
 * Класс-отправитель http(s)-запросов, построенный на основе библиотеки java.net
 */
public class JavaNetRequestSender implements RequestSender {

    @Override
    public Response send(Request request) {
        WrappedHttpURLConnection connection = setUpHttpURLConnection(request);
        connection.setMethod(request.getMethod());
        connection.setDoInput(true);
        connection.setHeaders(request.getHeaders());
        connection.setCookies(request.getCookies());
        connection.setBody(request);
        try {
            connection.getConn().connect();
        } catch (IOException e) {
            throw new RuntimeException("Не удаётся открыть соединение с " + request.asLink(), e);
        } finally {
            connection.getConn().disconnect();
        }
        return new BrowserResponse(
                connection.getResponseCode(),
                connection.getMessage(),
                connection.getContentType(),
                connection.getHeaders(),
                connection.getCookies(),
                connection.getBody()
        );
    }

    private WrappedHttpURLConnection setUpHttpURLConnection(Request request) {
        try {
            return new WrappedHttpURLConnection((HttpURLConnection) request.asURI().toURL().openConnection());
        } catch (IOException e) {
            throw new RuntimeException("Не удаётся соединиться с " + request.asURI() + " Возможно, невалидный URL.", e);
        }
    }

    private class WrappedHttpURLConnection {

        private HttpURLConnection conn;

        WrappedHttpURLConnection(HttpURLConnection conn) {
            this.conn = conn;
        }

        void setMethod(Method method) {
            try {
                conn.setRequestMethod(method.toString());
            } catch (ProtocolException e) {
                throw new RuntimeException("Не удаётся установить метод для соединения.", e);
            }
        }

        void setDoInput(boolean doInput) {
            conn.setDoInput(doInput);
        }

        void setHeaders(List<NameValuePair> headers) {
            headers.stream().forEach(header ->
                conn.setRequestProperty(header.getName(), header.getValue())
            );
        }

        void setCookies(List<HttpCookie> cookies) {
            conn.setRequestProperty("Cookie",
                    cookies.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(";")));
        }

        void setBody(Request request) {
            if (Method.POST.equals(request.getMethod()) || Method.PUT.equals(request.getMethod())) {
                conn.setDoOutput(true);
                try {
                    IOUtils.write(request.getBody(), conn.getOutputStream());
                } catch (IOException e) {
                    throw new RuntimeException("Не удаётся установить тело запроса.", e);
                }
            }
        }

        HttpURLConnection getConn() {
            return conn;
        }

        int getResponseCode() {
            try {
                return conn.getResponseCode();
            } catch (IOException e) {
                throw new RuntimeException("Не удаётся получить код ответа", e);
            }
        }

        String getMessage() {
            try {
                return conn.getResponseMessage();
            } catch (IOException e) {
                throw new RuntimeException("Не удаётся получить сообщение ответа", e);
            }
        }

        String getContentType() {
            return conn.getContentType();
        }

        List<NameValuePair> getHeaders() {
            List<NameValuePair> headers = new LinkedList<>();
            conn.getHeaderFields().entrySet().stream()
                    .filter(entry -> entry != null && entry.getKey() != null)
                    .forEach(entry -> headers.addAll(entry.getValue().stream()
                                .map(values -> new BasicNameValuePair(entry.getKey(), values))
                                .collect(Collectors.toList()))
                    );
            return headers;
        }

        List<HttpCookie> getCookies() {
            List<HttpCookie> cookies = new LinkedList<>();
            List<String> cookiesHeader = conn.getHeaderFields().get("Set-Cookie");
            if (cookiesHeader != null) {
                cookiesHeader.stream()
                        .forEach(cookie -> cookies.addAll(HttpCookie.parse(cookie)));
            }
            return cookies;
        }

        InputStream getBody() {
            InputStream is;
            getResponseCode();
            is = conn.getErrorStream();
            if (is == null) {
                try {
                    return conn.getInputStream();
                } catch (IOException e) {
                    throw new RuntimeException("Не удаётся получить тело запроса.", e);
                }
            }
            return is;
        }

    }

}
