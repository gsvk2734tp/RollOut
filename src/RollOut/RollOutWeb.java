package RollOut;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static RollOut.RollOutConstants.BUTTON_ADD_USER;
import static RollOut.RollOutConstants.BUTTON_SAVE_USER;

public abstract class RollOutWeb {
    public WebDriver driver;
    public WebDriverWait wait;
    public int count = 0;
    public char[] specSumb = {'!', '#', '$', '%', '&', '\'', '*', '+', '-', '/', '=', '?', '^', '_', '`', '{', '|', '}', '~'};


    public void createUsers(int number) throws InterruptedException {
        //Открытие карточки для создания пользователя
        for (int i = 0; i < number; i++) {
            driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.header_title")));
            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(By.cssSelector("div.host_input input"));
            elements.get(0).sendKeys("User" + count);
            elements.get(1).sendKeys("q@q");
            elements.get(2).sendKeys();
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
        //Проверка, что кнопка Сохранить не доступна.
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        //Проверка, что появилась ошибка. Нужно подумать, как лучше проверять

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
        //Проверка, что кнопка Сохранить не доступна.
        Assert.assertFalse(driver.findElement(By.cssSelector(BUTTON_SAVE_USER)).isEnabled());
        //Проверка, что появилась ошибка. Ошибка еще не реализована
    }

}
