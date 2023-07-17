package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.EnvironmentVariable;


public class LoginPage {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;

    @AndroidFindBy(id = "et_login_username")
    private WebElement emailInput;

    @AndroidFindBy(id = "et_login_password")
    private WebElement passwordInput;
    @AndroidFindBy(id = "btn_login")
    private WebElement loginButton;

    @AndroidFindBy(id = "tv_register")
    private WebElement registerButton;
    @AndroidFindBy(id = "tv_reset_password")
    private WebElement resetButton;

    public LoginPage(DriverFactory driverFactory, MyAppium myAppium, Waiters wait){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void waitForLoad(WebElement element){
        wait.elementToBeClickable(element);
    }

    public void login(String email,String password) {
        waitForLoad(emailInput);
        enterEmail(email);
        enterPassword(password);
        validateConnexion();
    }
    public void enterEmail(String email){
        String userEmail = EnvironmentVariable.get(email);
        waitForLoad(emailInput);
        myAppium.type(emailInput, userEmail);
    }
    public void enterPassword(String password){
        String userPassword = EnvironmentVariable.get(password);
        waitForLoad(passwordInput);
        myAppium.type(passwordInput, userPassword);
    }
    public void validateConnexion(){
        waitForLoad(loginButton);
        myAppium.click(loginButton);
    }

    public boolean isloginBtnDisplayed() {
        waitForLoad(loginButton);
        return myAppium.isElementDisplayed(loginButton);
    }
    public void moveToRegisterPage() {
        waitForLoad(registerButton);
        myAppium.click(registerButton);
    }
    public void moveToResetPage() {
        waitForLoad(resetButton);
        myAppium.click(resetButton);
    }
}
