package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Testcase;
import org.openqa.selenium.WebDriver;
import pages.AddTestcasePage;
import pages.TestcasesPage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class TestCasesSteps {

    TestcasesPage testcasesPage;
    AddTestcasePage addTestcasePage;

    public TestCasesSteps(WebDriver driver) {
        testcasesPage = new TestcasesPage(driver);
        addTestcasePage = new AddTestcasePage(driver);
    }

    @Step("Create new Test case: '{testcase}'")
    public TestCasesSteps createNewTestCase(Testcase testcase){
        log.info("Create new Test case " + testcase);
        testcasesPage
                .isPageOpened()
                .clickAddTestcase();
        addTestcasePage
                .createNewTestCase(testcase)
                .clickAddTestcase();
        return this;
    }

    @Step("Validation that Test case '{testCaseName}' is created")
    public TestCasesSteps isTestCaseCreated(String testCaseName) {
        log.info("Validation that Test case "  + testCaseName + " is created");
        testcasesPage
            .clickTestCasesButton();
        assertTrue(testcasesPage.isTestCaseCreated(testCaseName));
        return this;
    }

    @Step("Edit Test case: '{testCaseName}'")
    public TestCasesSteps editTestCase(Testcase editTestCase, String testCaseName){
        log.info("Editing Test case: "  + editTestCase);
        testcasesPage
                .openEditing(testCaseName);
        addTestcasePage
                .createNewTestCase(editTestCase)
                .clickAddTestcase();
        return this;
    }

    @Step("Validate that Test case '{editedTestCaseName}' is edited")
    public TestCasesSteps isTestCaseEdited(String editedTestCaseName, String editedExpectedResult) {
        testcasesPage
                 .isTestCaseEdited(editedTestCaseName, editedExpectedResult);
        return this;
    }

    @Step("Delete Test case: '{testCaseName}'")
    public TestCasesSteps deleteTestCase(String testCaseName){
        log.info("Deleting Test case: "  + testCaseName);
        testcasesPage
                .clickTestCasesButton()
                .isPageOpened()
                .clickDelete(testCaseName)
                .isModalOpened()
                .delete();
        return this;
    }

    @Step("Validation that Test case '{testCaseName}' is deleted")
    public TestCasesSteps isTestCaseDeleted(String testCaseName) {
        log.info("Validation that Test case " + testCaseName + " is deleted");
        testcasesPage
                .isPageOpened();
        assertTrue(testcasesPage.isTestCaseDeleted(testCaseName));
        return this;
    }
}
