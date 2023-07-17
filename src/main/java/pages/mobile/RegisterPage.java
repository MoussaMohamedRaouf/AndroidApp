package pages.mobile;

import functions.miscellaneous.RandomMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class RegisterPage {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;
    private final RandomMethods randomMethods;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/et_register_username")
    private WebElement nameInput;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/et_register_mno")
    private WebElement mobileInput;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/et_register_email")
    private WebElement emailInput;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/et_register_password")
    private WebElement passwordInput;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/btn_register")
    private WebElement registerButton;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/tv_login")
    private WebElement backToLoginButton;
    public void waitForLoad(WebElement element){
        wait.elementToBeClickable(element);
    }

    public RegisterPage(DriverFactory driverFactory, MyAppium myAppium, Waiters wait, RandomMethods randomMethods) {
        this.driver = driverFactory.getDriver();
        this.myAppium = myAppium;
        this.wait = wait;
        this.randomMethods = randomMethods;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void fillData() {
        waitForLoad(registerButton);
        enterName();
        enterMobileNumber();
        enterEmail();
        enterPassword();
    }

    private void enterEmail() {
        waitForLoad(emailInput);
        String email = randomMethods.string(7)+"@yopmail.com";
        myAppium.type(emailInput,email);

    }

    private void enterPassword() {
    waitForLoad(passwordInput);
    String password = randomMethods.string(7);
    myAppium.type(passwordInput, password);

    }

    private void enterMobileNumber() {
        waitForLoad(mobileInput);
        String mobileNumber = randomMethods.cellPhoneNumber();
        myAppium.type(mobileInput, mobileNumber);
    }

    private void enterName() {
        waitForLoad(nameInput);
        String name = randomMethods.lastName();
        myAppium.type(nameInput,name);
    }

    public void submit() {
        waitForLoad(registerButton);
        myAppium.click(registerButton);
    }

    public void returnToLoginPage() {
        waitForLoad(backToLoginButton);
        myAppium.click(backToLoginButton);
    }
}
