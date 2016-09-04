package ru.nnmsoftware.encounter.api;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by stille on 05.09.16.
 */
public class EncounterApiTest {

    private static URI baseURI;
    private static EncounterApi api;

    @BeforeClass
    public static void setUp() throws URISyntaxException {
        baseURI = new URI("http://demo.en.cx");
        api = new EncounterApi(baseURI);
    }

    @Test
    public void testLogin() throws Exception {
        String token = api.login("stille", "nerhg4ygahg");
    }

    @Test
    public void testLogout() throws Exception {

    }

}