package ru.nnmsoftware.browserrequest.request;

import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 * Created by stille on 30.08.16.
 * Класс для задания, хранения и представления http-запросов с браузерной спецификой
 */
public class BrowserRequest implements Request {

    protected URIBuilder uriBuilder;
    protected byte[] body;
    protected List<HttpCookie> cookies = new LinkedList<>();
    protected List<NameValuePair> headers = new LinkedList<>();
    protected Method method = Method.GET;

    public BrowserRequest(URI uri) {
        this.uriBuilder = new URIBuilder(uri);
    }

    private BrowserRequest(URIBuilder uriBuilder) {
        this.uriBuilder = uriBuilder;
    }

    public BrowserRequest setQueryParameter(String parameterName, String parameterValue) {
        Validate.notNull(parameterName);
        return copyAndModify(request -> request.uriBuilder.setParameter(parameterName, parameterValue));
    }

    public BrowserRequest addQueryParameter(String parameterName, List<String> parameterValues) {
        Validate.notNull(parameterName);
        return copyAndModify(request -> {
           for (String parameterValue: parameterValues) {
               request.uriBuilder.addParameter(parameterName, parameterValue);
           }
        });
    }

    public BrowserRequest addQueryParameters(String parameterName, String parameterValue) {
        return addQueryParameter(parameterName, asList(parameterValue));
    }

    public BrowserRequest addQueryParameters(String parameterName, String... parameterValues) {
        return addQueryParameter(parameterName, asList(parameterValues));
    }

    public BrowserRequest withScheme(String scheme) {
        return copyAndModify(request -> request.uriBuilder.setScheme(scheme));
    }

    public BrowserRequest withUserInfo(String userInfo) {
        return copyAndModify(request -> request.uriBuilder.setUserInfo(userInfo));
    }

    public BrowserRequest withHost(String host) {
        return copyAndModify(request -> request.uriBuilder.setHost(host));
    }

    public BrowserRequest withPort(int port) {
        return copyAndModify(request -> request.uriBuilder.setPort(port));
    }

    public BrowserRequest withPath(String path) {
        return copyAndModify(request -> request.uriBuilder.setPath(path));
    }

    public BrowserRequest withFragment(String fragment) {
        return copyAndModify(request -> request.uriBuilder.setFragment(fragment));
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public List<NameValuePair> getHeaders() {
        return headers;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return cookies;
    }

    @Override
    public URI asURI() {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public String asLink() {
        return uriBuilder.toString();
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    @Override
    public String toString() {
        return asLink();
    }

    @Override
    public BrowserRequest clone() {
        return new BrowserRequest(cloneUriBuilder());
    }

    private BrowserRequest copyAndModify(Consumer<BrowserRequest> modifier) {
        BrowserRequest clone = this.clone();
        modifier.accept(clone);
        return clone;
    }

    private URIBuilder cloneUriBuilder() {
        URIBuilder clone = new URIBuilder();
        clone.setScheme(uriBuilder.getScheme());
        clone.setUserInfo(uriBuilder.getUserInfo());
        clone.setHost(uriBuilder.getHost());
        clone.setPort(uriBuilder.getPort());
        clone.setPath(uriBuilder.getPath());
        clone.setFragment(uriBuilder.getFragment());
        uriBuilder.getQueryParams().stream()
                .forEach(queryParam -> clone.addParameter(queryParam.getName(), queryParam.getValue()));
        return clone;
    }

}
