package ru.nnmsoftware.browserrequest.response;

import org.apache.http.NameValuePair;
import org.apache.http.entity.ContentType;
import ru.nnmsoftware.browserrequest.convertors.Converter;
import ru.nnmsoftware.browserrequest.convertors.StringConverter;

import java.io.InputStream;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by stille on 02.09.16.
 * Класс, представляющий ответ в "браузерной" специфике — с куками, заголовками, etc.
 */
public class BrowserResponse implements Response {

    private int statusCode;
    private String message;
    private String contentType;
    private List<NameValuePair> headers;
    private List<HttpCookie> cookies;
    private InputStream body;

    public BrowserResponse(
            int statusCode,
            String message,
            String contentType,
            List<NameValuePair> headers,
            List<HttpCookie> cookies,
            InputStream body) {
        this.statusCode = statusCode;
        this.message = message;
        this.contentType = contentType;
        this.headers = headers;
        this.cookies = cookies;
        this.body = body;
    }

    @Override
    public int getStatus() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getBody() {
        return getBodyAs(new StringConverter());
    }

    @Override
    public List<NameValuePair> getHeaders() {
        return headers;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return cookies;
    }

    @Override
    public <T> T getBodyAs(Converter<T> converter) {
        return converter.convert(body, getCharset());
    }

    private Charset getCharset() {
        return ContentType.parse(contentType).getCharset();
    }
}
