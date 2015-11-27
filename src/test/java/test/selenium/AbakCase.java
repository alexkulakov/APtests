package test.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AbakCase {

    private WebDriver driver;

    @Before
    public void BeforeTest()
    {
        driver = new FirefoxDriver();
    }

    @After
    public void AfterTest()
    {
        driver.quit();
    }

    @Test
    public void firstCase()
    {
        driver.get("http://www.apress.ru/");

        driver.findElement(By.id("menu-item-213")).click();
        driver.findElement(By.xpath(".//a[text()='Екатеринбург']")).click();
        driver.findElement(By.xpath(".//span[@class='selectbox']//b[@class='trigger']")).click();
        driver.findElement(By.xpath(".//span[@class='selectbox']/div[@class='dropdown']//li[text()='IT']")).click();
        driver.findElement(By.xpath(".//input[@value='Показать вакансии']")).click();
        List<WebElement> elements = driver.findElements(By.cssSelector("div.content-container>div:nth-child(2) ul>li div.vacancy-title"));
        WebElement[] rows = elements.toArray(new WebElement[elements.size()]);

        List<String> titles = Arrays.stream(rows)
                .map(e -> e.getText().toLowerCase()).collect(Collectors.toList());

        List<String> expectedStrings = new ArrayList<>(Arrays.asList(new String[]{"qa", "тестировщик"}));

        Assert.assertTrue( "There are't " + expectedStrings + " in " + titles, titles.containsAll(expectedStrings));
    }
}
