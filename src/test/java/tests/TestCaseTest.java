package tests;

import models.Testcase;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class TestCaseTest extends BaseTest {

    String projectName = faker.country().name();
    String announcement = faker.country().capital();

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
    public void createEditDeleteTestCaseTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        projectSteps
                .createNewProject(projectName, announcement)
                .openProject(projectName);
        testCasesSteps
                .createNewTestCase(testCase)
                .isTestCaseCreated(title)
                .editTestCase(editedTestCase, title)
                .isTestCaseEdited(editTitle, editedExpectedResult)
                .deleteTestCase(editTitle)
                .isTestCaseDeleted(editTitle);
        projectSteps
                .deleteProject(projectName);
    }
}
