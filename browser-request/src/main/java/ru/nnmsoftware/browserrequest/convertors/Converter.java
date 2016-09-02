package ru.nnmsoftware.browserrequest.convertors;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by stille on 30.08.16.
 */
public interface Converter<T> {

    T convert(InputStream is, Charset charset);

}
