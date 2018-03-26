package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Удаление пользователя. Возможно стоит запускать после теста на создания пользователей
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
    public void deleteUsers() throws InterruptedException {
        count = 2;
        createUsers(count);

        for (int i = 0; i < count; i++) {
            driver.findElement(By.xpath("//td[text()='User" + count + "']")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".icon.icon-web-general.icons-general-delete")));
            driver.findElement(By.cssSelector(".icon.icon-web-general.icons-general-delete")).click();
        }
    }

    @After
    public void tearDown() {
        //Выгрузка браузера
        driver.quit();
        driver = null;
    }
}
