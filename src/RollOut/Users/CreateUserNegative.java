package RollOut.Users;

import RollOut.RollOutWeb;
import RollOut.RandomStr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий негативные сценарии создания пользователя
 * TfsTestCase xxx-xxx
 */

public class CreateUserNegative extends RollOutWeb {

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        //Открытие
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
    }

    @Test
    public void createNewUserAndCheckVisible() throws IOException, InterruptedException {
        //Проверка имени негативные сценарии
        createUserNegative("", "", ""); // все поля пустые
        createUserNegative("", "gmail@gmail.com", ""); // пустое user
        createUserNegative("", "gmail@gmail.com", "");
        // createUser(" \\ / : * ? \" < > | ", "gmail@gmail.com", ""); // спецсимволы, баг
        createUserNegative(RandomStr.getStr(129), "gmail@gmail.com", "");
        createUserNegative(RandomStr.getStr(257), "gmail@gmail.com", "");

        //Проверка email негативные
        createUserNegative("User", "@" + "gmail.com", ""); // локальная 0 симв
        //createUser("User", RandomStr.getStr(64) + "@" + "gmail.com", ""); // локальная 64 симв, баг

        /* Проверка на спецсимволы в локальной части, запрещены вначале в локальной части, пока что баг
        for (char sumb : specSumb) {
            createUser("User", sumb + "@" + RandomStr.getStr(3), "");
        } */

        createUserNegative("User", "alice" + "@", ""); // доменная 0 симв
        createUserNegative("User", "alice" + "@" + RandomStr.getStr(65), ""); // доменная более 63 без точки
        createUserNegative("User", "1" + "@" + RandomStr.getStrDomain(253), ""); // более 252 симв

        // Проверка на спецсимволы, запрещены в доменной части
        for (char sumb : specSumb) {
            if (sumb != '-' && sumb != '.') createUserNegative("User", "User" + "@" + "gmail" + sumb + "com", "");
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
        //Выгрузка браузера
        driver.quit();
        driver = null;
    }
}
