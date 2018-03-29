package RollOut.Users;

import RollOut.RandomStr;
import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 28.03.2018.
 * Автотест, проверяющий позитивные сценарии редактирования свойств пользователя с проверкой, что редактирование применено.
 * TfsTestCase xxx-xxx
 */

public class EditUserPositive extends RollOutWeb {
    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        //authSilso(URL_NSMS_SITE_TEST);
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
    }

    @Test
    public void editUsers() throws InterruptedException {
        int number = 14;
        Thread.sleep(3000);
        createUsers(number);
        editUser("1", null, null, null); // 1 0 0 0
        editUser(null, "1@gmail.com", null, null); // 0 1 0 0
        editUser(null, null, "+721", null); // 0 0 1 0
        editUser(null, null, null, "Q"); // 0 0 0 1

        editUser(RandomStr.getStr(128), RandomStr.getStr(63) + "@gmail.com", null, null); // 1 1 0 0
        editUser(RandomStr.getStr(63), null, "+123456789012345", null); // 1 0 1 0
        editUser("qwe1", null, null, RandomStr.getStr(128)); // 1 0 0 1
        editUser(null, "alice@1", "+87654321", null); // 0 1 1 0
        editUser(null, "Q@" + RandomStr.getStrDomain(252), null, RandomStr.getStr(57)); // 0 1 0 1
        editUser(null, null, "   9876512   ", "  qwe   "); // 0 0 1 1

        editUser("qwe2", RandomStr.getStr(60) + "@" + RandomStr.getStrDomain(193), "123456789012345", null); // 1 1 1 0
        editUser("qwe3", null, "721", "qwe"); // 1 0 1 1
        editUser("a", "alice@d", null, "qwe"); // 1 1 0 1
        editUser(null, "z@1", "+721", "qwe"); // 0 1 1 1
    }

    public void editUser(String name, String email, String phone, String about) throws InterruptedException {
        driver.findElement(By.xpath("//td[text()='User" + (count - 1) + "']")).click();
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        if (name != null) {
            elements.get(0).clear();
            elements.get(0).sendKeys(name);
        }
        if (email != null) {
            elements.get(1).clear();
            elements.get(1).sendKeys(email);
        }
        if (phone != null) {
            elements.get(2).clear();
            elements.get(2).sendKeys(phone);
        }
        if (about != null) {
            driver.findElement(By.cssSelector("textarea")).clear();
            driver.findElement(By.cssSelector("textarea")).sendKeys(about);
        }
        driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).click();
        Thread.sleep(1000);
        if (name != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + name + "']")));
        if (email != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + email + "']")));
        if (phone != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + phone + "']")));
        count--;
    }

    @After
    public void tearDown() {
        deleteAllUsers();
        driver.quit();
        driver = null;
    }
}
