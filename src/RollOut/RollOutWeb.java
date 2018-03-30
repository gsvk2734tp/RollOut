package RollOut;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public abstract class RollOutWeb {
    public WebDriver driver;
    public WebDriver firefoxDriver;
    public WebDriverWait wait;
    public static OperaOptions options = new OperaOptions();
    {
        options.setBinary("C:\\Program Files\\Opera\\52.0.2871.40\\opera.exe");
    }


    public int count = 0;
    public char[] specSumb = {'!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|', '}', '~'};
    public char[] specSumbUserName = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};


    public void createUsers(int number) throws InterruptedException {
        //Открытие карточки для создания пользователя
        for (int i = 0; i < number; i++) {
            driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
            elements.get(0).sendKeys("User" + count);
            elements.get(1).sendKeys("q@q");
            elements.get(2).sendKeys("+7925");
            driver.findElement(By.cssSelector("textarea")).sendKeys("123qwe");
            driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='User" + count + "']")));
            count++;
        }
    }

    public void createUser(String userName, String email, String mobile) throws InterruptedException {
        //Открытие карточки для создания пользователя
        driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        elements.get(0).sendKeys(userName);
        elements.get(1).sendKeys(email);
        elements.get(2).sendKeys(mobile);
        driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Создан пользователь " + userName + "']")));
        count++;
        //Проверка, что пользователь появился в списке
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + userName + "']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + email + "']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + mobile + "']")));

    }

    public void createUser(String userName, String email, String mobile, String about) throws InterruptedException {
        //Открытие карточки для создания пользователя
        driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        elements.get(0).sendKeys(userName);
        elements.get(1).sendKeys(email);
        elements.get(2).sendKeys(mobile);
        driver.findElement(By.cssSelector("textarea")).sendKeys(about); //Описание
        driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Создан пользователь " + userName + "']")));
        count++;
        //Проверка, что пользователь появился в списке
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + userName + "']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + email + "']")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + mobile + "']")));
    }

    public void createUserNegative(String userName, String email, String mobile) throws InterruptedException {
        //Открытие карточки для создания пользователя
        driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        elements.get(0).sendKeys(userName);
        elements.get(1).sendKeys(email);
        elements.get(2).sendKeys(mobile);
        //Проверка, что кнопка Сохранить не доступна. Присутствует сообщение об ошибке
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector(FIELD_ERROR_USER)).isEnabled());
    }

    public void createUserNegative(String userName, String email, String mobile, String about) throws InterruptedException {
        //Открытие карточки для создания пользователя
        driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        elements.get(0).sendKeys(userName);
        elements.get(1).sendKeys(email);
        elements.get(2).sendKeys(mobile);
        driver.findElement(By.cssSelector("textarea")).sendKeys(about); //Описание
        //Проверка, что кнопка Сохранить не доступна. Присутствует сообщение об ошибке
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector(FIELD_ERROR_USER)).isEnabled());
    }

    public void deleteAllUsers() {
        try {
            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='a']")));
            driver.findElement(By.cssSelector(CHECKBOX_SELECTALL_USERS)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Удалить пользователей']")));
            driver.findElement(By.cssSelector("a.toolbar_button:nth-child(1)")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BUTTON_DELETE_YES_USER)));
            driver.findElement(By.cssSelector(BUTTON_DELETE_YES_USER)).click();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Ошибка очистки списка");
        }
    }

    public void editUserPositive(String name, String email, String phone, String about) throws InterruptedException {
        driver.findElement(By.xpath("//td[text()='User" + (count - 1) + "']")).click();
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        if (name != null) {
            elements.get(0).clear();
            elements.get(0).sendKeys(name);
        }
        if (email != null) {
            elements.get(1).clear();
            elements.get(1).sendKeys(email);
        }
        if (phone != null) {
            elements.get(2).clear();
            elements.get(2).sendKeys(phone);
        }
        if (about != null) {
            driver.findElement(By.cssSelector("textarea")).clear();
            driver.findElement(By.cssSelector("textarea")).sendKeys(about);
        }
        driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).click();
        Thread.sleep(1000);
        if (name != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + name + "']")));
        if (email != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + email + "']")));
        if (phone != null) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='" + phone + "']")));
        count--;
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
    public void editUserNegative(String name, String email, String phone, String about) throws InterruptedException {
        if (count % 2 == 0) driver.findElement(By.xpath("//td[text()='User0']")).click();
        else driver.findElement(By.xpath("//td[text()='User1']")).click();
        Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
        if (name != null) {
            elements.get(0).clear();
            elements.get(0).sendKeys(name);
        }
        if (email != null) {
            elements.get(1).clear();
            elements.get(1).sendKeys(email);
        }
        if (phone != null) {
            elements.get(2).clear();
            elements.get(2).sendKeys(phone);
        }
        if (about != null) {
            driver.findElement(By.cssSelector("textarea")).clear();
            driver.findElement(By.cssSelector("textarea")).sendKeys(about);
        }
        Thread.sleep(500);
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        Assert.assertTrue(driver.findElement(By.cssSelector(FIELD_ERROR_USER)).isEnabled());
        count++;
    }

}
