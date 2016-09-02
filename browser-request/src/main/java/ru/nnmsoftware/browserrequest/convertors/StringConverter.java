package ru.nnmsoftware.browserrequest.convertors;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by stille on 02.09.16.
 * Преобразователь потока в строковое представление
 */
public class StringConverter implements Converter<String> {

    @Override
    public String convert(InputStream is, Charset charset) {
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        try {
            return new String(IOUtils.toByteArray(is), charset.name());
        } catch (IOException e) {
            throw new RuntimeException("Не удаётся прочитать тело ответа", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
    }
}
