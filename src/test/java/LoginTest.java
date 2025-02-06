import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    /*
    1. Открываем браузер
    2. Вводим в строку поиска https://www.saucedemo.com/
    3. Вводим в поле user - standard_user
    4. Вводим в поле пароль - 123456789
    5. Жмем на кнопку login
    6. Проверяем, что получили ошибку - Epic sadface: Username and password do not match any user in this service
     */

    @Test
    public void negativeTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.id("login-button")).click();
        String error = driver.findElement(By.cssSelector("[data-test=error]")).getText();
        Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service");

        driver.quit();

    }
    @Test
    public void positiveTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        boolean title = driver.findElement(By.cssSelector("[data-test=title]")).isDisplayed();
        Assert.assertTrue(title);
        driver.quit();
    }
}
