package ru.nnmsoftware.browserrequest.request;

import org.apache.http.NameValuePair;

import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

/**
 * Created by stille on 30.08.16.
 * Интерфейс объектов для хранения и составления http(s)-запросов
 */
public interface Request {

    Method getMethod();

    List<NameValuePair> getHeaders();

    List<HttpCookie> getCookies();

    URI asURI();

    String asLink();

    byte[] getBody();

}
