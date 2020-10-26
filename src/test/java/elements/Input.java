package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input {
    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    WebDriver driver;
    String label;
    String locator = "//label[contains(text(),'%s')]/following-sibling::input";

    public void write(String text) {
        driver.findElement(By.xpath(String.format(locator, label))).clear();
        driver.findElement(By.xpath(String.format(locator, label))).sendKeys(text);
    }
}
