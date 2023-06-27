import Client.User;
import Client.UserGenerator;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.ForgotPasswordPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;

import java.util.concurrent.TimeUnit;


public class LoginPageTest {

    WebDriver driver;
    String emailValue = "testUSer1234@yandex.ru";
    String passwordValue = "1234567";
    User user;

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
    @Description("Логин с главной страницы")
    public void loginFromMainPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(emailValue, passwordValue);
        Assert.assertTrue(mainPage.checkMainPageLoad());

    }
    @Test
    @Description("Логин со страницы личного кабинета")
    public void loginFromProfilePageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(emailValue, passwordValue);
        Assert.assertTrue(mainPage.checkMainPageLoad());

    }
    @Test
    @Description("Логин со страницы регистрации")
    public void loginFromRegistrationPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegister();
        RegistrationPage registerPage = new RegistrationPage(driver);
        user = UserGenerator.createRandomUser();
        registerPage.registerNewUser(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitForLoadEntrance();
        loginPage.loginUser("leonid@yandex.ru", "12345asd");
        Assert.assertTrue(mainPage.checkMainPageLoad());
    }
    @Test
    @Description("Логин со страницы восстановления пароля")
    public void loginFromForgotPasswordPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordLink();
        ForgotPasswordPage recoverPasswordPage = new ForgotPasswordPage(driver);
        recoverPasswordPage.waitForLoadedRecoverPassword();
        recoverPasswordPage.clickOnLoginLink();
        loginPage.loginUser(emailValue, passwordValue);
        Assert.assertTrue(mainPage.checkMainPageLoad());

    }
    @After
    public void shutDown(){
        driver.quit();
    }
}
