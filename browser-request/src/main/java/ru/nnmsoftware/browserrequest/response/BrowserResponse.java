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

    public BrowserResponse(
            int statusCode,
            String message,
            String contentType,
            List<NameValuePair> headers,
            List<HttpCookie> cookies,
            InputStream body) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public List<NameValuePair> getHeaders() {
        return null;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return null;
    }

    @Override
    public <T> T getBodyAs(Converter<T> converter) {
        return null;
    }
}
