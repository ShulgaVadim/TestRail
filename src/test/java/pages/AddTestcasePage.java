package pages;

import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Testcase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AddTestcasePage extends BasePage{

    private static final By ADD_TESTCASE_BUTTON = By.id("accept");
    String allertMessage = "//div[@id = 'content-inner']/div[1]";

    public AddTestcasePage(WebDriver driver) {
        super(driver);
    }

    @Step("Create new Test сase")
    public AddTestcasePage createNewTestCase(Testcase testCase) {
        new Input(driver, "Title").write(testCase.getTitle());
        new Select(driver, "Section").select(testCase.getSections());
        new Select(driver, "Template").select(testCase.getTemplate());
        new Select(driver, "Type").select(testCase.getType());
        new Select(driver, "Priority").select(testCase.getPriority());
        new Input(driver, "Estimate").write(testCase.getEstimate());
        new Select(driver, "Automation Type").select(testCase.getAutomationType());
        driver.findElement(By.id("refs")).clear();
        driver.findElement(By.id("refs")).sendKeys(testCase.getReferences());
        driver.findElement(By.id("custom_preconds")).clear();
        driver.findElement(By.id("custom_preconds")).sendKeys(testCase.getPreconditions());
        driver.findElement(By.id("custom_steps")).clear();
        driver.findElement(By.id("custom_steps")).sendKeys(testCase.getSteps());
        driver.findElement(By.id("custom_expected")).clear();
        driver.findElement(By.id("custom_expected")).sendKeys(testCase.getExpectedResult());
        return this;
    }

    @Step("Validation that Add Test case Page is opened")
    public AddTestcasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_TESTCASE_BUTTON));
        return this;
    }

    @Step("Click 'Add Test case' Button")
    public TestcasesPage clickAddTestcase(){
        driver.findElement(ADD_TESTCASE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(allertMessage)));
        return new TestcasesPage(driver);
    }
}
