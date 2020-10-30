package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class NewProjectPage extends BasePage {

    public static final By NAME = By.id("name");
    public static final By ANNOUNCEMENT = By.id("announcement");
    public static final By SINGLE_REP_RADIOBUTTON = By.id("suite_mode_single");
    public static final By SINGLE_REP_BASESUPPORT_RADIOBUTTON = By.id("suite_mode_single_baseline");
    public static final By MULTIPLE_TESTSUITES_RADIOBUTTON = By.id("suite_mode_multi");
    public static final By ADD_PROJECT_BUTTON = By.id("accept");

    public NewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that NewProject Page is opened")
    public NewProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    @Step("Click 'Add Project' button")
    public NewProjectPage clickAddProjectButton() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return this;
    }

    @Step("Create Project '{name}'")
    public ProjectsPage createProject(String name, String announcement) {
        log.info("Creating new project: " + name);
        driver.findElement(NAME).sendKeys(name);
        driver.findElement(ANNOUNCEMENT).sendKeys(announcement);
        driver.findElement(SINGLE_REP_RADIOBUTTON).click();
        clickAddProjectButton();
        return new ProjectsPage(driver);
    }
}
