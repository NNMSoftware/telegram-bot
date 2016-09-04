package ru.nnmsoftware.encounter.api;

import com.sun.istack.internal.Nullable;
import org.apache.http.message.BasicNameValuePair;
import ru.nnmsoftware.browserrequest.request.BrowserRequest;
import ru.nnmsoftware.browserrequest.response.Response;
import ru.nnmsoftware.browserrequest.sender.JavaNetRequestSender;
import ru.nnmsoftware.browserrequest.sender.RequestSender;
import ru.nnmsoftware.encounter.beans.LevelInfo;
import ru.nnmsoftware.encounter.utils.CookieBox;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by stille on 02.09.16.
 * Основное API encounter-движка (работа через моибльную версию)
 */
public class EncounterApi {

    private static final String USER_AGENT =
            "Mozilla/5.0 (Linux; U; Android 2.3; en-us) AppleWebKit/999+ (KHTML, like Gecko) Safari/999.9";

    private static RequestSender sender = new JavaNetRequestSender();
    private static CookieBox cookieBox = CookieBox.getInstance();

    private URI baseURI;

    public EncounterApi(String hostname) throws URISyntaxException {
        this.baseURI = new URI(hostname);
    }

    @Nullable
    public String login(String login, String password) {
        BrowserRequest request = basicRequest()
                .withPath("/Login.aspx?return=%2f")
                .withFormUrlEncodedBody(
                        new BasicNameValuePair("login", login),
                        new BasicNameValuePair("password", password)
                );
        Response response = sender.send(request);
        return cookieBox.register(response.getCookies());
    }

    public boolean logout(String token) {
        // TODO: 04.09.16 добавить собственно отправку запроса
        return cookieBox.unregister(token);
    }

    public boolean enterTheGame(String token, long gameId) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public boolean sendCode(String token, String code) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public boolean sendBonusCode(String token, String code) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public LevelInfo getCurrentLevelInfo(String token) {
        // TODO: 02.09.16 implementation
        return new LevelInfo();
    }

    private BrowserRequest basicRequest() {
        return new BrowserRequest(baseURI).addHeader("User-Agent", USER_AGENT);
    }

    private BrowserRequest basicRequestWithCookies(String token) {
        return basicRequest().withCookies(cookieBox.get(token));
    }

}
