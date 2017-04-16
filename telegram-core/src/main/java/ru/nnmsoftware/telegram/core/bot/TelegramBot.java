package ru.nnmsoftware.telegram.core.bot;

import com.google.gson.Gson;
import ru.nnmsoftware.browserrequest.convertors.JsonConverter;
import ru.nnmsoftware.browserrequest.request.BrowserRequest;
import ru.nnmsoftware.browserrequest.request.Method;
import ru.nnmsoftware.browserrequest.sender.JavaNetRequestSender;
import ru.nnmsoftware.browserrequest.sender.RequestSender;
import ru.nnmsoftware.telegram.core.types.TelegramMultipleResponse;
import ru.nnmsoftware.telegram.core.types.TelegramResponse;
import ru.nnmsoftware.telegram.core.types.User;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stille on 16.04.17.
 */
public class TelegramBot {

    private static RequestSender sender = new JavaNetRequestSender();

    private URI baseApiUri;
    private String token;
    private Updates updates;

    public TelegramBot(URI baseApiUri, String token) {
        this.baseApiUri = baseApiUri;
        this.token = token;
        this.updates = new Updates(this);
    }

    public Updates updates() {
        return updates;
    }

    public User getMe() {
        BrowserRequest request = basicRequest()
                .appendPath("getMe")
                .withMethod(Method.GET);
        return getResponse(request, User.class);
    }

    BrowserRequest basicRequest() {
        return new BrowserRequest(baseApiUri).withPath("/bot" + token);
    }

    String token() {
        return token;
    }

    <T> T getResponse(BrowserRequest request, Class<T> tClass) {
        return new Gson().fromJson(sender
                .send(request)
                .getBodyAs(new JsonConverter<TelegramResponse>(TelegramResponse.class))
                .getResultAsJson(), tClass);
    }

    <T> List<T> getMultipleResponse(BrowserRequest request, Class<T> tClass) {
        List<String> jsons = sender
            .send(request)
            .getBodyAs(new JsonConverter<TelegramMultipleResponse>(TelegramMultipleResponse.class))
            .getResultAsListOfJson();
        List<T> result = new ArrayList<T>();
        for (String json : jsons) {
            result.add(new Gson().fromJson(json, tClass));
        }
        return result;
    }

}
