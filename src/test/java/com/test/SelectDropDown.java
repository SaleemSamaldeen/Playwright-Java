package com.test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.Test;

public class SelectDropDown {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void selectDropdownInPage() throws InterruptedException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        BrowserContext browserContext = browser.newContext();
        page = browserContext.newPage();

        page.navigate("http://leaftaps.com/opentaps");
        page.locator("input#username").type("demosalesmanager");
        page.locator("input#password").type("crmsfa");
        page.click("//input[@type='submit']");
        page.click("text=CRM/SFA");
        page.click("text=Leads");
        page.click("text=Create Lead");
        page.type("#createLeadForm_companyName", "Testing");
        page.type("#createLeadForm_firstName", "Playwright");
        page.locator("#createLeadForm_dataSourceId").selectOption("LEAD_DIRECTMAIL");  //give direct drop down value
        page.locator("#createLeadForm_dataSourceId").selectOption(new SelectOption().setValue("LEAD_CONFERENCE")); //Alternate way - give direct drop down value
        page.locator("#createLeadForm_dataSourceId").selectOption(new SelectOption().setIndex(1)); //Alternate way - select using index
        page.close();
    }
}
