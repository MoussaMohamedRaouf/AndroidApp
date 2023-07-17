package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;
import pages.CommonInstance;
import pages.mobile.*;

public class LoginSteps {
    private final LoginPage loginPage;
    private final HomePage homePage;
    private final TutorialPage tutorialPage;
    private final AccountPage accountPage;
    private final SoftAssert softAssert;

    public LoginSteps(
            TutorialPage tutorialPage,
            AccountPage accountPage,
            HomePage homePage,
            LoginPage loginPage,
            CommonInstance instance
    ){
        this.loginPage                      = loginPage;
        this.accountPage                    = accountPage;
        this.homePage                       = homePage;
        this.tutorialPage                   = tutorialPage;
        this.softAssert                     = instance.getSoftAssert();
    }
    @Given("I access the loginPage from the navigation bar")
    public void iAccessTheLoginPageFromTheNavigationBar() {
        this.homePage.accessLoginPage();
    }

    @Then("I should be logged")
    public void iShouldBeLogged() {
        softAssert.assertTrue(this.accountPage.isProfileBtnDisplayed(),"Is the user log in : ");
    }

    @And("I skip tutorial")
    public void iSkipTutorial() {
    tutorialPage.skipTutorial();
    }

    @And("I log out")
    public void iLogOut() {
        accountPage.logout();
    }

    @Then("I should be logged out")
    public void iShouldBeLoggedOut() {
        softAssert.assertTrue(this.loginPage.isloginBtnDisplayed(),"Is the user log in : ");

    }


    @And("I access the registerPage from the loginPage")
    public void iAccessTheRegisterPageFromTheLoginPage() {
        loginPage.moveToRegisterPage();
    }


    @When("I log with email and the password")
    public void iLogWithEmailAndThePassword() {
    }

    @When("I log with the email {string} and the password {string}")
    public void iLogWithTheEmailAndThePassword(String email, String password) {
        loginPage.login(email, password);

    }


}
