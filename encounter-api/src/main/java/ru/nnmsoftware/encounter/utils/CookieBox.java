package ru.nnmsoftware.encounter.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.net.HttpCookie;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stille on 02.09.16.
 * Хранитель кук для различных сессий. Позволяет работать с набором разных авторизационных сессий
 */
public class CookieBox {

    private static CookieBox instanceKeeper;

    private Map<String, List<HttpCookie>> storage = new HashMap<String, List<HttpCookie>>();

    private CookieBox() {
        // singleton
    }

    public static CookieBox getInstance() {
        if (instanceKeeper == null) {
            instanceKeeper = new CookieBox();
        }
        return instanceKeeper;
    }

    /**
     * Сохранить куку в хранилище
     * @param cookie кука
     * @return токен для получения куки из хранилища
     */
    public String register(List<HttpCookie> cookie) {
        String id = DigestUtils.md5Hex(cookie.toString() + new Date().toString());
        storage.put(id, cookie);
        return id;
    }

    /**
     * Удалить куку из хранилища
     * @param id токен для доступа к куке
     * @return результат удаления: true в случае успеха, false в случае отсутствия куки по токену
     */
    public boolean unregister(String id) {
        if (storage.containsKey(id)) {
            storage.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Получить куку по токену
     * @param id токен для доступа к куке
     * @return кука
     */
    public List<HttpCookie> get(String id) {
        return storage.get(id);
    }

}
