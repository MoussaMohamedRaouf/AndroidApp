package myappium;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Waiters {
    protected AppiumDriver driver;
    protected WebDriverWait waiter;
    protected MyAppium myAppium;
    protected int defaultImplicitlyWait;
    protected final Logger logger;

    public Waiters(DriverFactory driverFactory, MyAppium myAppium){
        this.driver     = driverFactory.getDriver();
        this.waiter     = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        this.myAppium = myAppium;
        this.defaultImplicitlyWait = DriverFactory.getDefaultImplicitlyWait();
        logger = LogManager.getLogger(Waiters.class);
    }


    /**
     * Stop thread in second
     * @param seconds to wait
     */
    public void sleepInSeconds(int seconds){
        Uninterruptibles.sleepUninterruptibly(seconds, TimeUnit.SECONDS);
    }

    /**
     * Stop thread in millis second
     * @param millis to wait
     */
    public void sleep(int millis){
        Uninterruptibles.sleepUninterruptibly(millis, TimeUnit.MILLISECONDS);
    }

    /***
     * Implicitly wait in second
     * @param seconds to wait
     */
    public void implicitlyWait(int seconds){
        this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /***
     * Implicitly wait in second
     */
    public void defaultImplicitlyWait(){
        this.driver.manage().timeouts().implicitlyWait(this.defaultImplicitlyWait, TimeUnit.SECONDS);
    }

    /***
     * On our web site, a custom loader might appears and blocks the next action - as a click.
     * In order to avoid a click error this method will wait to see if the loader appears and - if so - wait for it to disappear
     */
    public void vlsLoader(){
        if(myAppium.isElementDisplayed("//div[contains(@class, 'loader-container')]")){
            try {
                waiter.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("loader-container"))));
            }catch(NoSuchElementException | StaleElementReferenceException e){
                logger.info("The loader disappeared from the DOM");
            }
        }
    }

    public void visibilityOf(WebElement webElement){
        try {
            waiter.until(ExpectedConditions.visibilityOf(webElement));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void visibilityOf(WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    /**
     * Needed for waiting redirection_btn when validating a payment in order to avoid that an error occurs in the log
     * @param webElement element to wait
     * @param timeOutInSeconds timeout
     */
    public void visibilityOf_WithoutExceptionHandling(WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public boolean waitAndVerifyVisibilityOf(WebElement element, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch(NoSuchElementException | TimeoutException e){
            return false;
        }
        return true;
    }

    public void elementToBePresent(TypeOfSelector selector, String locationOfElement){
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            waiter.until(ExpectedConditions.presenceOfElementLocated(element));
        }catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBePresent(TypeOfSelector selector, String locationOfElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
        } catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void allElementsToBePresent(TypeOfSelector selector, String locationOfElement){
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
        }catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void allElementsToBePresent(TypeOfSelector selector, String locationOfElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
        } catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }


    public void untilInvisibilityOf(WebElement webElement){
        try {
        waiter.until(ExpectedConditions.invisibilityOf(webElement));
        } catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementNotPresent(WebElement element, int timeOut){
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - startTime < timeOut* 1000L && myAppium.isElementDisplayed(element)){
            sleep(500);
        }
    }

    public void clickAndWait(WebElement elementToClick, WebElement elementToWait, int timeOut){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeOut * 1000L && !myAppium.isElementDisplayed(elementToWait)) {
            myAppium.click(elementToClick);
        }
    }

    public void untilInvisibilityOf(WebElement webElement, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try{
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        } catch(NoSuchElementException | StaleElementReferenceException | TimeoutException  e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBeClickable(WebElement element){
        try{
            waiter.until(ExpectedConditions.elementToBeClickable(element));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBeClickable(WebElement element, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementNotToBeClickable(WebElement element){
        try{
            waiter.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(element)));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBeSelected(WebElement element, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try{
            wait.until(ExpectedConditions.elementToBeSelected(element));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBeSelected(WebElement element) {
        try{
            waiter.until(ExpectedConditions.elementToBeSelected(element));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementNotToBeSelected(WebElement element, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try{
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeSelected(element)));
        }catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void titleToBe(String expectedTitle){
        try {
            waiter.until(ExpectedConditions.titleIs(expectedTitle));
        } catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void urlToBe(String url){
        try {
            waiter.until(ExpectedConditions.urlToBe(url));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void urlContains(String fractionOfUrl){
        try {
            waiter.until(ExpectedConditions.urlContains(fractionOfUrl));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void urlContains(String fractionOfUrl, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try {
            wait.until(ExpectedConditions.urlContains(fractionOfUrl));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }


    public void numberOfElementsToBe(TypeOfSelector selector, String locationOfElement, int numberOfExpectedElements){
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            waiter.until(ExpectedConditions.numberOfElementsToBe(element, numberOfExpectedElements));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void numberOfElementsToBe(TypeOfSelector selector, String locationOfElement,int numberOfExpectedElements, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        By element = selectTypeOfSelector(selector, locationOfElement);
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(element, numberOfExpectedElements));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void numberOfWindowsToBe(int numberOfExpectedWindows){
        try{
            waiter.until(ExpectedConditions.numberOfWindowsToBe(numberOfExpectedWindows));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void numberOfWindowsToBe(int numberOfExpectedWindows, int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOutInSeconds));
        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(numberOfExpectedWindows));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void elementToBeEnable(WebElement element){
        try {
            waiter.until(ExpectedConditions.not(ExpectedConditions.attributeContains(element,  "class", "disabled")));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public void forAttributeToBePresent(WebElement element, String attribute, String value) {
        try{
            waiter.until(ExpectedConditions.attributeContains(element, attribute, value));
        }catch (TimeoutException e){
            logger.error(() -> e);
            throw new TimeoutException(e);
        }
    }

    public By selectTypeOfSelector(TypeOfSelector selector, String locationOfElement){
        switch(selector){
            case XPATH:
                return By.xpath(locationOfElement);
            case CLASS_NAME:
                return By.className(locationOfElement);
            case ID:
                return By.id(locationOfElement);
            case NAME:
                return By.name(locationOfElement);
            case LINK_TEXT:
                return By.linkText(locationOfElement);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(locationOfElement);
            case TAG_NAME:
                return By.tagName(locationOfElement);
        }
        return null;
    }


}
