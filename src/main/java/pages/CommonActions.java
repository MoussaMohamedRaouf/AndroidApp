package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class CommonActions {
    private final AppiumDriver driver;
    private final Waiters wait;
    private final MyAppium myAppium;
    private final Logger logger = LogManager.getLogger(CommonActions.class);

    @AndroidFindBy(id="progressBar")
    private WebElement loader;

    public CommonActions(DriverFactory driverFactory, Waiters wait, MyAppium myAppium){
        this.driver     = driverFactory.getDriver();
        this.wait       = wait;
        this.myAppium = myAppium;
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }


    public void waitForVlsLoaderToDisappear(){
        PageFactory.initElements(this.driver, this);
        if(myAppium.isElementDisplayed(loader)){
            logger.info("****** The loader is present.");
            try {
                wait.untilInvisibilityOf(loader, 15);
            }catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e){
                logger.info("****** The loader disappeared.");
            }
        }
    }


}
