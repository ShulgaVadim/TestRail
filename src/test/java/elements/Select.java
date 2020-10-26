package elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Select {

    WebDriver driver;
    String label;
    String labelLocator = "//label[contains(text(),'%s')]/ancestor::td/div";
    String optionLocator = "//tbody//li[contains(.,'%s')]";

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    @Step("Select '{option}'" )
    public void select(String option) {
        log.info(String.format("Select option %s in %s", option, label));
        driver.findElement(By.xpath(String.format(labelLocator, label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();
    }
}
