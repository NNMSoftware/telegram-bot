package ru.nnmsoftware.browserrequest.request;

import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.net.URI;

import static java.util.Arrays.asList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by stille on 05.09.16.
 */
public class BrowserRequestTest {

    @Test
    public void testSetQueryParameter() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.setQueryParameter("param", "value").asLink(),
                equalTo("http://host.domain/path?param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.setQueryParameter("param", "value").asLink(),
                equalTo("http://host.domain/path?a=b&param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.setQueryParameter("a", "c").asLink(),
                equalTo("http://host.domain/path?a=c"));
    }

    @Test
    public void testAddQueryParameters() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.addQueryParameter("param", asList("value")).asLink(),
                equalTo("http://host.domain/path?param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.addQueryParameter("param", asList("value")).asLink(),
                equalTo("http://host.domain/path?a=b&param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.addQueryParameter("a", asList("c")).asLink(),
                equalTo("http://host.domain/path?a=b&a=c"));
    }

    @Test
    public void testAddQueryParameter() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.addQueryParameter("param", "value").asLink(),
                equalTo("http://host.domain/path?param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.addQueryParameter("param", "value").asLink(),
                equalTo("http://host.domain/path?a=b&param=value"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.addQueryParameter("a", "c").asLink(),
                equalTo("http://host.domain/path?a=b&a=c"));
    }

    @Test
    public void testWithScheme() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.withScheme("https").asLink(),
                equalTo("https://host.domain/path"));
    }

    @Test
    public void testWithUserInfo() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.withUserInfo("userinfo").asLink(),
                equalTo("http://userinfo@host.domain/path"));
    }

    @Test
    public void testWithHost() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.withHost("alternative.host").asLink(),
                equalTo("http://alternative.host/path"));
    }

    @Test
    public void testWithPort() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.withPort(1080).asLink(),
                equalTo("http://host.domain:1080/path"));
    }

    @Test
    public void testWithPath() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http", "host.domain", "/path", null));
        assertThat(request.withPath("/another/long/path/without/params").asLink(),
                equalTo("http://host.domain/another/long/path/without/params"));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.withPath("/another/long/path/with/params").asLink(),
                equalTo("http://host.domain/another/long/path/with/params?a=b"));
    }

    @Test
    public void testWithFragment() throws Exception {

    }

    @Test
    public void testWithCookies() throws Exception {

    }

    @Test
    public void testAddCookies() throws Exception {

    }

    @Test
    public void testWithHeaders() throws Exception {

    }

    @Test
    public void testAddHeaders() throws Exception {

    }

    @Test
    public void testAddHeader() throws Exception {

    }

    @Test
    public void testWithMethod() throws Exception {

    }

    @Test
    public void testWithBody() throws Exception {

    }

    @Test
    public void testWithFormUrlEncodedBody() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"))
                .withFormUrlEncodedBody(
                        new BasicNameValuePair("param", "value")
                );
        byte[] body = "param=value".getBytes();
        assertThat(request.getBody(), equalTo(body));
        request = new BrowserRequest(new URI("http://host.domain/path?a=b"))
                .withFormUrlEncodedBody(
                        new BasicNameValuePair("param", "value"),
                        new BasicNameValuePair("strange_param", "а бу")
                );
        body = "param=value&strange_param=%D0%B0+%D0%B1%D1%83".getBytes();
        assertThat(request.getBody(), equalTo(body));
    }

    @Test
    public void testGetMethod() throws Exception {
        Method method = Method.DELETE;
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"))
                .withMethod(method);
        assertThat(request.getMethod(), equalTo(method));
    }

    @Test
    public void testGetHeaders() throws Exception {

    }

    @Test
    public void testGetCookies() throws Exception {

    }

    @Test
    public void testAsURI() throws Exception {
        URI uri = new URI("http://host.domain/path?a=b");
        BrowserRequest request = new BrowserRequest(uri);
        assertThat(request.asURI(), equalTo(uri));
    }

    @Test
    public void testAsLink() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.asLink(), equalTo("http://host.domain/path?a=b"));
    }

    @Test
    public void testGetBody() throws Exception {
        byte[] body = "123".getBytes();
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"))
                .withBody(body);
        assertThat(request.getBody(), equalTo(body));
    }

    @Test
    public void testToString() throws Exception {
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"));
        assertThat(request.toString(), equalTo("http://host.domain/path?a=b"));
    }

    @Test
    public void testClone() throws Exception {
        Method method = Method.DELETE;
        byte[] body = "123".getBytes();
        BrowserRequest request = new BrowserRequest(new URI("http://host.domain/path?a=b"))
                .withBody(body)
                .withMethod(method);
        BrowserRequest clone = request.clone();
        assertThat(clone.asLink(),
                equalTo("http://host.domain/path?a=b"));
        assertThat(clone.getBody(), equalTo(body));
        assertThat(clone.getMethod(), equalTo(method));
    }
}