package RollOut.auth;


import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий авторизацию
 * TfsTestCase xxx-xxx
 */

//TODO Тест не сделан, будет доработан после интеграции с NSMS

@RunWith(value = Parameterized.class)
public class AuthSislo extends RollOutWeb {
    public AuthSislo(WebDriver driver) {
        super(driver);
    }

    @Before
    public void setUp() {
    }


    @Test
    public void auth() throws InterruptedException {
        driver.get(URL_NSMS_SITE_TEST);
        //Редирект на страницу аутентификации
        wait.until(titleIs(TITLE_SILSO));
        driver.findElement(By.id("UserName")).sendKeys(LOGIN);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(BUTTON_LOGIN)).click();
        wait.until(titleIs(TITLE_APP));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='Ромашка']")));
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
