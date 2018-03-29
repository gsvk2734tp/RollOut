package RollOut.auth;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static RollOut.RollOutConstants.TITLE_SILSO;
import static RollOut.RollOutConstants.URL_NSMS_SITE_TEST;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий наличие элементов на вкладке с авторизацией
 * TfsTestCase xxx-xxx
 */

@RunWith(value = Parameterized.class)
public class AuthElement extends RollOutWeb {

    public AuthElement(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @Parameterized.Parameters
    public static List<Object> data() {
        Object[] data = new Object[]{new ChromeDriver(), new EdgeDriver()}; // new FirefoxDriver() - не работает
        return Arrays.asList(data);
    }

    @Before
    public void setUp() {
    }

    @Test
    public void auth() throws InterruptedException {
        driver.get(URL_NSMS_SITE_TEST);
        wait.until(titleIs(TITLE_SILSO));
        driver.findElement(By.className("logo"));
        driver.findElement(By.xpath("//span[text()='ViPNet']"));
        driver.findElement(By.xpath("//span[text()='Network Security Management System']"));
        driver.findElement(By.xpath("//span[text()='© 2018, ОАО «ИнфоТеКС»']"));
        driver.findElement(By.className("language-dropdown-toggle-text"));
        driver.findElement(By.xpath("//label[text()='Имя учетной записи:']"));
        driver.findElement(By.xpath("//label[text()='Пароль:']"));
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
