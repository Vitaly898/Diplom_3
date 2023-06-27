package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By accountButton = By.xpath(".//a[@href='/account']");
    private final By logo = By.xpath(".//div/a[@href='/']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    private final By activeIngredientTab = By.cssSelector(".tab_tab_type_current__2BEPc");

    public By bunsImg = By.xpath(".//img[@alt='Краторная булка N-200i']");
    public By bunsText = By.xpath(".//h2[text()='Булки']");
    public By saucesImg = By.xpath(".//p[text()='Соус с шипами Антарианского плоскоходца']");
    public By fillingsImg = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");

    public By getBunsButton() {
        return bunsButton;
    }

    public By getSaucesButton() {
        return saucesButton;
    }

    public By getFillingsButton() {
        return fillingsButton;
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Клик по логотипу 'Stellar Burgers'")
    public void clickOnLogo() {
        driver.findElement(logo).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
        waitForInvisibilityLoadingAnimation();
    }


    @Step("Клик по кнопке 'Булки'")
    public void clickOnBunsButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(bunsButton).click();

    }
    @Step("Клик по кнопке 'Соуса'")
    public void clickOnSaucesButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(saucesButton).click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickOnFillingButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(fillingsButton).click();
    }

    @Step("Выставлено ожидание загрузки страницы полностью, анимация исчезнет.")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }

    @Step("Проверка, что отобразился main контейнер главной страницы")
    public Boolean checkMainPageLoad(){
        return driver.findElement(bunsButton).isDisplayed();
    }
    @Step("Получение названия активного таба")
    public String getTextOfActiveTab(){
      return   driver.findElement(activeIngredientTab).getText();
    }




}
