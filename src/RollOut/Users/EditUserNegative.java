package RollOut.Users;

import RollOut.RandomStr;
import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 28.03.2018.
 * Автотест, проверяющий негативные сценарии редактирования свойств пользователя.
 * TfsTestCase xxx-xxx
 */

public class EditUserNegative extends RollOutWeb {
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
        Thread.sleep(3000);
        createUsers(2);

        //Проверка имени негативные
        editUserNegative("", "", "", ""); // все поля пустые
        editUserNegative("", null, null, null); // пустое user
        /* Проверка на спецсимволы в имени, запрещены, пока что баг
        for (char sumb : specSumbUserName) {
            editUserNegative(specSumb, null, null, null);
        } */
        editUserNegative(RandomStr.getStr(129), null, null, null); // имя 129
        editUserNegative(RandomStr.getStr(257), null, null, null); // имя 257

        //Проверка email негативные
        editUserNegative(null, "@gmail.com", null, null); // локальная часть 0
        //editUserNegative(null, RandomStr.getStr(64) + "@gmail.com" , null, null); // локальная часть 64, баг
        /* Проверка на спецсимволы в локальной части, запрещены вначале в локальной части, пока что баг
        for (char sumb : specSumb) {
            editUserNegative(null, sumb + "@gmail.com", null, null);
        } */
        editUserNegative(null, "qwe@", null, null); // доменная 0
        editUserNegative(null, "qwe@" + RandomStr.getStr(64), null, null); // доменная более 63 без точки
        editUserNegative(null, "qwe@" + RandomStr.getStrDomain(253), null, null); // более 254 символов
        // Проверка на спецсимволы, запрещены в доменной части
        for (char sumb : specSumb) {
            if (sumb != '-' && sumb != '.') editUserNegative(null, "alice@" + "gmail" + sumb + "com", null, null);
        }

        /**Проверка phone негативные - еще не реализовали валидацию */
       /* createUser("User" + count, "gmail@gmail.com", "+7"); //менее 2х симв
        createUser("User" + count, "gmail@gmail.com", "+79212312312331321423154131"); //более 15 симв
        createUser("User" + count, "gmail@gmail.com", "qqqq"); //буквы
        createUser("User" + count, "gmail@gmail.com", "+ERDFS"); //буквы и + вначале
        createUser("User" + count, "gmail@gmail.com", "+7(123)456-78-90"); //буквы и + вначале
        createUser("User" + count, "gmail@gmail.com", "   +712132123456789 "); //20 симв с пробелами
        */

        /** Проверка Описания негативные - еще не реализовали валидацию  */
        // createUser("User" + count, "gmail@gmail.com", "+71234", RandomStrings.getStr(129)); // 129 симв
    }

    public void editUserNegative(String name, String email, String phone, String about) throws InterruptedException {
        if (count%2 == 0) driver.findElement(By.xpath("//td[text()='User0']")).click();
        else driver.findElement(By.xpath("//td[text()='User1']")).click();
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
        Thread.sleep(500);
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector(FIELD_ERROR_USER)).isEnabled());
        count++;
    }

    @After
    public void tearDown() {
        deleteAllUsers();
        driver.quit();
        driver = null;
    }
}
