package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import pages.CommonInstance;
import pages.mobile.*;

public class ForgetPasswordSteps {


    private final LoginPage loginPage;
    private final ForgetPasswordPage forgetPasswordPage;
    private final SoftAssert softAssert;
    private Integer verifCode;
    public ForgetPasswordSteps(
            ForgetPasswordPage forgetPasswordPage,
            LoginPage loginPage,
            CommonInstance instance
    ){
        this.loginPage                      = loginPage;
        this.forgetPasswordPage             = forgetPasswordPage;
        this.softAssert                     = instance.getSoftAssert();
    }

    @When("I access the forgetPasswordPage and enter the email {string} and submit")
    public void iAccessTheForgetPasswordPageAndEnterTheEmailAndSubmit(String email) {
        loginPage.moveToResetPage();
        forgetPasswordPage.enterEmail(email);
        forgetPasswordPage.validateSubmit();
    }

    @And("I navigate to navigator and get the code")
    public void iNavigateToNavigatorAndGetTheCode() {
         this.verifCode= forgetPasswordPage.navigateToYopmail();
    }

    @And("I get back to app and change password")
    public void iGetBackToAppAndChangePassword() {
        forgetPasswordPage.navigateBackToApp(this.verifCode);
    }

    @Then("I should get an error")
    public void iShouldGetAnError() {
        softAssert.assertTrue(forgetPasswordPage.isErrorMessageDisplayed(),"Error message was not displayed");
    }
}
