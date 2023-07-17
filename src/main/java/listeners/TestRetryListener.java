package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utils.Profile;

public class TestRetryListener implements IRetryAnalyzer {
    private static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);
    private int retryMax = getRetryMax();
    public static ThreadLocal<Boolean> retryAvailable = ThreadLocal.withInitial(() -> false);
    private static final String RETRY = "RETRY";


    @Override
    public boolean retry(ITestResult iTestResult) {

        int status = iTestResult.getStatus();
        if(!iTestResult.isSuccess()){
            if(counter.get() < retryMax){
                counter.set(counter.get()+1);
                iTestResult.setStatus(ITestResult.FAILURE);
                retryAvailable.set(true);
                return true;
            }else{
                iTestResult.setStatus(status);
                counter.set(0);
                retryAvailable.set(false);
            }
        }else{
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    public static int getCounter(){
        return counter.get();
    }

    public static boolean isRetryAvailable() {
        return retryAvailable.get();
    }

    public int getRetryMax(){
        if(System.getProperty(RETRY)!=null && !System.getProperty(RETRY).isEmpty()){
            return Integer.parseInt(System.getProperty(RETRY));
        }else {
            return Integer.parseInt(Profile.RETRY);
        }
    }

    public static void unloadRetryCounter(){
        counter.remove();
    }
}



