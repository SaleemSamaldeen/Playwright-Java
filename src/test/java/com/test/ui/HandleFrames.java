package com.test.ui;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class HandleFrames {

    @Test
    public void handleFramesAndNestedFrames() {

        Playwright playwright = Playwright.create();

        Browser chrome = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        BrowserContext browserContext = chrome.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("videos")));

        Page page = browserContext.newPage();

        Video video = page.video();

        page.navigate("https://www.mail.com/");

        page.frameLocator("//iframe[@class='permission-core-iframe']") /* parent frame */
                .frameLocator("//iframe[@scrolling='yes']")  /* child frame */
                .locator("//button[@id='onetrust-accept-btn-handler']")
                .click();

        page.locator("//a[@id='login-button']//span").click();

        page.type("//input[@id='login-email']","int_01@mail.com");

        page.type("//input[@id='login-password']","3457576545635345");

        page.click("//button[contains(@class,'login-submit')]");

        page.locator("").hover();

        page.close();

        video.saveAs(Paths.get("videos/HandleFrames/Frames.mp4"));
        video.delete();

    }
}
