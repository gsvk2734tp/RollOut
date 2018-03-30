package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(value = Parameterized.class)
public class Test extends RollOutWeb {
    public Test(WebDriver driver) {
        super(driver);
    }

    @org.junit.Test
    public void start() {
        driver.get("http://yandex.ru");
        driver.quit();
    }
}
