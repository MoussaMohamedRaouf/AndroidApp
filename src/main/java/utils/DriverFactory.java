package utils;


import exceptions.NotFoundValueException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    protected AppiumDriver driver;
    private String apkPath;
    private String url;
    private static final String PLATFORM_NAME = "platformName";
    private static final String AUTOMATION_NAME = "automationName";
    private static final String PLATFORM_VERSION = "platformVersion";
    private static final String DEVICE_NAME = "deviceName";
    private static final String APPIUM_LANGUAGE = "language";
    private static final String APPIUM_LOCALE = "locale";
    private static final String FULL_RESET = "fullReset";
    private static final String APPIUM_AUTO_GRANT_PERMISSIONS = "appium:autoGrantPermissions";
    private static final String APP = "app";
    private static final String ACCESS_MODE_ERROR_MSG = "the specified access mode does not exist. expected 'emulator', 'realtime' or 'cloud' but found : ";
    private static final String PLATFORM_ERROR_MSG = "the specified platform does not exist. expected 'android' or 'ios' but found : ";
    private final Logger logger = LogManager.getLogger(DriverFactory.class);

    protected static final int DEFAULT_IMPLICITLY_WAIT = 10;

    /**
     * Return the instanced driver
     * @return driver
     */
    public AppiumDriver getDriver(){
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return this.driver;
    }

    /**
     * According the platform provided, the right driver is setup
     */
    public void setUpDriver() {
        this.apkPath = System.getProperty("user.dir")+"/apps/ecomm1.8.apk";
        this.url = "http://"+Profile.HOST+":"+Profile.PORT;
        logger.info(() -> "the apk path is : "+ this.apkPath);
        logger.info(() -> "the appium server url is : "+ this.url);
        switch (Profile.PLATFORM.toLowerCase()) {
            case "android" -> setUpAndroidDriver();
            case "ios" -> setUpIOSDriver();
            default -> {
                logger.error(() -> PLATFORM_ERROR_MSG + Profile.PLATFORM);
                throw new NotFoundValueException(PLATFORM_ERROR_MSG + Profile.PLATFORM);
            }
        }

    }

    /**
     * According the access mode provided, the android driver is setup
     */
    public void setUpAndroidDriver(){
        switch (Profile.ACCESS_MODE.toLowerCase()) {
            case "emulator", "realtime" -> setUpAndroidEmulator();
            case "cloud" -> setUpAndroidCloud();
            default -> {
                logger.error(() -> ACCESS_MODE_ERROR_MSG + Profile.ACCESS_MODE);
                throw new NotFoundValueException(ACCESS_MODE_ERROR_MSG + Profile.ACCESS_MODE);
            }
        }
    }

    /**
     * According the access mode provided, the ios driver is setup
     */
    public void setUpIOSDriver(){
        switch (Profile.ACCESS_MODE.toLowerCase()) {
            case "emulator" -> setUpIOSEmulator();
            case "realtime" -> setUpIOSRealtime();
            case "cloud" -> setUpIOSCloud();
            default -> {
                logger.error(() -> ACCESS_MODE_ERROR_MSG + Profile.ACCESS_MODE);
                throw new NotFoundValueException(ACCESS_MODE_ERROR_MSG + Profile.ACCESS_MODE);
            }
        }
    }

    public void setUpAndroidEmulator(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "Android");
        caps.setCapability(AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(PLATFORM_VERSION, Profile.OS_VERSION);
        caps.setCapability(DEVICE_NAME, Profile.DEVICE_NAME);
        caps.setCapability(FULL_RESET, true);
        caps.setCapability(APPIUM_LANGUAGE, "fr");
        caps.setCapability(APPIUM_LOCALE, "FR");
        caps.setCapability(APPIUM_AUTO_GRANT_PERMISSIONS,true);
        caps.setCapability(APP, this.apkPath);
        logger.info(() -> "Capabilities of android emulator are : "+ caps);
        try {
            driver = new AndroidDriver(new URL(this.url), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpIOSEmulator(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "iOS");
        caps.setCapability(AUTOMATION_NAME, "XCUITest");
        caps.setCapability(PLATFORM_VERSION, Profile.OS_VERSION);
        caps.setCapability(DEVICE_NAME, Profile.DEVICE_NAME);
        caps.setCapability(APPIUM_LANGUAGE, "fr");
        caps.setCapability(APPIUM_LOCALE, "fr_FR");
        caps.setCapability(FULL_RESET, true);
        caps.setCapability(APPIUM_AUTO_GRANT_PERMISSIONS, true);
        caps.setCapability(APP, this.apkPath);
        logger.info(() -> "Capabilities of IOS emulator are : "+ caps);
        try {
            driver = new IOSDriver(new URL(this.url), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpIOSRealtime(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM_NAME, "iOS");
        caps.setCapability(AUTOMATION_NAME, "XCUITest");
        caps.setCapability(PLATFORM_VERSION, Profile.OS_VERSION);
        caps.setCapability(DEVICE_NAME, Profile.DEVICE_NAME);
        caps.setCapability(FULL_RESET, true);
        caps.setCapability(APP, this.apkPath);
        logger.info(() -> "Capabilities IOS realtime are : "+ caps);
        try {
            driver = new IOSDriver(new URL(this.url), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void setUpAndroidCloud(){
        logger.info(()-> "setUpAndroidCloud is not yet implemented");
    }

    public void setUpIOSCloud() {
        logger.info(() -> "setUpIOSCloud is not yet implemented");
    }

    public static int getDefaultImplicitlyWait(){
        return DEFAULT_IMPLICITLY_WAIT;
    }


}
























