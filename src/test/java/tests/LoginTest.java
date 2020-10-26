package tests;


import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class LoginTest extends BaseTest {

    @Test (description = "Login with correct data", retryAnalyzer = Retry.class)
    public void correctLogin() {
        loginSteps
                .login(EMAIL, PASSWORD);
    }
}
