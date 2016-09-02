package ru.nnmsoftware.browserrequest.response;

import org.apache.http.NameValuePair;
import ru.nnmsoftware.browserrequest.convertors.Converter;

import java.io.InputStream;
import java.net.HttpCookie;
import java.util.List;

/**
 * Created by stille on 02.09.16.
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
        // // TODO: 02.09.16 implementation 
        return null;
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
        // // TODO: 02.09.16 implementation
        return null;
    }
}
