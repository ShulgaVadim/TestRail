package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import org.openqa.selenium.WebDriver;
import pages.AddTestRunPage;
import pages.TestRunsPage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class TestRunsSteps {

    TestRunsPage testRunsPage;
    AddTestRunPage addTestRunPage;

    public TestRunsSteps(WebDriver driver) {
        testRunsPage = new TestRunsPage(driver);
        addTestRunPage = new AddTestRunPage(driver);
    }

    @Step("Create new Test Run: '{testRun}'")
    public TestRunsSteps createNewTestRun(TestRun testRun) {
        log.info("Create new Test Run " + testRun);
        testRunsPage
                .openPage()
                .isPageOpened()
                .clickAddTestRun();
        addTestRunPage
                .isPageOpened()
                .createNewTestRun(testRun)
                .clickAddTestRun();
        return this;
    }

    @Step("Validation that Test Run '{testRunName}' is created")
    public TestRunsSteps isTestRunCreated(String testRunName) {
        log.info("Validation that Test Run " + testRunName + " is created");
        testRunsPage
                .openPage()
                .isPageOpened();
        assertTrue(testRunsPage.isTestRunCreated(testRunName));
        return this;
    }

    @Step("Edit Test Run: '{testRunName}'")
    public TestRunsSteps editTestRun(TestRun editedTestRun, String testRunName) {
        log.info("Edit Test Run: " + testRunName);
        testRunsPage
                .clickEditTestRun(testRunName);
        addTestRunPage
                .createNewTestRun(editedTestRun)
                .clickAddTestRun();
        return this;
    }

    @Step("Validation that Test Run '{editedTestRunName}' is edited")
    public TestRunsSteps isTestRunEdited(String editedTestRunName, String editedDescription) {
        log.info("Validation that Test Run " + editedTestRunName + " is edited");
        testRunsPage
                .isTestRunEdited(editedTestRunName, editedDescription);
        return this;
    }

    @Step("Delete Test Run: '{editedTestRunName}'")
    public TestRunsSteps deleteTestRun(String editedTestRunName) {
        log.info("Delete Test Run: " + editedTestRunName);
        testRunsPage
                .openPage()
                .isPageOpened()
                .clickEditTestRun(editedTestRunName);
        addTestRunPage
                .clickDeleteTestRun()
                .isModalOpened()
                .delete();
        return this;
    }

    @Step("Validation that Test Run '{editedTestRunName}' is deleted")
    public TestRunsSteps isTestRunDeleted(String editedTestRunName) {
        log.info("Validation that Test Run " + editedTestRunName + " is deleted");
        testRunsPage
                .openPage()
                .isPageOpened();
        assertTrue(testRunsPage.isTestRunDeleted(editedTestRunName));
        return this;
    }
}