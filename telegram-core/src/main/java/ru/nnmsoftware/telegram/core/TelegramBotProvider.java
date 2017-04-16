package ru.nnmsoftware.telegram.core;

import ru.nnmsoftware.telegram.core.bot.TelegramBot;

import java.net.URI;

/**
 * Created by stille on 16.04.17.
 */
public class TelegramBotProvider {

    private static URI baseApiUri = URI.create("https://api.telegram.org/");

    public static TelegramBot get(URI baseApiUri, String token) {
        return new TelegramBot(baseApiUri, token);
    }

    public static TelegramBot get(String token) {
        return new TelegramBot(baseApiUri, token);
    }

    public static void setBaseApiUri(URI uri) {
        baseApiUri = uri;
    }

}
