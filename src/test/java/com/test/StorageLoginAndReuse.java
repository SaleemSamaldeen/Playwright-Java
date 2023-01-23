package com.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class StorageLoginAndReuse {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void storeLoginAsJSON() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        BrowserContext browserContext = browser.newContext();
        page = browserContext.newPage();

        page.navigate("http://leaftaps.com/opentaps");
        page.locator("input#username").type("demosalesmanager");
        page.locator("input#password").type("crmsfa");
        page.click("//input[@type='submit']");
        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("loginLeafTaps.json")));
        page.close();
    }


    @Test
    public void usingLoginLeafTapsJSON() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("loginLeafTaps.json")));
        page = browserContext.newPage();
        page.navigate("http://leaftaps.com/opentaps");
        page.click("text=CRM/SFA");
        page.click("text=Leads");
        page.click("//a[@href='/crmsfa/control/findLeads']"); //click action in one way
        page.click("//span[text()='Phone']");
        page.locator("id");
        Thread.sleep(5000);
        page.close();
    }
}
