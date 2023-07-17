package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class TutorialPage {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;

    @AndroidFindBy(id = "com.studiobluelime.ecommerceapp:id/btn_next")
    private WebElement skip_btn;


    public TutorialPage(DriverFactory driverFactory, MyAppium myAppium, Waiters wait){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.wait       = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void waitForLoad(){
        wait.elementToBeClickable(skip_btn, 15);
    }
    public void skipTutorial(){
        waitForLoad();
        myAppium.click(skip_btn);
        waitForLoad();
        myAppium.click(skip_btn);
        waitForLoad();
        myAppium.click(skip_btn);
    }
}
