package pages;

import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import models.TestRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.modals.DeleteTestRunModal;

public class AddTestRunPage extends BasePage {

    private static final By ADD_TESTRUN_BUTTON = By.id("accept");
    private static final By DELETE_TESTRUN_BUTTON = By.cssSelector(".button-delete");
    String allertMessage = "//div[@id = 'content-inner']/div[1]";

    public AddTestRunPage(WebDriver driver) {
        super(driver);
    }

    @Step("Validation that Add Test Run Page is opened")
    public AddTestRunPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TESTRUN_BUTTON));
        return this;
    }

    @Step("Create new Test сase")
    public AddTestRunPage createNewTestRun(TestRun testrun) {
        new Input(driver, "Name").write(testrun.getName());
        new Select(driver, "Milestone").select(testrun.getMilestone());
        new Select(driver, "Assign To").select(testrun.getAssignTo());
        driver.findElement(By.id("refs")).clear();
        driver.findElement(By.id("refs")).sendKeys(testrun.getReferences());
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys(testrun.getDescription());
        driver.findElement(By.id("includeAll")).click();
        return this;
    }

    @Step("Click 'Add Test Run' Button")
    public TestRunsPage clickAddTestRun() {
        driver.findElement(ADD_TESTRUN_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allertMessage)));
        return new TestRunsPage(driver);
    }

    @Step("Click 'Delete Test Run' Button")
    public DeleteTestRunModal clickDeleteTestRun(){
        driver.findElement(DELETE_TESTRUN_BUTTON).click();
        return new DeleteTestRunModal(driver);
    }
}

