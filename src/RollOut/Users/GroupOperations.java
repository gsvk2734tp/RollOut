package RollOut.Users;

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
 * Автотест, проверяющий групповые операции.
 * TfsTestCase xxx-xxx
 */

@RunWith(value = Parameterized.class)
public class GroupOperations extends RollOutWeb {
    public GroupOperations(WebDriver driver) {
        super(driver);
    }

    @Before
    public void setUp() throws InterruptedException {
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
        createUsers(20);
    }

    @Test
    public void checkGroupOperations() throws InterruptedException {
        //Массовое удаление
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='User1']")));
        driver.findElement(By.cssSelector(CHECKBOX_SELECTALL_USERS)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Удалить пользователей']")));
        driver.findElement(By.cssSelector("a.toolbar_button:nth-child(1)")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BUTTON_DELETE_YES_USER)));
        driver.findElement(By.cssSelector(BUTTON_DELETE_YES_USER)).click();
        Thread.sleep(1000);
    }

    @After
    public void tearDown() {
        //Выгрузка браузера
        driver.quit();
        driver = null;
    }
}
