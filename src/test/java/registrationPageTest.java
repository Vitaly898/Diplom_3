import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.util.concurrent.TimeUnit;


public class registrationPageTest {
    private WebDriver driver;
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
    @Description("Регистрация пользователя с валидными данными")
    public void registrationTestSuccess(){
        mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegistrationPage registerPage = new RegistrationPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registerNewUser("igor", "lemon@mail.eu", "1234567");
        loginPage.waitForLoadEntrance();
    }
    @Test
    @Description("Регистрация пользователя с невалидным паролем")
    public void registrationTestFail(){
        mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegistrationPage registerPage = new RegistrationPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registerNewUser("igor", "apelsin@mail.eu", "123");
        Assert.assertTrue(driver.findElement(registerPage.errorPasswordText).isDisplayed());
    }

    @After
    public void shutDown(){
        driver.quit();
    }
}
