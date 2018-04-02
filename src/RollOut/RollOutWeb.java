package RollOut;

import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public abstract class RollOutWeb {
    public WebDriver driver;
    public WebDriver firefoxDriver;
    public WebDriverWait wait;
    public static OperaOptions options = new OperaOptions();
    public int count = 0;
    public char[] specSumb = {'!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|', '}', '~'};
    public char[] specSumbUserName = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};

    @Parameterized.Parameters
    public static List<Object> data() {
        options.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
        Object[] data = new Object[]{new OperaDriver(options), new FirefoxDriver(), new ChromeDriver(), new EdgeDriver()};
        return Arrays.asList(data);
    }

    public RollOutWeb(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    public void authSilso(String site) {
        driver.get(site);
        //Редирект на страницу аутентификации
        wait.until(titleIs(TITLE_SILSO));
        driver.findElement(By.id("UserName")).sendKeys(LOGIN);
        driver.findElement(By.id("Password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(BUTTON_LOGIN)).click();
        wait.until(titleIs(TITLE_APP));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='Ромашка']")));
    }


}
