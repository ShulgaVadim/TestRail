package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import pages.AddMilestonePage;
import pages.MilestonesPage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class MilestonesSteps {

    MilestonesPage milestonesPage;
    AddMilestonePage addMilestonePage;

    public MilestonesSteps(WebDriver driver) {
        milestonesPage = new MilestonesPage(driver);
        addMilestonePage = new AddMilestonePage(driver);
    }

    @Step("Create new Milestone: '{milestone}'")
    public MilestonesSteps createNewMilestone(Milestone milestone) {
        log.info("Create new Milestone " + milestone);
        milestonesPage
                .openPage()
                .isPageOpened()
                .clickAddMilestone();
        addMilestonePage
                .isPageOpened()
                .createNewMilestone(milestone)
                .clickAddMilestone();
        return this;
    }

    @Step("Validation that Milestone '{milestoneName}' is created")
    public MilestonesSteps isMilestoneCreated(String milestoneName) {
        log.info("Validation that Milestone " + milestoneName + " is created");
        milestonesPage
                .openPage()
                .isPageOpened();
        assertTrue(milestonesPage.isMilestoneCreated(milestoneName));
        return this;
    }

    @Step("Edit Milestone: '{milestoneName}'")
    public MilestonesSteps editMilestone(Milestone editedMilestone, String milestoneName) {
        log.info("Edit Milestone: " + milestoneName);
        milestonesPage
                .clickEditMilestone(milestoneName);
        addMilestonePage
                .createNewMilestone(editedMilestone)
                .clickAddMilestone();
        return this;
    }

    @Step("Validation that Milestone '{editedMilestoneName}' is edited")
    public MilestonesSteps isMilestoneEdited(String editedMilestoneName, String editedDescription) {
        log.info("Validation that Milestone " + editedMilestoneName + " is edited");
        milestonesPage
                .isMilestoneEdited(editedMilestoneName, editedDescription);
        return this;
    }

    @Step("Delete Milestone: '{editMilestoneName}'")
    public MilestonesSteps deleteMilestone(String editMilestoneName) {
        log.info("Delete Milestone: " + editMilestoneName);
        milestonesPage
                .openPage()
                .isPageOpened()
                .clickEditMilestone(editMilestoneName);
        addMilestonePage
                .clickDeleteMilestone()
                .isModalOpened()
                .delete();
        return this;
    }

    @Step("Validation that Milestone '{editMilestoneName}' is deleted")
    public MilestonesSteps isMilestoneDeleted(String editMilestoneName) {
        log.info("Validation that Milestone " + editMilestoneName + " is deleted");
        milestonesPage
                .openPage()
                .isPageOpened();
        assertTrue(milestonesPage.isMilestoneDeleted(editMilestoneName));
        return this;
    }
}
