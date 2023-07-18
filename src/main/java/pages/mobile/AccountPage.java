package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class AccountPage {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;

    @AndroidFindBy(id = "android.widget.ImageButton")
    private WebElement returnButton;

    @AndroidFindBy(id = "btn_mydetails")
    private WebElement myDetailsMenu;

    @AndroidFindBy(id = "btn_myorders")
    private WebElement myOrdersMenu;

    @AndroidFindBy(id = "btn_product_return_request")
    private WebElement productReturnsMenu;

    @AndroidFindBy(id = "btn_trackmyorders")
    private WebElement trackMyOrdersMenu;

    @AndroidFindBy(id = "btn_chg_password")
    private WebElement changePasswordMenu;

    @AndroidFindBy(id = "btn_logout")
    private WebElement logoutMenu;

    public AccountPage(DriverFactory driverFactory, MyAppium myAppium, Waiters wait){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.wait       = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void clickReturnButton(){
        wait.elementToBeClickable(returnButton);
        myAppium.click(returnButton);
    }

    public void waitForLoad(){
        wait.elementToBeClickable(logoutMenu);
    }


    public boolean isProfileBtnDisplayed(){
        waitForLoad();
        return myAppium.isElementDisplayed(logoutMenu);
    }

    public void logout() {
        waitForLoad();
        myAppium.click(logoutMenu);

    }
}
