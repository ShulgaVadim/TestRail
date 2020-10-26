package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    public LoginSteps(WebDriver driver) {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Step("Login with email: '{email}', password: '{password}'")
    public LoginSteps login(String email, String password){
        loginPage
                .openPage()
                .isPageOpened()
                .login(email, password);
        dashboardPage
                .isPageOpened();
        return this;
    }
//
//    @Step("Validation that Dashboard Page is opened")
//    public LoginSteps isDashboardPageOpened() {
//        dashboardPage.isPageOpened();
//        return this;

}
