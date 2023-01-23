package com.test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.awt.*;

public class Alerthandling {

    @Test
    public void launchBrowser(){

        Playwright pw = Playwright.create();
        Browser launch = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = launch.newPage();
        page.viewportSize();

        GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = defaultScreenDevice.getDisplayMode().getWidth();
        int height = defaultScreenDevice.getDisplayMode().getHeight();

        page.setViewportSize(width, height);
        page.navigate("https://leafground.com/alert.xhtml");

        page.onDialog(dialog -> {
            dialog.accept();
            String message = dialog.message();
            System.out.println("Alert message: " + message);
        });

        page.locator("(//span[text()='Show'])[2]").click();
        pw.close();

    }
}
