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

    public DeleteTestcaseModal(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    @Step("Validate that Delete Test сase window is opened")
    public DeleteTestcaseModal isModalOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(OK_BUTTON));
        return this;
    }

    public TestcasesPage delete() {
        driver.findElement(OK_BUTTON).click();
        return new TestcasesPage(driver);
    }
}
