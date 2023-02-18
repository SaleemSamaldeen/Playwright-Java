package com.test.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.awt.*;

public class ViewPortSize {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void checkSelectedLeadIsDeleted() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        //one way to maximize window during runtime
        GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = defaultScreenDevice.getDisplayMode().getWidth();
        int height = defaultScreenDevice.getDisplayMode().getHeight();

        //another way to maximize window during runtime
   /**  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)size.getWidth();
        int height = (int)size.getHeight();  **/

        page = browser.newPage();
        page.reload();
        page.setViewportSize(width, height);
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("input#username").type("demosalesmanager");
        page.locator("input#password").type("crmsfa");
        page.click("//input[@type='submit']");
        page.click("text=CRM/SFA"); //directly use text even if there are any spaces
        page.click("text=Leads");  //a[text()='Leads'] (xpath)
        page.click("//a[@href='/crmsfa/control/findLeads']"); //click action in one way
        page.click("//span[text()='Phone']");
        page.locator("id");
        page.fill("//input[@name='phoneNumber']", ""); // fill is used to clear text field by passing empty String
        page.type("//input[@name='phoneNumber']", "9964098735");
        page.click("//button[text()='Find Leads']");
        String leadName = page.locator("//span[text()='Lead List']//following::table[contains(@class,'row-table')][1]//tbody//tr[1]//td[1]//a[1]").innerText();
        page.locator("//span[text()='Lead List']//following::table[contains(@class,'row-table')][1]//tbody//tr[1]//td[1]//a[1]").click();
        page.locator("Delete").click(); //click action in another way
        page.locator("Find Leads").click();
        page.locator("id").click();
        page.locator("//button[text()='Find Leads']").click();
        page.close();

    }
}
