package com.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class LoginWithBasicAuth {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void loginUsingBasicAuth() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setHttpCredentials("admin", "admin"));
        page = browserContext.newPage();
        page.navigate("https://the-internet.herokuapp.com/basic_auth");
        Thread.sleep(5000);
        page.close();
    }


}
