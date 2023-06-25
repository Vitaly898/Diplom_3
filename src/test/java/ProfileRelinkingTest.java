import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.ProfilePage;

import java.util.concurrent.TimeUnit;


public class ProfileRelinkingTest {
    WebDriver driver;
    String emailValue = "testUSer1234@yandex.ru";
    String passwordValue = "1234567";
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
    @Description("Переход с главной страницы на страницу логина")
    public void relinkFromMainToLoginPageTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue( driver.findElement(loginPage.login).isDisplayed());
    }

    @Test
    @Description("Переход по клику на лого Бургрера")
    public void relinkToConstructorPageClickOnBurger(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue( driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }
    @Test
    @Description("Переход из личного кабинета в конструктор")
    public void relinkToConstructorFromAccountPage(){
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue( driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }
    @Test
    @Description("выход из аккаунта")
    public void exitAccountTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.loginUser(emailValue, passwordValue);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickOnExitButton();
        mainPage.waitForInvisibilityLoadingAnimation();
        Assert.assertTrue("Не удалось выйти из аккаунта", driver.findElement(loginPage.login).isDisplayed());
    }

}
