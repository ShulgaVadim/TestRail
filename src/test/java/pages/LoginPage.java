package pages;


import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    public static final By EMAIL_INPUT = By.id("name");
    public static final By PASSWORD_INPUT = By.id("password");
    public static final By LOGIN_BUTTON = By.id("button_primary");
    public static final By ERROR_LABEL = By.cssSelector(".form-error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Login Page is opened")
    public LoginPage isPageOpened() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }

    @Step("Open Login Page")
    public LoginPage openPage() {
        log.info("Opening Login page: " + URL);
        driver.get(URL);
        isPageOpened();
        return this;
    }

    @Step("Login with Email '{email} and Password '{password}'")
    public DashboardPage login(String email, String password){
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new DashboardPage(driver);
    }
}
