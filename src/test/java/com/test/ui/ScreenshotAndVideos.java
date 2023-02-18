package com.test.ui;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class ScreenshotAndVideos {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void takeScreenshotAndVideos() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType
                .LaunchOptions()
                .setChannel("chrome")
                .setHeadless(true));

        //To start video, we have options in Browser context
        BrowserContext browserContext = browser.newContext(new Browser
                .NewContextOptions()
                .setRecordVideoDir(Paths.get("videos")));

        page = browserContext.newPage();

        Video video = page.video();

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

        //To take screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setType(ScreenshotType.PNG)
                .setPath(Paths.get("screenshot/testScreenshot.png")));

        page.close();

        //To save video
        video.saveAs(Paths.get("videos/CreateLead/testVideo.mp4"));
        video.delete();  /* to delete a copy of default created video */
    }
}
