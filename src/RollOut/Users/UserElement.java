package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserElement extends RollOutWeb {
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
