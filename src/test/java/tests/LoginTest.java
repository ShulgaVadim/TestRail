package tests;


import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class LoginTest extends BaseTest {

    @Test (description = "Login with correct data", retryAnalyzer = Retry.class)
    public void correctLogin() {
        loginSteps
//                .login(System.getenv("email"), System.getenv("password"))
                .login("stiffler88@bk.ru", "St5777758")
                .isDashboardPageOpened();
    }
}
