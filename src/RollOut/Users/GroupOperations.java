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
 * Групповые операции
 */

public class GroupOperations extends RollOutWeb {

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        //Открытие
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
        createUsers(20);
    }

    @Test
    public void groupOper() throws InterruptedException {
        //Массовое удаление
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='User1']")));
        driver.findElement(By.cssSelector("tbody tr:first-child .checkbox")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Удалить пользователей']")));
        driver.findElement(By.cssSelector("a.toolbar_button:nth-child(1)")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.actions_button")));
        driver.findElement(By.cssSelector("button.actions_button")).click();
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        //Выгрузка браузера
        driver.quit();
        driver = null;
    }
}
