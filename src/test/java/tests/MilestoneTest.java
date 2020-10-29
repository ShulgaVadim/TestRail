package tests;

import models.Milestone;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class MilestoneTest extends BaseTest {
    String projectName = faker.leagueOfLegends().champion();
    String announcement = faker.leagueOfLegends().location();

    String name = faker.name().name();
    String references = faker.artist().name();
    String description = faker.chuckNorris().fact();

    String editedName = faker.name().username();
    String editedReferences = faker.artist().name();
    String editedDescription = faker.chuckNorris().fact();

    Milestone milestone = Milestone.builder()
            .name(name)
            .references(references)
            .description(description)
            .startDate("12/01/2020")
            .endDate("12/31/2020")
            .build();

    Milestone editedMilestone = Milestone.builder()
            .name(editedName)
            .references(editedReferences)
            .description(editedDescription)
            .startDate("12/10/2020")
            .endDate("01/31/2021")
            .build();

    @Test
    public void createEditDeleteMilestoneTest() {
        loginSteps
                .login(EMAIL, PASSWORD);
        projectSteps
                .createNewProject(projectName, announcement)
                .openProject(projectName);
        milestonesSteps
                .createNewMilestone(milestone)
                .isMilestoneCreated(name)
                .editMilestone(editedMilestone, name)
                .isMilestoneEdited(editedName, editedDescription)
                .deleteMilestone(editedName)
                .isMilestoneDeleted(editedName);
        projectSteps
                .deleteProject(projectName);

    }
}
