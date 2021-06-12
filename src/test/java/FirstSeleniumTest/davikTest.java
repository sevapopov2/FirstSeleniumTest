package FirstSeleniumTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class davikTest {
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get("https://www.daviktapes.com/");
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTeardown() {
        driver.get("https://www.daviktapes.com/");
    }

    @Test
    public void topMenuTest {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement aboutUsLink = driver.findElement(By.xpath("//a[text() = 'About us']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(aboutUsLink));
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutUsLink).build().perform();
    }
}
