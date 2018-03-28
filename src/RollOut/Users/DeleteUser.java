package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий удаление пользователя.
 * TfsTestCase xxx-xxx
 */

public class DeleteUser extends RollOutWeb {
    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        authSilso(URL_NSMS_SITE_TEST);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS_TEST);
    }

    @Test
    public void deleteUsersAndCheckVisible() throws InterruptedException {
        int number = 2;
        createUsers(number);

        for (int i = number - 1; i >= 0; i--) {
            driver.findElement(By.xpath("//td[text()='User" + i + "']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BUTTON_DELETE_USER)));
            driver.findElement(By.cssSelector(BUTTON_DELETE_USER)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BUTTON_DELETE_YES_USER)));
            driver.findElement(By.cssSelector(BUTTON_DELETE_YES_USER)).click();
            Thread.sleep(1000);
            //Проверяем, что пользователь удален
            Assert.assertTrue(driver.findElements(By.xpath("//td[text()='User" + i + "']")).isEmpty());
        }
    }

    @After
    public void tearDown() {
        //Выгрузка браузера
        driver.quit();
        driver = null;
    }
}
