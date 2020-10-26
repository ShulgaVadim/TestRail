package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProjectTest extends BaseTest {

    String name = faker.leagueOfLegends().champion();
    String announcement = faker.leagueOfLegends().location();

    @Test
    public void createProjectTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        projectSteps
                .createNewProject(name, announcement)
                .isProjectCreated(name);
    }

    @Test
    public void deleteProjectTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        projectSteps
                .deleteProject(name)
                .isProjectDeleted(name);
    }
}
