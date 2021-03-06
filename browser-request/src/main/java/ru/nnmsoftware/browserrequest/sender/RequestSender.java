package ru.nnmsoftware.browserrequest.sender;

import ru.nnmsoftware.browserrequest.request.Request;
import ru.nnmsoftware.browserrequest.response.Response;

/**
 * Created by stille on 30.08.16.
 * Интерфейс отправителя http(s)-запросов
 */
public interface RequestSender {

    Response send(Request request);

}
