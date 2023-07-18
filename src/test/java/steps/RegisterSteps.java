package steps;

import io.cucumber.java.en.When;
import pages.mobile.*;

public class RegisterSteps {
    private final RegisterPage registerPage;


    public RegisterSteps(
            RegisterPage registerPage
    ){
        this.registerPage = registerPage;
    }



    @When("I fill data and submit")
    public void iFillDataAndSubmit() {
        registerPage.fillData();
        registerPage.submit();
    }

    @When("I get back to login")
    public void iGetBackToLogin() {
        registerPage.returnToLoginPage();
    }
}
