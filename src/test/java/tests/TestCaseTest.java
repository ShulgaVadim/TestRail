package tests;

import models.Testcase;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class TestCaseTest extends BaseTest {

    String title = faker.name().title();
    String references = faker.address().streetAddress();
    String preconditions = faker.name().fullName();
    String steps = faker.address().streetAddress();
    String expectedResult = faker.ancient().god();

    String editTitle = faker.name().title();
    String editedReferences = faker.address().streetAddress();
    String editedPreconditions = faker.name().fullName();
    String editedSteps = faker.address().streetAddress();
    String editedExpectedResult = faker.ancient().god();

    Testcase testCase = Testcase.builder()
            .title(title)
            .sections("Test Cases")
            .template("Test Case (Text)")
            .type("Functional")
            .priority("High")
            .estimate("25")
            .references(references)
            .preconditions(preconditions)
            .automationType("None")
            .steps(steps)
            .expectedResult(expectedResult)
            .build();

    Testcase editedTestCase = Testcase.builder()
            .title(editTitle)
            .sections("Test Cases")
            .template("Test Case (Text)")
            .type("Destructive")
            .priority("Medium")
            .estimate("48")
            .references(editedReferences)
            .preconditions(editedPreconditions)
            .automationType("None")
            .steps(editedSteps)
            .expectedResult(editedExpectedResult)
            .build();


    @Test
    public void createTestCaseTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        testCasesSteps
                .createNewTestCase(testCase)
                .isTestCaseCreated(title);
    }

    @Test(priority = 1)
    public void editTestCaseTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        testCasesSteps
                .editTestCase(editedTestCase, title)
                .isTestCaseEdited(editTitle, editedExpectedResult);
    }

    @Test(priority = 2)
    public void deleteTestCaseTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        testCasesSteps
                .deleteTestCase(editTitle)
                .isTestCaseDeleted(editTitle);
    }
}
