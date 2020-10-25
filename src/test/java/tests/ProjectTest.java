package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ProjectTest extends BaseTest {

    String name = faker.leagueOfLegends().champion();
    String announcement = faker.leagueOfLegends().location();

    @Test
    public void createProjectTest() {
        loginSteps
                .login("stiffler88@bk.ru", "St5777758")
                .isDashboardPageOpened();
        projectSteps
                .createNewProject(name, announcement)
                .isProjectCreated(name);
    }

    @Test
    public void deleteProjectTest() {
        loginSteps
                .login("stiffler88@bk.ru", "St5777758")
                .isDashboardPageOpened();
        projectSteps
                .deleteProject(name)
                .isProjectDeleted(name);
    }
}
