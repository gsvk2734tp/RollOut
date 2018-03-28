package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserRole extends RollOutWeb {
    /**
     * @author Golyshkin.Dmitriy on 27.03.2018.
     * Автотест, проверяющий роли пользователей.
     * TfsTestCase Фича не реализована
     */

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void editUser() {

    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
