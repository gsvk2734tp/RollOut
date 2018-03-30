package RollOut.Users;

import RollOut.RandomStr;
import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 28.03.2018.
 * Автотест, проверяющий позитивные сценарии редактирования свойств пользователя с проверкой, что редактирование применено.
 * TfsTestCase xxx-xxx
 */

@RunWith(value = Parameterized.class)
public class EditUserPositive extends RollOutWeb {

    public EditUserPositive(WebDriver driver) {
        super(driver);
    }

    @Before
    public void setUp() throws InterruptedException {
        //authSilso(URL_NSMS_SITE_TEST);
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS);
    }

    @Test
    public void editUsers() throws InterruptedException {
        int number = 15;
        Thread.sleep(3000);
        createUsers(number);
        editUserPositive("1", null, null, null); // 1 0 0 0
        editUserPositive(null, "1@gmail.com", null, null); // 0 1 0 0
        editUserPositive(null, null, "+721", null); // 0 0 1 0
        editUserPositive(null, null, null, "Q"); // 0 0 0 1

        editUserPositive(RandomStr.getStr(128), RandomStr.getStr(63) + "@gmail.com", null, null); // 1 1 0 0
        editUserPositive(RandomStr.getStr(63), null, "+123456789012345", null); // 1 0 1 0
        editUserPositive("qwe1", null, null, RandomStr.getStr(128)); // 1 0 0 1
        editUserPositive(null, "alice@1", "+87654321", null); // 0 1 1 0
        editUserPositive(null, "Q@" + RandomStr.getStrDomain(252), null, RandomStr.getStr(57)); // 0 1 0 1
        editUserPositive(null, null, "   9876512   ", "  qwe   "); // 0 0 1 1

        editUserPositive("qwe2", RandomStr.getStr(60) + "@" + RandomStr.getStrDomain(193), "123456789012345", null); // 1 1 1 0
        editUserPositive("qwe3", null, "721", "qwe"); // 1 0 1 1
        editUserPositive("a", "alice@d", null, "qwe"); // 1 1 0 1
        editUserPositive(null, "z@1", "+721", "qwe"); // 0 1 1 1
        editUserPositive("qwe4", "z@1", "+721", "qwe"); // 1 1 1 1
    }

    @After
    public void tearDown() {
        deleteAllUsers();
        driver.quit();
        driver = null;
    }
}
