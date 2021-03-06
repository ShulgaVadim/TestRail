package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.modals.DeleteTestcaseModal;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestcasesPage extends BasePage {

    public static final By TESTCASES_BUTTON = By.id("navigation-cases-section");
    private static final By ADD_TESTCASE_BUTTON = By.id("sidebar-cases-add");
    private static final By EDIT_BUTTON = By.xpath("//span[contains(text(),'Edit')]");
    public static final By TESTCASES_XPATH = By.xpath("//table[@class='grid']//td[4]/a/span");
    String testcaseLocator = "//td//span[text()='%s']/ancestor::a";
    String deleteTestCase = "//span[contains(text(),'%s')]/ancestor::td/following-sibling::td[2]";
    String textareaLocator = "//span[text()='%s']/parent::div/following-sibling::div//p";
    List<WebElement> testCases;

    public TestcasesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Test cases Page is opened")
    public TestcasesPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_TESTCASE_BUTTON));
        return this;
    }

    @Step("Click 'Add Test case' Button")
    public AddTestcasePage clickAddTestcase() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TESTCASE_BUTTON)).click();
        return new AddTestcasePage(driver);
    }

    @Step("Validate that Test case '{testCaseName}' is created")
    public boolean isTestCaseCreated(String testCaseName) {
        boolean isCreated = false;
        if (
                driver.findElement(By.xpath(String.format(testcaseLocator, testCaseName))).isDisplayed()) {
            isCreated = true;
        }
        return isCreated;
    }

    @Step("Click 'Test Cases' button")
    public TestcasesPage clickTestCasesButton() {
        driver.findElement(TESTCASES_BUTTON).click();
        return this;
    }

    @Step("Open Edit window for '{testCaseName}'")
    public TestcasesPage openEditing(String testCaseName) {
        driver.findElement(By.xpath(String.format(testcaseLocator, testCaseName))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT_BUTTON)).click();
        return this;
    }

    @Step("Get text from '{textarea}'")
    public String getTextfromTextarea(String textarea) {
        log.info("Get text from " + textarea);
        return driver.findElement(By.xpath(String.format(textareaLocator, textarea))).getText();
    }

    @Step("Validate that Test case '{editedTestCaseName}' is edited")
    public TestcasesPage isTestCaseEdited(String editedTestCaseName, String editedExpectedResult) {
        log.info("Validate that Test case " + editedTestCaseName + "is edited");
        String editTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page_title"))).getText();
        assertEquals(editTitle, editedTestCaseName);
        assertEquals(getTextfromTextarea("Expected Result"), editedExpectedResult);
        return this;
    }

    @Step("Open Delete window for '{testCaseName}'")
    public DeleteTestcaseModal clickDelete(String testCaseName) {
        driver.findElement(By.xpath(String.format(deleteTestCase, testCaseName))).click();
        return new DeleteTestcaseModal(driver);
    }

    @Step("Validation that Test Case '{testCaseName}' is deleted")
    public boolean isTestCaseDeleted(String testCaseName) {
        log.info("Validate that Test Case " + testCaseName + "is deleted");
        driver.navigate().refresh();
        boolean isDeleted;
        testCases = driver.findElements(TESTCASES_XPATH);

        if (testCases.size() == 0) {
            isDeleted = true;
        } else {
            isDeleted = false;
        }
        return isDeleted;
    }
}
