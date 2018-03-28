package RollOut.Users;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий наличие элементов и правильность текстовок на вкладке Пользователи.
 * TfsTestCase xxx-xxx
 */

public class UserElement extends RollOutWeb {

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        authSilso(URL_NSMS_SITE_TEST);
        wait.until(titleIs(TITLE_APP));
        driver.get(URL_NSMS_USERS_TEST);
    }

    @Test
    public void editUser() throws InterruptedException {
        Thread.sleep(1000); //Пропуск анимации
        //Проверка центральной страницы
        driver.findElement(By.xpath("//div[contains(text(),'Пользователи  зарегистрированные в Rollout Center')]"));
        driver.findElement(By.xpath("//a[text()='Добавить пользователя']"));
        driver.findElement(By.xpath("//th[contains(text(),'Имя пользователя')]"));
        driver.findElement(By.xpath("//th[contains(text(),'Электронная почта')]"));
        driver.findElement(By.xpath("//th[contains(text(),'Телефон')]"));
        driver.findElement(By.xpath("//th[contains(text(),'Статус')]"));
        driver.findElement(By.xpath("//p[text()='Выберите пользователя для управления свойствами']"));
        driver.findElement(By.cssSelector("img.properties-image"));

        //Проверка элементов на странице добавления пользователя
        driver.findElement(By.cssSelector(BUTTON_ADD_USER)).click();
        driver.findElement(By.xpath("//span[text()='Новый пользователь']"));
        driver.findElement(By.xpath("//span[text()='Имя пользователя:']"));
        driver.findElement(By.xpath("//span[text()='Электронная почта:']"));
        driver.findElement(By.xpath("//span[text()='Телефон:']"));
        driver.findElement(By.xpath("//span[text()='Описание:']"));

    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

}
