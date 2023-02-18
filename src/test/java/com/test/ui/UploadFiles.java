package com.test.ui;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class UploadFiles {


    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void uploadFileCheck() throws InterruptedException {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));

        page = browser.newPage();

        page.navigate("https://leafground.com/file.xhtml;jsessionid=node09qu3tuapqvtu1qlyd8q0583e973155.node0");

        page.locator("//h5[text()='Basic Upload']/following::input[2]").setInputFiles(Paths.get("loginLeafTaps.json"));

        Download download = page.waitForDownload(() -> {
            page.locator("//h5[text()='Basic Download']/following::span[2]").click();
        });

        System.out.println(download.url());

        System.out.println(download.path());

       download.saveAs(Paths.get("C:\\Users\\salee\\Playwright\\downloadedFile"));

       Thread.sleep(5000);
    }
}
