package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class NavigationBarPopin {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;

    @AndroidFindBy(id = "titleTextView")
    private WebElement burgerMenu;

    @AndroidFindBy(id = "inviteFriendButton")
    private WebElement friend_Btn;

    @AndroidFindBy(id = "signInButton")
    private WebElement signIn_btn;

    @AndroidFindBy(id = "nav_map")
    private WebElement map_nav;

    @AndroidFindBy(id = "nav_account")
    private WebElement myProfile_nav;

    @AndroidFindBy(id = "nav_subscriptions")
    private WebElement mySubscriptions_nav;

    @AndroidFindBy(id = "nav_trips")
    private WebElement myTrips_nav;

    @AndroidFindBy(id = "nav_stats")
    private WebElement myStats_nav;

    @AndroidFindBy(id = "nav_payments")
    private WebElement myPayments_nav;

    @AndroidFindBy(id = "nav_offers")
    private WebElement offers_nav;

    @AndroidFindBy(id = "nav_news")
    private WebElement news_nav;

    @AndroidFindBy(id = "nav_contact_us")
    private WebElement contactUs_nav;

    @AndroidFindBy(id = "nav_logout")
    private WebElement logout_nav;

    public NavigationBarPopin(DriverFactory driverFactory, MyAppium myAppium, Waiters wait){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.wait       = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    public void waitForLoad(WebElement element){
        wait.elementToBeClickable(element);
    }

    public void accessLogInPage(){
        waitForLoad(signIn_btn);
        myAppium.click(signIn_btn);
    }

    public void accessOffersGroups(){
        waitForLoad(offers_nav);
        myAppium.click(offers_nav);
    }

    public boolean isSignInBtnDisplayed(){
        return myAppium.isElementDisplayed(signIn_btn);
    }

    public boolean isProfileBtnDisplayed(){
        waitForLoad(myProfile_nav);
        return myAppium.isElementDisplayed(myProfile_nav);
    }

    public void logOut(){
        waitForLoad(logout_nav);
        myAppium.click(logout_nav);
    }


}
