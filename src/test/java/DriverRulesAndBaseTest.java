import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import api.UserActivities;
import parameters.ApiEndpoints;
import parameters.LoginStartingPoints;
import data.UserDataGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import user.UserModel;
import org.junit.After;
import org.junit.Before;
import pom.LoginPage;
import pom.HomePage;
import pom.PasswordRecoveryPage;
import pom.RegistrationPage;

import java.time.Duration;

import static javax.print.attribute.standard.MediaSizeName.C;

public abstract class DriverRulesAndBaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected LoginPage loginPage;
    protected PasswordRecoveryPage passwordRecoveryPage;
    protected RegistrationPage registrationPage;
    protected UserActivities userActivities;
    protected UserModel userModel;
    protected String accessToken;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println("Запуск теста для браузера: " + browser);

        if ("yandex".equals(browser)) {

            System.setProperty("webdriver.chrome.driver", "C:/YandexDriver/yandexdriver-26.4.3.894-win64/yandexdriver.exe");

            ChromeOptions yandexOptions = new ChromeOptions();

            yandexOptions.setBinary("C:\\Program Files\\Yandex\\YandexBrowser\\application\\browser.exe");

            yandexOptions.addArguments("--no-sandbox");
            yandexOptions.addArguments("--disable-dev-shm-usage");
            yandexOptions.addArguments("--disable-gpu");
            yandexOptions.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(yandexOptions);

        } else {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(ApiEndpoints.BASE_URL);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        registrationPage = new RegistrationPage(driver);
        userActivities = new UserActivities();
        if (!(this instanceof RegistrationTest)) {
            userModel = UserDataGenerator.GenerateUser();
            accessToken = userActivities.createUser(userModel).extract().path("accessToken");
        }
    }

    @After
    public void tearDown() {
        if (accessToken != null) userActivities.deleteUser(accessToken);
        if (driver != null) driver.quit();
    }

    @Step("Открытие страниц авторизации через разные {entryPoint}")
    public void openLoginPage(LoginStartingPoints entryPoint) {
        switch (entryPoint) {
            case HOME_PAGE:
                homePage.clickLoginButton(); break;
            case PROFILE:
                homePage.clickProfileButton(); break;
            case REGISTER_PAGE:
                homePage.clickProfileButton();
                loginPage.loginPageToLoad();
                loginPage.clickLinkRegister();
                registrationPage.clickLinkLogin(); break;
            case PASSWORD_RECOVERY_PAGE:
                homePage.clickProfileButton();
                loginPage.loginPageToLoad();
                loginPage.clickLinkRecoverPassword();
                passwordRecoveryPage.clickLinkLogin(); break;
        }
    }
}