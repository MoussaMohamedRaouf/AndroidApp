package pages.mobile;

import com.google.common.collect.ImmutableMap;
import functions.miscellaneous.RandomMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;
import utils.EnvironmentVariable;
import java.util.Scanner;
public class ForgetPasswordPage {
    private final AppiumDriver driver;
    private final RandomMethods randomMethods;
    private final MyAppium myAppium;
    private final Waiters wait;
    @AndroidFindBy(id = "et_pr_email")
    private WebElement emailInput;
    @AndroidFindBy(id = "btn_pr_reset")
    private WebElement submitButton;
    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/snackbar_text")
    private WebElement errorAlert;

    @AndroidFindBy(id = "et_pr_code")
    private WebElement codeInput;
    @AndroidFindBy(id = "et_pr_password")
    private WebElement newPasswordInput;
    @AndroidFindBy(id = "btn_pr_reset")
    private WebElement submitResetButton;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")
    private WebElement inputYopmail_CHROME;

    @AndroidFindBy(xpath = "//*[@resource-id='com.android.chrome:id/search_box_text'] | //*[@resource-id='com.android.chrome:id/url_bar']")
    private WebElement navBar_CHROME;
 @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]/android.widget.Button")
    private WebElement lunchSearch_CHROME;
 @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]/android.widget.Button")
    private WebElement mail_CHROME;
 @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.widget.TextView")
    private WebElement text_CHROME;



    public ForgetPasswordPage(DriverFactory driverFactory, MyAppium myAppium,Waiters wait,RandomMethods randomMethods){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.randomMethods   = randomMethods;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void waitForLoad(WebElement element){
        wait.elementToBeClickable(element);
    }
    public void enterEmail(String email){
        String userEmail = EnvironmentVariable.get(email);
        waitForLoad(emailInput);
        myAppium.type(emailInput, userEmail);
    }

    public void validateSubmit(){
        waitForLoad(submitButton);
        myAppium.click(submitButton);
        myAppium.sleep(5000);
    }

    public int navigateToYopmail(){
        ((InteractsWithApps)driver).activateApp("com.android.chrome");
        myAppium.click(navBar_CHROME);
        myAppium.type(navBar_CHROME,EnvironmentVariable.get("YOPMAILURL"));
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
        myAppium.type(inputYopmail_CHROME,EnvironmentVariable.get("RESET_EMAIL"));
        myAppium.click(lunchSearch_CHROME);
        myAppium.click(mail_CHROME);
        String text = text_CHROME.getText();
        ((InteractsWithApps) driver).terminateApp("com.android.chrome");
        return new Scanner(text).useDelimiter("\\D+").nextInt();
    }
    public void navigateBackToApp(Integer verifCode) {
        String newPassword = randomMethods.string(6);
        EnvironmentVariable.set("RESET_PASSWORD",newPassword);
        ((InteractsWithApps)driver).activateApp("com.studiobluelime.ecommerceapp");
        myAppium.type(codeInput,verifCode.toString());
        myAppium.type(newPasswordInput,newPassword);
        myAppium.click(submitResetButton);
    }
    public boolean isErrorMessageDisplayed() {
        return myAppium.isElementDisplayed(errorAlert);
    }
}
