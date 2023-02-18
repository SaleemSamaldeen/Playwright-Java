package com.test.restapi;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAPITest {

    @Test
    public void checkRestAPITest() {
        Playwright playwright = Playwright.create();


        APIRequest request = playwright.request();

        Map<String,String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Basic JRkT4=v$5vvN");
        requestHeaders.put("Content-Type","application/json");


        APIRequestContext apiRequestContext = request.newContext(new APIRequest
                .NewContextOptions()
                .setBaseURL("https://dev96213.service-now.com")
                .setExtraHTTPHeaders(requestHeaders));

        String body = "";

        APIResponse apiResponse = apiRequestContext.post("/api/now/table/incident", RequestOptions.create().setData(body));

        apiResponse.status();

        apiResponse.statusText();

        apiResponse.body();


    }
}
