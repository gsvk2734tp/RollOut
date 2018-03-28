package RollOut.organizations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static RollOut.RollOutConstants.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * @author Golyshkin.Dmitriy on 27.03.2018.
 * Автотест, проверяющий видимость элементов и корректность текстовок на вкладке Организации
 * TfsTestCase xxx-xxx
 */

public class OrgElement {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    @Test
    public void orgVisiable() {
        driver.get(URL_NSMS_SITE);
        wait.until(titleIs(TITLE_APP));

        //Проверка текстовок и всех элементов на центральной странице
        String s = driver.findElement(By.cssSelector("div.header")).getText();
        Assert.assertEquals(s, "Организации зарегистрированные в Rollout Center");
        driver.findElement(By.xpath("//div[contains(text(),'Организации зарегистрированные в Rollout Center')]"));
        driver.findElement(By.xpath("//a[text()='Добавить организацию']"));
        driver.findElement(By.xpath("//th[text()='Название']"));
        driver.findElement(By.xpath("//th[text()='URI префикс']"));
        driver.findElement(By.xpath("//td[text()='Быки и Коровы']"));
        driver.findElement(By.xpath("//td[text()='Ромашка']"));
        driver.findElement(By.cssSelector("td.organization-list_first-column")).click();

        //Проверка невидимого элемента Редактировать у первой в списке Орг
        Assert.assertTrue(driver.findElement(By.cssSelector("tr:nth-child(2) i.table_edit-row-icon")).isEnabled());
        //Проверка элементов в карточке Организации
        actions.moveToElement(driver.findElement(By.cssSelector("tr:nth-child(2) i.table_edit-row-icon")));
        driver.findElement(By.cssSelector("tr:nth-child(2) i.table_edit-row-icon")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Данные организации в Rollout Center')]"));
        driver.findElement(By.xpath("//div[contains(text(),'Название:')]"));
        driver.findElement(By.xpath("//div[contains(text(),'URI префикс для авторизации пользователей:')]"));
        driver.findElement(By.cssSelector("button:nth-child(2)")).click();

        //Проверка невидимого элемента Удалить

        //Проверка лого
        driver.findElement(By.className("brand_logo"));
        driver.findElement(By.xpath("//span[text()='ViPNet']"));
        driver.findElement(By.xpath("//span[text()='Network Security Management System']"));

        //Проверка боковой панели

        //Проверка шапки
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
