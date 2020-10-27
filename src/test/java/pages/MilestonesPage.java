package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class MilestonesPage extends BasePage {

    public static final String MILESTONES_URL = URL + "/index.php?/milestones/overview/52";
    private static final By ADD_MILESTONES_BUTTON = By.id("navigation-milestones-add");
    public static final By MILESTONES_XPATH = By.cssSelector(".summary-title");
    String editMilestoneLocator = "//a[text()='%s']/parent::div//span//span[2]/following-sibling::a/div";
    String milestoneNameLocator = "//div[@class='summary-title summary-title-compact text-ppp']/a[text()='%s']";
    private static final By EXPECTED_NAME = By.cssSelector(".page_title");
    private static final By EXPECTED_DESCRIPTION = By.xpath ("//div[@class='markdown']");
    List<WebElement> milestones;

    public MilestonesPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open Milestones Page")
    public MilestonesPage openPage() {
        log.info("Open Milestones page: " + MILESTONES_URL);
        driver.get(MILESTONES_URL);
        isPageOpened();
        return this;
    }

    @Step("Validation that Milestones Page is opened")
    public MilestonesPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ADD_MILESTONES_BUTTON));
        return this;
    }

    @Step("Click 'Add Milestone' Button")
    public AddMilestonePage clickAddMilestone() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MILESTONES_BUTTON)).click();
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
        Actions builder = new Actions(driver);
        WebElement milestoneArea = driver.findElement(By.xpath(String.format(milestoneNameLocator, milestoneName)));
        builder.moveToElement(milestoneArea).build().perform();
        WebElement editButton = driver.findElement(By.xpath(String.format(editMilestoneLocator, milestoneName)));
        editButton.click();
        return new AddMilestonePage(driver);
    }

    @Step("Validation that Milestone'{editedMilestoneName}' is edited")
    public MilestonesPage isMilestoneEdited(String editedMilestoneName, String editDescription)  {
        log.info("Validation that Milestone " + editedMilestoneName + "is edited");
        driver.findElement(By.xpath(String.format(milestoneNameLocator, editedMilestoneName))).click();
        String expectedName = driver.findElement(EXPECTED_NAME).getText();
        String expectedDescription = driver.findElement(EXPECTED_DESCRIPTION).getText();
        assertEquals(editedMilestoneName, expectedName);
        assertEquals(editDescription, expectedDescription);
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