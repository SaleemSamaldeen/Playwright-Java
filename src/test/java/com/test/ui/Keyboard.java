package com.test.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class Keyboard {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void checkKeyBoardActions() throws InterruptedException {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        page = browser.newPage();

        page.navigate("https://jqueryui.com/autocomplete/");

        page.frameLocator("//iframe[@class='demo-frame']").locator("input#tags").click();

        page.keyboard().press("A");

        page.keyboard().press("ArrowDown");

        page.keyboard().press("ArrowDown");

        page.keyboard().press("Enter");

        /*String innerText = page.frameLocator("//iframe[@class='demo-frame']").locator("input#tags").innerText();

        page.wait(5000);

        page.close();*/

    }
}
