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

    @Step("Create new Testcase: '{testcase}'")
    public TestCasesSteps createNewTestCase(Testcase testcase){
        log.info("Create new Testcase " + testcase);
        testcasesPage
                .openPage()
                .isPageOpened()
                .clickAddTestcase();
        addTestcasePage
                .createNewTestCase(testcase)
                .clickAddTestcase();
        return this;
    }

    @Step("Validation that TestCase '{testCaseName}' is created")
    public TestCasesSteps isTestCaseCreated(String testCaseName) {
        log.info("Validation that TestCase "  + testCaseName + " is created");
        testcasesPage
                .openPage()
                .isPageOpened();
        assertTrue(testcasesPage.isTestCaseCreated(testCaseName));
        return this;
    }

    @Step("Delete TestCase: '{testCaseName}'")
    public TestCasesSteps deleteTestCase(String testCaseName){
        log.info("Deleting TestCase: "  + testCaseName);
        testcasesPage
                .openPage()
                .isPageOpened()
                .checkTheCheckbox(testCaseName)
                .clickDeleteTestcase()
                .isModalOpened()
                .delete();
        return this;
    }

    @Step("Validation that TestCase '{testCaseName}' is deleted")
    public TestCasesSteps isTestCaseDeleted(String testCaseName) {
        log.info("Validation that testCase " + testCaseName + " is deleted");
        testcasesPage
                .openPage()
                .isPageOpened();
        assertTrue(testcasesPage.isTestCaseDeleted(testCaseName));
        return this;
    }

    @Step("Delete TestCase: '{testCaseName}'")
    public TestCasesSteps editTestCase(Testcase editTestCase, String testCaseName){
        log.info("Editing TestCase: "  + editTestCase);
        testcasesPage
                .openPage()
                .isPageOpened()
                .openEditing(testCaseName);
        addTestcasePage
                .createNewTestCase(editTestCase)
                .clickAddTestcase();
        return this;
    }

    @Step("Validate that TestCase '{editCaseName}' is edited")
    public TestCasesSteps isTestCaseEdited(String editedTestCaseName, String editedExpectedResult) {
        testcasesPage
                .isTestCaseEdited(editedTestCaseName, editedExpectedResult);
        return this;
    }
}
