package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


@Log4j2
public class TestRailProjectPage extends BasePage{

    public static final String TESTRAIL_URL = "https://aqa6shulga.testrail.io/index.php?/projects/overview/2";
    public static final By MILESTONES = By.id("sidebar-milestones-overview");
    public static final By TESTRUNS = By.id("sidebar-runs-overview");
    public static final By TESTCASES = By.id("sidebar-cases-overview");
    public static final By ADD_MILESTONE_BUTTON = By.id("sidebar-milestones-add");
    public static final By ADD_TESTRUN_BUTTON = By.id("sidebar-runs-add");
    public static final By ADD_TESTCASE_BUTTON = By.id("sidebar-cases-add");



    public TestRailProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that TestRail Project Page is opened")
    public TestRailProjectPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TESTRUNS));
        return this;
    }

    @Step("Opening estRail Project Page Page")
    public TestRailProjectPage openPage() {
        log.info("Open TestRail Project Page page: " + TESTRAIL_URL);
        driver.get(TESTRAIL_URL);
        isPageOpened();
        return this;
    }
}
