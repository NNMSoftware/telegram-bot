package ru.nnmsoftware.telegram.core.types;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stille on 16.04.17.
 */
public class TelegramMultipleResponse {

    private boolean ok;
    private List<Object> result;

    public boolean isOk() {
        return ok;
    }

    public List<Object> getResult() {
        return result;
    }

    public List<String> getResultAsListOfJson() {
        List<String> l = new ArrayList<String>();
        for (Object o : result) {
            l.add(new Gson().toJson(o));
        }
        return l;
    }
}
