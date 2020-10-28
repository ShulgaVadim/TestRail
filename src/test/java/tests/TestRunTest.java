package tests;

import models.TestRun;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class TestRunTest extends BaseTest {

    String name = faker.name().name();
    String references = faker.artist().name();
    String description = faker.chuckNorris().fact();

    String editedName = faker.name().username();
    String editedReferences = faker.artist().name();
    String editedDescription = faker.chuckNorris().fact();

    TestRun testRun = TestRun.builder()
            .name(name)
            .references(references)
            .description(description)
            .milestone("Version 1.0")
            .assignTo("Vadim Shulga")
            .build();

    TestRun editedTestRun = TestRun.builder()
            .name(editedName)
            .references(editedReferences)
            .description(editedDescription)
            .milestone("Version 2.0")
            .assignTo("Me")
            .build();

    @Test
    public void createEditDeleteTestRunTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        testRunsSteps
                .createNewTestRun(testRun)
                .isTestRunCreated(name)
                .editTestRun(editedTestRun, name)
                .isTestRunEdited(editedName, editedDescription)
                .deleteTestRun(editedName)
                .isTestRunDeleted(editedName);
    }
}