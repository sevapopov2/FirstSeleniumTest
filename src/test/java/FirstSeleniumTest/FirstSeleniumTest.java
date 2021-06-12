package FirstSeleniumTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

@BeforeAll      
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();

    }

    @AfterEach
    public void testTeardown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void homePageURLTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs do not match");
    }



    //@Test
    public void switchLanguageTest(){
        //Find element to switch language.
        WebElement languageElement = driver.findElement(By.xpath("//a[@title='English (UK)']"));
        assertNotNull(languageElement);
        languageElement.click();
    }

    @Test
    public void createAccountTest() throws InterruptedException {
        WebElement createNewAccount = driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']"));
        assertNotNull(createNewAccount);
        createNewAccount.click();
        Thread.sleep(1000);
    }

    @Test
    public void firstNameErrorTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement firstnameError = driver.findElement(By.xpath("//*[contains(text(), 'вас зовут?')]"));
        assertNotNull(firstnameError);
    }

    @Test
    public void lastNameErrorTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Anna");
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement lastNameError = driver.findElement(By.xpath("//*[contains(text(), 'вас зовут?')]"));
        assertNotNull(lastNameError);
    }

    @Test
    public void emailConfirmationErrorTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Pupkina");
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        driver.findElement(By.xpath("//input[@name='reg_email__']")).click();
        WebElement emailError = driver.findElement(By.xpath("//*[contains(text(), 'для входа')]"));
        assertNotNull(emailError);
    }


    @Test
    public void emailErrorTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Pupkina");
        driver.findElement(By.xpath("//input[@name='reg_email__']")).sendKeys("testemail@da.net");
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement emailConfirmationError = driver.findElement(By.xpath("//*[contains(text(), 'Введите повторно')]"));
        assertNotNull(emailConfirmationError);
    }

    @Test
    public void paswordErrorTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Pupkina");
        WebElement email = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        email.sendKeys("testemail@da.net");
        WebElement emailConfirmation = driver.findElement(By.xpath("//*[contains(text(), 'Повторите ваш')]"));
        assertNotNull(emailConfirmation);
        emailConfirmation.sendKeys("testemail@da.net");
        assertEquals(email, emailConfirmation);
        driver.findElement(By.xpath("//button[@name='websubmit']")).click();
        WebElement passwordError = driver.findElement(By.xpath("//*[contains(text(), 'Пароль должен')]"));
        assertNotNull(passwordError);
    }

    @ParameterizedTest
    @ValueSource(strings = {"янв", "июн", "дек"})
    public void monthsTest(String monthInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='month']")).click();
        driver.findElement(By.xpath("//*[text() = '"+ monthInput +"']")).click();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearTest(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='year']")).click();
        driver.findElement(By.xpath("//*[text() = '"+ yearInput +"']")).click();
    }

    @Test
    public void genderRadiobuttonsTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[text() = 'Мужчина']//following-sibling::*[@type='radio']")).click();
        //driver.findElement(By.xpath("//*[text() = 'Мужчина']//preceding-sibling::*[@type='radio']")).click();
    }

    @Test
    public void termsLinkTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='terms-link']")).click();
        for (String newWindow : driver.getWindowHandles()) {
            driver.switchTo().window(newWindow);
        }
        driver.getCurrentUrl();
        assertNotNull(driver.getCurrentUrl());
    }

    @Test
    public void privacyLinkTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text() = 'Создать аккаунт']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@id='privacy-link']")).click();
        for (String newWindow : driver.getWindowHandles()) {
            driver.switchTo().window(newWindow);
        }
        String newWindowURL = driver.getCurrentUrl();
        assertNotNull(newWindowURL);
        
    }

}

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


//Второй тест показался мне сложнее первого. Я не совсем понимаю, по какому принципу работает wait, нужно ли его использовать до нахождения элемента или после и как потом проверить то, что элемент найден? Я попробовал сделать один пункт меню, но мне не совсем понятно, как тестировать страницу на то, что она открылась и на то, что элемент нашёлся. В случае с элементом assert not null, а в случае со страницей? Также непонятно как тестировать элемент на открытие, какой assertion испольовать.