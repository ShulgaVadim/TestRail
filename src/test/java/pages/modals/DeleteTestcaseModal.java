package pages.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TestcasesPage;

public class DeleteTestcaseModal {
    WebDriver driver;
    WebDriverWait wait;
    private static final By OK_BUTTON = By.xpath("//div[@id='deleteDialog']//a[contains(text(),'OK')]");
    private static final By CONFIRM_DELETE = By.xpath("//div[@id='deleteDialog']//input[@name='deleteCheckbox']");

    public DeleteTestcaseModal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    @Step("Validate that Delete TestCase window is opened")
    public DeleteTestcaseModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        return this;
    }

    public TestcasesPage delete() {
        driver.findElement(CONFIRM_DELETE).click();
        driver.findElement(OK_BUTTON).click();
        return new TestcasesPage(driver);
    }
}
