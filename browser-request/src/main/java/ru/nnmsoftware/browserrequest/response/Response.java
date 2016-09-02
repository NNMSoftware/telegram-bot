package ru.nnmsoftware.browserrequest.response;

import org.apache.http.NameValuePair;
import ru.nnmsoftware.browserrequest.convertors.Converter;

import java.net.HttpCookie;
import java.util.List;

/**
 * Created by stille on 30.08.16.
 * Интерфейс для представления ответов http(s)-сервисов
 */
public interface Response {

    int getStatus();

    String getMessage();

    String getBody();

    List<NameValuePair> getHeaders();

    List<HttpCookie> getCookies();

    <T> T getBodyAs(Converter<T> converter);

}
