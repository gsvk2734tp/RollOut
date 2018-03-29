package RollOut.Users;

import RollOut.RandomStr;
import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 28.03.2018.
 * Автотест, проверяющий негативные сценарии редактирования свойств пользователя.
 * TfsTestCase xxx-xxx
 */

@RunWith(value = Parameterized.class)
public class EditUserNegative extends RollOutWeb {

    public EditUserNegative(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Parameterized.Parameters
    public static List<Object> data() {
        Object[] data = new Object[]{ new FirefoxDriver(), new EdgeDriver()};
        return Arrays.asList(data);
    }


    @Before
    public void setUp() throws InterruptedException {
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

    @After
    public void tearDown() {
        deleteAllUsers();
        driver.quit();
        driver = null;
    }
}
