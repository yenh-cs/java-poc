package com.contentsquare;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class TestExcercise1 {
    WebDriver driver;
    String target;

    @Before
    public void setUp() {
        target = "http://main.ctqatest.info/test.php";
        try {
            System.out.println("Starting...");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.setAcceptInsecureCerts(true);
            options.setHeadless(true);
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    public void testTextUnderShadowRoot() {
        driver.get(target);
        WebElement rootElement = driver.findElement(By.cssSelector("#site-info"));
        SearchContext expandRootElement = (SearchContext) ((JavascriptExecutor) driver).executeScript(("return arguments[0].shadowRoot"), rootElement);
        WebElement spanElement = expandRootElement.findElement(By.cssSelector("span"));
        Assert.assertEquals("This is a demo website for testing purpose", spanElement.getText());
    }
}
