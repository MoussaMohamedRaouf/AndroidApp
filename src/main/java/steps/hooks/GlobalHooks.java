package steps.hooks;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.CommonInstance;
import utils.DriverFactory;
import utils.EnvironmentVariable;

public class GlobalHooks {
    private final DriverFactory driverFactory;
    private AppiumDriver driver;
    private final CommonInstance instance;
    private final Logger logger = LogManager.getLogger(GlobalHooks.class);

    public GlobalHooks(DriverFactory driverFactory, CommonInstance instance){
        this.driverFactory      = driverFactory;
        this.instance           = instance;
    }

    @Before
    public void testInitialization(Scenario scenario){
        setupDriver();
        logger.info(()-> "** The test '"+ scenario.getName() +"' has just started");
    }


    public void setupDriver(){
        driverFactory.setUpDriver();
        this.driver = driverFactory.getDriver();
        logger.info(() -> "The driver is UP ");
    }

    public void pasteScreenShotInReports(Scenario scenario){
        String screenshotName = scenario.getName().replace(" ","_");
        TakesScreenshot newScreen = driver;
        String scrshot = newScreen.getScreenshotAs(OutputType.BASE64);
        ExtentCucumberAdapter.getCurrentStep().fail(screenshotName+".png", MediaEntityBuilder.createScreenCaptureFromBase64String(scrshot).build());
    }


}
