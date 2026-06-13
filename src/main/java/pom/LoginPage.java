package pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    private final By emailField = By.xpath("//input[@name='name' or @type='text']");
    private final By passwordField = By.xpath("//input[@name='Пароль' or @type='password']");
    private final By buttonLogin = By.xpath("//*[contains(text(),'Войти')]");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By recoverPasswordButton = By.linkText("Восстановить пароль");

    @Step("Загрузка страницы авторизации")
    public void loginPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> d.findElement(headerLogin).isDisplayed());
    }

    @Step("Ввести электронную почту: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickLinkRegister() {
        driver.findElement(registerLink).click();
    }

    @Step("Нажать на кнопку 'Восстановить пароль'")
    public void clickLinkRecoverPassword() {
        driver.findElement(recoverPasswordButton).click();
    }

}
