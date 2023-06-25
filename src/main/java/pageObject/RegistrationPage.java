package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {

        this.driver = driver;
    }
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    public final By errorPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    public final By registrationText = By.xpath(".//div/h2[text()='Регистрация']");

    // метод ввода имени
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // метод ввода email
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    // метод ввода пароля
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }


    public void clickRegisterButton(){
        driver.findElement(registerButton).click();
        waitForInvisibilityLoadingAnimation();
    }

    @Step
    public void registerNewUser(String name, String email,String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Метод для ожидания исчезвновения анимации регистраниции")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
    @Step("Ожидание загрузки страницы регистрации")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationText));
    }

}
