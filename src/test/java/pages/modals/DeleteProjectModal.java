package pages.modals;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ProjectsPage;

@Log4j2
public class DeleteProjectModal {
    WebDriver driver;
    WebDriverWait wait;

    public DeleteProjectModal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    private static final By OK_BUTTON = By.xpath("//div[@id='deleteDialog']//a[contains(text(),'OK')]");
    private static final By CONFIRM_DELETE = By.xpath("//div[@id='deleteDialog']//input[@name='deleteCheckbox']");

    @Step("Validate that Delete Project window is opened")
    public DeleteProjectModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        return this;
    }

    public ProjectsPage delete() {
        driver.findElement(CONFIRM_DELETE).click();
        driver.findElement(OK_BUTTON).click();
        return new ProjectsPage(driver);
    }
}
