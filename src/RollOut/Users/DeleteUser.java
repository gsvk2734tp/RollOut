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
        //Открытие
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
    }

    @Test
    public void deleteUsersAndCheckVisible() throws InterruptedException {
        int number = 2;
        createUsers(number);

        for (int i = number-1; i >= 0; i--) {
            driver.findElement(By.xpath("//td[text()='User" + i + "']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".icon.icon-web-general.icons-general-delete")));
            driver.findElement(By.cssSelector(".icon.icon-web-general.icons-general-delete")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.actions_button")));
            driver.findElement(By.cssSelector("button.actions_button")).click();
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
