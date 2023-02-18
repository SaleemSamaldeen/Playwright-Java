package com.test.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class Mousehover {

    private Playwright playwright;

    private Browser browser;

    private Page page;



    @Test
    public void tryMouseHover() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        page = browser.newPage();

        page.navigate("https://www.flipkart.com/");

        page.click("button._2KpZ6l._2doB4z");

        page.locator("//div[text()='Electronics']").hover();

        page.locator("//a[text()='Powerbank']").click();


        page.locator("//div[text()='Electronics']").dblclick();


    }

}
