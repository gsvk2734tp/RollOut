package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
Проверка создания пользователей */

public class CreateMoreUsers extends RollOutWeb {

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);

        driver.get("https://10.0.15.39:8811/account/login");
            //Редирект на страницу аутентификации
            wait.until(titleIs("Silso"));
            driver.findElement(By.id("UserName")).sendKeys("alice");
            driver.findElement(By.id("Password")).sendKeys("P@ssw0rd");
            driver.findElement(By.cssSelector("button.btn-login")).click();

            //Ждем редиректа
            driver.get("http://10.0.15.39:8090/organizations");
            wait.until(titleIs("Rollout.WebApplication"));
            //Кастыль из-за необходимости обновлять страницу после входа
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='Ромашка']")));
            driver.findElement(By.cssSelector("div.header-menu_icon-link")).click();

            driver.get("http://10.0.15.39:8090/users");
            //Выбор выпадающего списка
            driver.findElement(By.cssSelector("div.header-menu_icon-link")).click();
            driver.findElement(By.className("dropdown-menu_item")).click();

            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='alex75']")));

    }

    @Test
        public void newUser() throws IOException, InterruptedException {
        //Открытие
        wait.until(titleIs("Rollout.WebApplication"));
        //Открытие карточки для создания пользователя
        for (int i = 0; i < 100; i++) {
            driver.findElement(By.cssSelector("a.toolbar_button")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
            elements.get(0).sendKeys("" + count);
            elements.get(1).sendKeys(count + "@" + count);
            //elements.get(2).sendKeys("+7" + count + count + count);
            //driver.findElement(By.cssSelector("textarea")).sendKeys(RandomStrings.randomStr(128)); //Описание
            count++;
            driver.findElement(By.cssSelector("button:nth-child(1)")).click();
        }
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}