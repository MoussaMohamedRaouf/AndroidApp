package pages.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import myappium.MyAppium;
import myappium.Waiters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class HomePage {
    private final AppiumDriver driver;
    private final MyAppium myAppium;
    private final Waiters wait;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"App\"]")
    private WebElement burgerMenu;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.appcompat.widget.LinearLayoutCompat[4]/android.widget.CheckedTextView")
    private WebElement accountMenu;
    public HomePage(DriverFactory driverFactory, MyAppium myAppium, Waiters wait){
        this.driver     = driverFactory.getDriver();
        this.myAppium   = myAppium;
        this.wait       = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public void waitForLoad(WebElement element){
        wait.elementToBeClickable(element);
    }

    public void accessLoginPage() {
        waitForLoad(burgerMenu);
        myAppium.click(burgerMenu);
        waitForLoad(accountMenu);
        myAppium.click(accountMenu);
    }
}
