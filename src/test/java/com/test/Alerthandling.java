package com.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.awt.*;

public class Alerthandling {

    private Playwright playwright;

    private Browser browser;

    private Page page;

    @Test
    public void handleAlertByDefault() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.viewportSize();

        GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = defaultScreenDevice.getDisplayMode().getWidth();
        int height = defaultScreenDevice.getDisplayMode().getHeight();

        page.setViewportSize(width, height);
        page.navigate("https://leafground.com/alert.xhtml");

        /*  By default playwright handles alert by clicking cancel in alert box  */
        page.locator("//h5[text()=' Alert (Simple Dialog)']//following::span[2]").click();
        page.locator("//h5[text()=' Alert (Confirm Dialog)']//following::span[2]").click();
        page.locator("//h5[text()=' Alert (Prompt Dialog)']//following::span[2]").click();

        playwright.close();

    }

    @Test
    public void acceptAlertAndVerifyMessage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        page = browser.newPage();
        page.viewportSize();

        GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = defaultScreenDevice.getDisplayMode().getWidth();
        int height = defaultScreenDevice.getDisplayMode().getHeight();

        page.setViewportSize(width, height);
        page.navigate("https://leafground.com/alert.xhtml");

        /* To handle alert globally in specific page - accept, read message and send text */
        /* Below event listener is created in advance to handle upcoming any alerts */
        page.onDialog(dialog -> {
            String defaultValue = dialog.defaultValue(); /* to get default value in alert text box */
            System.out.println("Default text value: " + defaultValue);

            dialog.accept("Playwright automation"); /* to accept alert with message in text box */

            String message = dialog.message(); /* to get alert message */
            System.out.println("Alert message: " + message);

            /*  dialog.accept(); -  To just accept the alert */
        });

        page.locator("//h5[text()=' Alert (Simple Dialog)']//following::span[2]").click();
        page.locator("//h5[text()=' Alert (Confirm Dialog)']//following::span[2]").click();
        
        page.locator("//h5[text()=' Alert (Prompt Dialog)']//following::span[2]").click();

        playwright.close();

    }
}
