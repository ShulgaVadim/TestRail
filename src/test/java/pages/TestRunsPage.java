package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestRunsPage extends BasePage {

    private static final By TESTRUNS_BUTTON = By.xpath("//a[@id='navigation-runs']");
    private static final By ADD_TESTRUN_BUTTON = By.id("sidebar-runs-add");
    private static final By EDIT_BUTTON = By.cssSelector(".button-edit");
    public static final By TESTRUNS_XPATH = By.cssSelector(".summary-title");
    String editTestRunLocator = "//a[text()='%s']/parent::div/following-sibling::div/a";
    String testRunNameLocator = "//div[@class='summary-title text-ppp']/a[text()='%s']";
    private static final By EXPECTED_NAME = By.cssSelector(".page_title");
    private static final By EXPECTED_DESCRIPTION = By.xpath("//div[@class='markdown']");
    List<WebElement> testruns;

    public TestRunsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Test Runs Page is opened")
    public TestRunsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TESTRUN_BUTTON));
        return this;
    }

    @Step("Click 'Add Test Run' Button")
    public AddTestRunPage clickAddTestRun() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TESTRUN_BUTTON)).click();
        return new AddTestRunPage(driver);
    }

    @Step("Click 'Test Runs' Button")
    public TestRunsPage clickTestRunsButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TESTRUNS_BUTTON)).click();
        return this;
    }

    @Step("Validation that Test Run '{testRunName}' is created")
    public boolean isTestRunCreated(String testRunName) {
        boolean isCreated = false;
        if (
                driver.findElement(By.xpath(String.format(testRunNameLocator, testRunName))).isDisplayed()) {
            isCreated = true;
        }
        return isCreated;
    }

    @Step("Click 'Edit Test Run' Button")
    public AddTestRunPage clickEditTestRun(String testRunName) {
        driver.findElement(By.xpath(String.format(editTestRunLocator, testRunName))).click();
        return new AddTestRunPage(driver);
    }

    @Step("Validation that Test Run '{editedTestRunName}' is edited")
    public TestRunsPage isTestRunEdited(String editedTestRunName, String editDescription) {
        log.info("Validation that Test Run " + editedTestRunName + "is edited");
        driver.findElement(By.xpath(String.format(testRunNameLocator, editedTestRunName))).click();
        String expectedName = driver.findElement(EXPECTED_NAME).getText();
        String expectedDescription = driver.findElement(EXPECTED_DESCRIPTION).getText();
        assertEquals(editedTestRunName, expectedName);
        assertEquals(editDescription, expectedDescription);
        return this;
    }

    @Step("Click 'Edit Test Run' Button")
    public AddTestRunPage clickEdit() {
        driver.findElement(EDIT_BUTTON).click();
        return new AddTestRunPage(driver);
    }

    @Step("Validation that Test Run '{editedTestRunName}' is deleted")
    public boolean isTestRunDeleted(String editedTestRunName) {
        log.info("Validate that Milestone " + editedTestRunName + "is deleted");
        driver.navigate().refresh();
        boolean isDeleted = true;
        testruns = driver.findElements(TESTRUNS_XPATH);

        if (testruns.size() == 0) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }
}

