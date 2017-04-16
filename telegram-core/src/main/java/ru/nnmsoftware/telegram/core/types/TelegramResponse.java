package ru.nnmsoftware.telegram.core.types;

import com.google.gson.Gson;

/**
 * Created by stille on 16.04.17.
 */
public class TelegramResponse<T> {

    private boolean ok;
    private Object result;

    public boolean isOk() {
        return ok;
    }

    public Object getResult() {
        return result;
    }

    public String getResultAsJson() {
        return new Gson().toJson(result);
    }
}
