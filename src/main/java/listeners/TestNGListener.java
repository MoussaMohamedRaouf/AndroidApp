package listeners;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestNGListener implements ITestListener {

    @Override
    public void onStart(ITestContext iTestContext) {
        ExtentService.getInstance().setSystemInfo("os", System.getProperty("os.name").toLowerCase());
        try {
            ExtentService.getInstance().setSystemInfo("host", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            ExtentService.getInstance().setSystemInfo("host", "unknown");
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // Do nothing on start
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        // Do nothing on test success
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        // Do nothing on test failure
        TestRetryListener.unloadRetryCounter();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult){
        if(TestRetryListener.isRetryAvailable()){
            String test = ExtentCucumberAdapter.getCurrentStep().getModel().getParent().getName();
            ExtentService.getInstance().getReport().getTestList().forEach(list ->
                    list.getChildren().removeIf(childTest -> childTest.getName().equals(test))
            );
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Do nothing on test failed but within success percentage
    }

    @Override
    public void onFinish(ITestContext testContext) {
        // Do nothing on test finish
    }


}
