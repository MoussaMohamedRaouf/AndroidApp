package myappium;

import com.google.common.util.concurrent.Uninterruptibles;
import exceptions.NotFoundValueException;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import utils.DriverFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MyAppium {
    private final AppiumDriver driver;
    private final int defaultImplicitlyWait;

    private WebElement ele = null;
    private final Logger logger = LogManager.getLogger(MyAppium.class);

    public MyAppium(DriverFactory driverFactory){
        this.driver                 = driverFactory.getDriver();
        this.defaultImplicitlyWait  = DriverFactory.getDefaultImplicitlyWait();
    }

    /**
     * Stop thread in millis second
     * @param millis
     */
    public void sleep(int millis){
            Uninterruptibles.sleepUninterruptibly(millis, TimeUnit.MILLISECONDS);
    }

    /**
     * If the checkbox is unselected, select it.
     * @param element
     */
    public void selectCheckBox(WebElement element){
        if(!element.isSelected()){
            click(element);
        }
    }

    /**
     * If the checkbox is selected, unselect it.
     * @param element
     */
    public void unselectCheckBox(WebElement element){
        if(element.isSelected()){
            click(element);
        }
    }

    /**
     * This method will clear the input and enter thr new value
     * @param input
     * @param value
     */
    public void type(WebElement input, String value){
       logger.log(Level.INFO, () -> ("****** Trying to enter '"+ value + "' in " + input.toString()));
       input.clear();
       input.sendKeys(value);
    }

    /**
     * By giving the first window, this methods return the second window in order to switch to it.
     * @param firstWindow
     * @return window Id
     */
    public String getSecondWindow(String firstWindow){
        for(String window: driver.getWindowHandles()){
            if(!window.equals(firstWindow)){
                return window;
            }
        }
        return  null;
    }

    /**
     * This methods return true if element is present and displayed and false if element is not present
     * @param element
     * @return boolean
     */
    public boolean isElementDisplayed(WebElement element){
        logger.log(Level.INFO, () -> ("****** Verifying whether the element : '"+element.toString()+"' is present or not"));
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            return element.isDisplayed();
        }catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e){
            logger.log(Level.INFO, () -> ("****** The element  : '"+element.toString()+"' is not display"));
            return false;
        }finally {
            this.driver.manage().timeouts().implicitlyWait(this.defaultImplicitlyWait, TimeUnit.SECONDS);
        }
    }

    /**
     * This methods return true if element is present and displayed and false if element is not present
     * @param xpathLocator
     * @return boolean
     */
    public boolean isElementDisplayed(String xpathLocator){
        logger.log(Level.INFO, () -> ("****** Verifying whether the element : '"+xpathLocator+"' is present or not"));
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            WebElement element = driver.findElement(By.xpath(xpathLocator));
            return element.isDisplayed();
        }catch(org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException e){
            logger.log(Level.INFO, () -> ("****** The element located by : '"+xpathLocator+"' is not display"));
            return false;
        }finally {
            this.driver.manage().timeouts().implicitlyWait(this.defaultImplicitlyWait, TimeUnit.SECONDS);
        }
    }

    /**
     * Since the new bootstrap hab been added, it seems that a transparent element blocks the click on some buttons.
     * This method is the only way to click on it.
     * @param element
     */
    public void click(WebElement element){
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                element.click();
                clicked = true;
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
                logger.warn(String.format("****** Impossible to click on element [%s] after %d attempts.", element.toString(), attempts));
            }
            attempts++;
        }
        int finalAttempts = attempts;
        logger.log(Level.INFO, () -> String.format("****** Trying to click %d times on the element: %s", finalAttempts, element.toString()));
        // If even the element is still not clicked, try to click to get the exception
        if(!clicked){
            try {
                element.click();
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.StaleElementReferenceException | org.openqa.selenium.ElementNotInteractableException e) {
                logger.log(Level.ERROR, "Element not clickable after 10 attempts");
                logger.log(Level.ERROR, e::getMessage);
                e.printStackTrace();
            }
        }
        this.driver.manage().timeouts().implicitlyWait(this.defaultImplicitlyWait, TimeUnit.SECONDS);
    }

    /**
     * This methods is used to tap on an element
     * @param mobileElement
     */
    public void tap(WebElement mobileElement) {
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), mobileElement.getLocation()));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));

    }

    /**
     * This methods is used to double tap on an element
     * @param mobileElement
     */
    public void doubleTap(WebElement mobileElement) {
        logger.log(Level.INFO, () -> String.format("****** Trying to double tap on the element: %s", mobileElement.toString()));
        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), mobileElement.getLocation()));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    /**
     * This methods is used to swipe by coordinates to a point X to point Y
     */
    public void swipeByCoordinates() {
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.getWidth() * 0.8);
        int startY = (int) (dimension.getHeight() * 0.5);
        int endX =  (int) (dimension.getWidth()  * 0.2);
        int endY =  (int) (dimension.getHeight() * 0.5);
        logger.info(() -> "****** Trying to swipe by coordinates. ");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }

    /**
     * This methods is used to swipe using elements, to click to a specified element
     * @param selector
     * @param locationOfElement
     * @param startElement
     * @param timeOut
     */
    public void swipeAndClick(WebElement startElement, TypeOfSelector selector, String locationOfElement, int timeOut) {
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.getWidth() * 0.8);
        int startY = (int) (dimension.getHeight() * 0.5);
        int endX =  (int) (dimension.getWidth()  * 0.2);
        int endY =  (int) (dimension.getHeight() * 0.5);
        logger.info(() -> "****** Trying to swipe by coordinates. ");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }

    /**
     * This methods is used to scroll down using coordinates, to a point X to point Y
     */
    public void scrollDownByCoordinates(){
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.getWidth() * 0.8);
        int startY = (int) (dimension.getHeight() * 0.5);
        int endX =  (int) (dimension.getWidth()  * 0.2);
        int endY =  (int) (dimension.getHeight() * 0.5);
        logger.info(() -> "****** Trying to swipe by coordinates. ");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(dragNDrop));

        }

   /** This methods is used to scroll down using elements as coordinates, and to click to a specified element
     * @param selector
     * @param locationOfElement
     * @param startElement
     * @param timeOut
     */
    public void scrollDownAndClick(WebElement startElement, TypeOfSelector selector, String locationOfElement, int timeOut){
        Dimension dimension = driver.manage().window().getSize();
        By element = selectTypeOfSelector(selector, locationOfElement);
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight());
        int endX = dimension.width / 2;
        int endY = 10;

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeOut * 1000L && !isElementDisplayed(locationOfElement)) {
            logger.info(() -> "****** Thrying to scroll down to find the element located "+ locationOfElement +" with the selector : "+selector);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence dragNDrop = new Sequence(finger, 1);
            dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), startX, startY));
            dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                    PointerInput.Origin.viewport(),endX, endY));
            dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(dragNDrop));


        }
        click(driver.findElement(element));    }

    /** This methods is used to select a specified type of selector
     * @param selector
     * @param locationOfElement
     */
    public By selectTypeOfSelector(TypeOfSelector selector, String locationOfElement){
        return switch (selector) {
            case XPATH -> By.xpath(locationOfElement);
            case CLASS_NAME -> By.className(locationOfElement);
            case ID -> By.id(locationOfElement);
            case NAME -> By.name(locationOfElement);
            case LINK_TEXT -> By.linkText(locationOfElement);
            case PARTIAL_LINK_TEXT -> By.partialLinkText(locationOfElement);
            case TAG_NAME -> By.tagName(locationOfElement);
            default -> throw new NotFoundValueException("The selector [" + selector + "] has not be found ");
        };
    }


}
