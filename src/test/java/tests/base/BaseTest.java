package tests.base;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import steps.ProjectSteps;
import utils.CapabilitiesGenerator;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    public LoginSteps loginSteps;
    public ProjectSteps projectSteps;
    public Faker faker = new Faker();
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) {

        try {
            driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        } catch (SessionNotCreatedException ex) {
            Assert.fail("Браузер не был открыт. Проверьте, что используется корректная версия драйвера");
            log.fatal(ex.getLocalizedMessage());
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginSteps = new LoginSteps(driver);
        projectSteps = new ProjectSteps(driver);
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
