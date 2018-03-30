package RollOut.organizations;

import RollOut.RollOutWeb;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Проверка видимости элементов на странице Организации
 */

//TODO: Проверить видимость элементов боковой панели
//TODO: Проверка видимость элементов на верхней панели

@RunWith(value = Parameterized.class)
public class OrgElement extends RollOutWeb {
    private Actions actions;

    public OrgElement(WebDriver driver) {
        super(driver);
    }

    @Before
    public void setUp() {
        actions = new Actions(driver);
        authSilso(URL_NSMS_SITE_TEST);
        wait.until(titleIs(TITLE_APP));
    }

    @Test
    public void orgVisiable() throws InterruptedException {
        //Проверка текстовок и всех элементов на центральной странице
        driver.findElement(By.xpath("//div[contains(text(),'Организации зарегистрированные в Rollout Center')]"));
        driver.findElement(By.xpath("//a[text()='Добавить организацию']"));
        driver.findElement(By.xpath("//th[text()='Название']"));
        driver.findElement(By.xpath("//th[text()='URI префикс']"));
        driver.findElement(By.xpath("//td[text()='Иногенты Юнайтед']"));
        driver.findElement(By.xpath("//td[text()='Ромашка']"));

        //Проверка невидимого элемента Редактировать у первой в списке Орг
        Assert.assertTrue(driver.findElement(By.cssSelector(BUTTON_EDIT_ORG)).isEnabled());
        //Проверка элементов в карточке Организации
        actions.moveToElement(driver.findElement(By.cssSelector(BUTTON_EDIT_ORG)));
        driver.findElement(By.cssSelector(BUTTON_EDIT_ORG)).click();
        driver.findElement(By.xpath("//div[contains(text(),'Данные организации в Rollout Center')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Название:')]"));
        driver.findElement(By.xpath("//div[contains(text(),'URI префикс для авторизации пользователей:')]"));
        driver.findElement(By.cssSelector(BUTTON_CANCEL_ORG)).click();
        Thread.sleep(1000);

        //Проверка невидимого элемента Удалить
        Assert.assertTrue(driver.findElement(By.cssSelector(BUTTON_DELETE_ORG)).isEnabled());
        //Проверка элементов в карточке подтверждения удаления организации
        actions.moveToElement(driver.findElement(By.cssSelector(BUTTON_DELETE_ORG)));
        driver.findElement(By.cssSelector(BUTTON_DELETE_ORG)).click();
        driver.findElement(By.xpath("//div[contains(text(),'Удаление организации')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Подтвердите удаление \"Ромашка\".')]"));
        driver.findElement(By.cssSelector(BUTTON_CANCEL_ORG)).click();
        Thread.sleep(1000);

        //Проверка лого
        driver.findElement(By.className("brand_logo"));
        driver.findElement(By.xpath("//span[text()='ViPNet']"));
        driver.findElement(By.xpath("//span[text()='Network Security Management System']"));
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
