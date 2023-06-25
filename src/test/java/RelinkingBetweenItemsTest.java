import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.MainPage;

import java.util.concurrent.TimeUnit;

public class RelinkingBetweenItemsTest {
    WebDriver driver;
    MainPage mainPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/vitalypetrov/IdeaProjects/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*","--headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @Description("Переход в раздел 'Соусы'")
    public void relinkToSaucesTest() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        Assert.assertEquals(driver.findElement(mainPage.getSaucesButton()).getText(),"Соусы");
    }
    @Test
    @Description("Переход в раздел 'Булки'")
    public void relinkToBunsTest() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        Assert.assertEquals(driver.findElement(mainPage.getBunsButton()).getText(),"Булки");
    }
    @Test
    @Description("Переход в раздел 'Начинки'")
    public void relinkToFillingsTest() throws InterruptedException {
        mainPage = new MainPage(driver);
        mainPage.clickOnFillingButton();
        Assert.assertEquals(driver.findElement(mainPage.getFillingsButton()).getText(),"Начинки");
    }

    @After
    public void shutDown(){
        driver.quit();
    }
}
