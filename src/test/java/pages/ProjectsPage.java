package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.modals.DeleteProjectModal;

import java.util.List;

@Log4j2
public class ProjectsPage extends BasePage {

    public static final String PROJECTS_URL = URL + "/index.php?/admin/projects/overview";
    public static final By ADD_PROJECT_BUTTON = By.xpath("//a[contains(text(),'Add Project')]");
    public static final By EDIT_PROJECT_BUTTON = By.xpath("//tr[contains(@class, 'hoverSensitive')]//td[1]/a");
    public static final String DELETE_PROJECT_BUTTON = "//tr[contains(@class, 'hoverSensitive')]//a[text()='%s']//ancestor::tbody//*[contains(@class,'icon-small-delete')]";
    public static final By PROJECTS_XPATH = By.xpath("//table[@class='grid']//td[1]/a");
    String projectLocator = "//tr[contains(@class, 'hoverSensitive')]//a[text()='%s']";
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

    @Step("Click Add Project button")
    public NewProjectPage clickAddProject() {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        return new NewProjectPage(driver);
    }

    @Step("Validate that Project '{projectName}' is created")
    public boolean isProjectCreated(String projectName) {
        boolean isCreated = false;
        if (
                driver.findElement(By.xpath(String.format(projectLocator, projectName))).isDisplayed()) {
            isCreated = true;
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
        boolean isDeleted = true;
        projects = driver.findElements(PROJECTS_XPATH);

        log.info("Validate that project " + projectName + "is deleted");
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getText().equals(projectName)) {
                isDeleted = false;
            }
        }
        return isDeleted;
    }

}


