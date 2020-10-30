package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.modals.DeleteProjectModal;

import java.util.List;

@Log4j2
public class ProjectsPage extends BasePage {

    public static final String PROJECTS_URL = URL + "/index.php?/admin/projects/overview";
    public static final By ADD_PROJECT_BUTTON = By.xpath("//a[contains(text(),'Add Project')]");
    public static final String DELETE_PROJECT_BUTTON = "//a[text()='%s']//ancestor::tr//td[3]//div";
    public static final By PROJECTS_XPATH = By.xpath("//table[@class='grid']//td[1]/a");
    String projectAreaLocator = "//a[text()='%s']/parent::td";
    String projectLocator = "//a[text()='%s']/parent::td//span/a";
    List<WebElement> projects;

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Projects Page")
    public ProjectsPage openPage() {
        log.info("Opening Projects page: " + PROJECTS_URL);
        driver.get(PROJECTS_URL);
        isPageOpened();
        return this;
    }

    @Step("Validate that Projects Page is opened")
    public ProjectsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_PROJECT_BUTTON));
        return this;
    }

    @Step("Open Project '{projectName}'")
    public ProjectsPage openProject(String projectName) {
        Actions builder = new Actions(driver);
        WebElement testRunArea = driver.findElement(By.xpath(String.format(projectAreaLocator, projectName)));
        builder.moveToElement(testRunArea).build().perform();
        driver.findElement(By.xpath(String.format(projectLocator, projectName))).click();
        return new ProjectsPage(driver);
    }

    @Step("Click Add Project button")
    public NewProjectPage clickAddProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new NewProjectPage(driver);
    }

    @Step("Validate that Project '{projectName}' is created")
    public boolean isProjectCreated(String projectName) {
        boolean isCreated = false;
        projects = driver.findElements(PROJECTS_XPATH);
        log.info("Validate that Project " + projectName + "is deleted");
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getText().equals(projectName)) {
                isCreated = true;
            }
        }
        return isCreated;
    }

    @Step("Click Delete button")
    public DeleteProjectModal clickDeleteButton(String projectName) {
        driver.findElement(By.xpath(String.format(DELETE_PROJECT_BUTTON, projectName))).click();
        return new DeleteProjectModal(driver);
    }

    @Step("Validation that project '{projectName}' is deleted")
    public boolean isProjectDeleted(String projectName) {
        log.info("Validate that project " + projectName + " is deleted");
        boolean isDeleted = true;
        projects = driver.findElements(PROJECTS_XPATH);
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getText().equals(projectName)) {
                isDeleted = false;
            }
        }
        return isDeleted;
    }
}


