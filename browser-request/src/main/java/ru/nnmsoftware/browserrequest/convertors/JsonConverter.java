package ru.nnmsoftware.browserrequest.convertors;

import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by stille on 16.04.17.
 */
public class JsonConverter<T> implements Converter<T> {

    private Class<T> tClass;

    public JsonConverter(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public T convert(InputStream is, Charset charset) {
        String body = new StringConverter().convert(is, charset);
        return new Gson().fromJson(body, tClass);
    }
}
