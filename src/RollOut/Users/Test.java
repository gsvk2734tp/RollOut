package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

@RunWith(value = Parameterized.class)
public class Test extends RollOutWeb {

    public Test(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Parameterized.Parameters
    public static List<Object> data() {
        Object[] data = new Object[]{new OperaDriver(options), new FirefoxDriver(), new ChromeDriver(), new EdgeDriver()};
        return Arrays.asList(data);
    }

    @org.junit.Test
            public void start() {
        driver.get("http://yandex.ru");
        driver.quit();
    }
}
