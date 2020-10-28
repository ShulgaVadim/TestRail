package pages.modals;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.TestRunsPage;

public class DeleteTestRunModal {
    WebDriver driver;
    WebDriverWait wait;

    public DeleteTestRunModal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    private static final By OK_BUTTON = By.xpath("//div[@id='deleteDialog']//a[contains(text(),'OK')]");
    private static final By CONFIRM_DELETE = By.xpath("//div[@id='deleteDialog']//input[@name='deleteCheckbox']");

    @Step("Validate that Delete Test Run window is opened")
    public DeleteTestRunModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        return this;
    }

    public TestRunsPage delete() {
        driver.findElement(CONFIRM_DELETE).click();
        driver.findElement(OK_BUTTON).click();
        return new TestRunsPage(driver);
    }
}
