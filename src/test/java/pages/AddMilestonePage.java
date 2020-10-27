package pages;

import elements.Input;
import io.qameta.allure.Step;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.modals.DeleteMilestoneModal;

public class AddMilestonePage extends BasePage{

    private static final By ADD_MILESTONE_BUTTON = By.id("accept");
    private static final By DELETE_MILESTONE_BUTTON = By.xpath("//div[@class='sidebar-action']//span");
    String allertMessage = "//div[@id = 'content-inner']/div[1]";

    public AddMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Step("Create new Milestone")
    public AddMilestonePage createNewMilestone(Milestone milestone) {
        new Input(driver, "Name").write(milestone.getName());
        new Input(driver, "Start Date").write(milestone.getStartDate());
        new Input(driver, "End Date").write(milestone.getEndDate());
        driver.findElement(By.id("reference")).clear();
        driver.findElement(By.id("reference")).sendKeys(milestone.getReferences());
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys(milestone.getDescription());
        return this;
    }

    @Step("Validation that Milestone Page is opened")
    public AddMilestonePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_MILESTONE_BUTTON));
        return this;
    }

    @Step("Click 'Add Milestone' Button")
    public MilestonesPage clickAddMilestone(){
        driver.findElement(ADD_MILESTONE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allertMessage)));
        return new MilestonesPage(driver);
    }

    @Step("Click 'Delete Milestone' Button")
    public DeleteMilestoneModal clickDeleteMilestone(){
        driver.findElement(DELETE_MILESTONE_BUTTON).click();
        return new DeleteMilestoneModal(driver);
    }
}
