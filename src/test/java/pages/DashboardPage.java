package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class DashboardPage extends BasePage{

    public static final String URL = "https://aqa6shulga.testrail.io/index.php?/dashboard";
    public static final By ADDPROJECT_BUTTON = By.id("sidebar-projects-add");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Dashboard Page is opened")
    public DashboardPage isPageOpened() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(ADDPROJECT_BUTTON));
        return this;
    }

    @Step("Opening Dashboard Page")
    public DashboardPage openPage() {
        log.info("Open Dashboard page: " + URL);
        driver.get(URL);
        isPageOpened();
        return this;
    }
}
