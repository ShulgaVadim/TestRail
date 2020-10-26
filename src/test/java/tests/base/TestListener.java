package tests.base;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    public void onTestStart(ITestResult iTestResult) {
        log.info((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("======================================== FAILED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        takeScreenshot(iTestResult);
    }

    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
        takeScreenshot(iTestResult);
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            return ((TakesScreenshot) context.getAttribute("driver")).getScreenshotAs(OutputType.BYTES);
        } catch (NoSuchSessionException ex) {
            return null;
        } catch (IllegalStateException ex) {
            return null;
        }
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
