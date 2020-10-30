package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import pages.NewProjectPage;
import pages.ProjectsPage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class ProjectSteps {

    ProjectsPage projectsPage;
    NewProjectPage newProjectPage;

    public ProjectSteps(WebDriver driver) {
        projectsPage = new ProjectsPage(driver);
        newProjectPage = new NewProjectPage(driver);
    }

    @Step("Create new project: '{projectName}'")
    public ProjectSteps createNewProject(String projectName, String announcement) {
        projectsPage
                .openPage()
                .isPageOpened()
                .clickAddProject();
        newProjectPage
                .createProject(projectName, announcement);
        return this;
    }

    @Step("Validation that Project '{projectName}' is created")
    public ProjectSteps isProjectCreated(String projectName) {
        log.info("Validation that Project " + projectName + " is created");
        projectsPage
                .isPageOpened();
        assertTrue(projectsPage.isProjectCreated(projectName));
        return this;
    }

    @Step("Delete project: '{projectName}'")
    public ProjectSteps deleteProject(String projectName) {
        projectsPage
                .openPage()
                .isPageOpened()
                .clickDeleteButton(projectName)
                .isModalOpened()
                .delete();
        return this;
    }

    @Step("Validation that Project '{projectName}' is deleted")
    public ProjectSteps isProjectDeleted(String projectName) {
        log.info("Validation that Project " + projectName + " is deleted");
        projectsPage
                .isPageOpened();
        assertTrue(projectsPage.isProjectDeleted(projectName));
        return this;
    }

    @Step("Open Projects Page '{projectName}'")
    public ProjectSteps openProject(String projectName) {
        projectsPage
                .openProject(projectName);
        return this;
    }
}
