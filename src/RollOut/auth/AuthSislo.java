package RollOut.auth;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Проверка авторизации
 */


public class AuthSislo {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void auth() throws InterruptedException {
        driver.get("");

        //Редирект на страницу аутентификации
        wait.until(titleIs("Silso"));
        driver.findElement(By.id("UserName")).sendKeys("admin");
        driver.findElement(By.id("Password")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button.btn-login")).click();

        //Ждем редиректа
        wait.until(titleIs("Rollout.WebApplication"));
        //Кастыль из-за необходимости обновлять страницу после входа
        Thread.sleep(2000);
        driver.get("http://rollout-test.nsms.site/organizations");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='Ромашка']")));
    }
}
