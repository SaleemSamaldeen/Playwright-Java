package com.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class Launch {

    @Test
    public void launchBrowser(){
        //Initialize your playwright
        Playwright pw = Playwright.create();

        //launch webkit
        //Browser launch = pw.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //launch edge browser
        //pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false));

        //launch chrome browser
        Browser launch = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = launch.newPage();
        page.viewportSize();
        page.setViewportSize(1920, 1200);
        page.navigate("https://demo.nopcommerce.com/");
        pw.close();

    }
}
