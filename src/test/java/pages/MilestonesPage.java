package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class MilestonesPage extends BasePage {

    public static final By EDIT = By.cssSelector(".button-edit");
    private static final By ADD_MILESTONE_BUTTON = By.id("sidebar-milestones-add");
    public static final By MILESTONES_XPATH = By.cssSelector(".summary-title");
    String milestoneNameLocator = "//a[text()='%s']/parent::div/a";
    private static final By EXPECTED_NAME = By.cssSelector(".page_title");
    private static final By EXPECTED_DESCRIPTION = By.xpath("//div[@class='markdown']");
    List<WebElement> milestones;

    public MilestonesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Milestones Page is opened")
    public MilestonesPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_MILESTONE_BUTTON));
        return this;
    }

    @Step("Click 'Add Milestone' Button")
    public AddMilestonePage clickAddMilestone() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MILESTONE_BUTTON)).click();
        return new AddMilestonePage(driver);
    }

    @Step("Validation that Milestone '{milestoneName}' is created")
    public boolean isMilestoneCreated(String milestoneName) {
        boolean isCreated = false;
        if (
                driver.findElement(By.xpath(String.format(milestoneNameLocator, milestoneName))).isDisplayed()) {
            isCreated = true;
        }
        return isCreated;
    }

    @Step("Click 'Edit Milestone' Button")
    public AddMilestonePage clickEditMilestone(String milestoneName) {
        driver.findElement(By.xpath(String.format(milestoneNameLocator, milestoneName))).click();
        clickEdit();
        return new AddMilestonePage(driver);
    }

    @Step("Validation that Milestone'{editedMilestoneName}' is edited")
    public MilestonesPage isMilestoneEdited(String editedMilestoneName, String editDescription) {
        log.info("Validation that Milestone " + editedMilestoneName + "is edited");
        String expectedName = driver.findElement(EXPECTED_NAME).getText();
        String expectedDescription = driver.findElement(EXPECTED_DESCRIPTION).getText();
        assertEquals(editedMilestoneName, expectedName);
        assertEquals(editDescription, expectedDescription);
        return this;
    }

    @Step("Click 'Edit' Button")
    public MilestonesPage clickEdit() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(EDIT)).click();
        return this;
    }

    @Step("Validation that Milestone '{editMilestoneName}' is deleted")
    public boolean isMilestoneDeleted(String editMilestoneName) {
        boolean isDeleted = true;
        milestones = driver.findElements(MILESTONES_XPATH);
        log.info("Validate that Milestone " + editMilestoneName + "is deleted");
        for (int i = 0; i < milestones.size(); i++) {
            if (milestones.get(i).getText().equals(editMilestoneName)) {
                isDeleted = false;
            }
        }
        return isDeleted;
    }
}
