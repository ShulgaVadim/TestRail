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
import steps.MilestonesSteps;
import steps.ProjectSteps;
import steps.TestCasesSteps;
import utils.CapabilitiesGenerator;

import java.util.concurrent.TimeUnit;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    public static final String EMAIL = System.getenv("email");
    public static final String PASSWORD = System.getenv("password");

    public LoginSteps loginSteps;
    public ProjectSteps projectSteps;
    public TestCasesSteps testCasesSteps;
    public MilestonesSteps milestonesSteps;
    public Faker faker = new Faker();
    WebDriver driver;

    @BeforeMethod
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
        testCasesSteps = new TestCasesSteps(driver);
        milestonesSteps = new MilestonesSteps(driver);
        context.setAttribute("driver", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
