package tests;

import models.TestRun;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class TestRunTest extends BaseTest {

    String projectName = faker.name().firstName();
    String announcement = faker.country().capital();

    String name = faker.name().firstName();
    String references = faker.artist().name();
    String description = faker.chuckNorris().fact();

    String editedName = faker.name().firstName();
    String editedReferences = faker.artist().name();
    String editedDescription = faker.chuckNorris().fact();

    TestRun testRun = TestRun.builder()
            .name(name)
            .references(references)
            .description(description)
            .assignTo("Vadim Shulga")
            .build();

    TestRun editedTestRun = TestRun.builder()
            .name(editedName)
            .references(editedReferences)
            .description(editedDescription)
            .assignTo("Me")
            .build();

    @Test
    public void createEditDeleteTestRunTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        projectSteps
                .createNewProject(projectName, announcement)
                .openProject(projectName);
        testRunsSteps
                .createNewTestRun(testRun)
                .isTestRunCreated(name)
                .editTestRun(editedTestRun, name)
                .isTestRunEdited(editedName, editedDescription)
                .deleteTestRun(editedName)
                .isTestRunDeleted(editedName);
        projectSteps
                .deleteProject(projectName);
    }
}