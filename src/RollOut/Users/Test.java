package RollOut.Users;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test {
    private static WebDriver driver = new FirefoxDriver();

    public static void main(String[] args) {
        driver.get("http://tfs.infotecs.ru:8080");
        driver.quit();
    }
}
