package pages;

import exceptions.NoneProcessIdCollectingException;
import functions.excel.ExcelManager;
import functions.miscellaneous.PathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.asserts.SoftAssert;
import utils.EnvironmentVariable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Supplier;

public class CommonInstance {
    private String subscriptionId;
    private String periodId;
    private String processName;
    private String accountEmail;
    private String offerId;
    private int lastProcessId;
    private final SoftAssert softAssert = new SoftAssert();
    Supplier<ExcelManager> newExcel = () -> new ExcelManager("ProcessesDetails/ProcessesDetails.xlsx", EnvironmentVariable.ENV+EnvironmentVariable.CONTRACT_CODE);
    private final ThreadLocal<ExcelManager> processesFile = ThreadLocal.withInitial(newExcel);
    private final Logger logger = LogManager.getLogger(CommonInstance.class);

    public String getSubscriptionId(){
        logger.info(() -> "****** The subscription id retrieved is : "+  this.subscriptionId);
        return this.subscriptionId;
    }


    public void setSubscriptionId(String subscriptionId){
        this.subscriptionId = subscriptionId;
    }

    public int getLastProcessId(){
        return this.lastProcessId;
    }

    public void setLastProcessId(int lastProcessId){
        this.lastProcessId = lastProcessId;
        logger.info(() -> "****** The last process is : "+  this.lastProcessId);
    }

    public String getOfferId(){
        logger.info(() -> "****** The Offer id gotten from the commonInstance class is : "+ this.offerId);
        return this.offerId;
    }

    public void setOfferId(String offerId){
        this.offerId = offerId;
        logger.info(() -> "****** The Offer id : "+ this.offerId+" is set into the commonInstance class");
    }

    public String getPeriodId(){
        logger.info(() -> "****** The period id retrieved is : "+ this.periodId);
        return this.periodId;
    }

    public void setPeriodId(String periodId){
        this.periodId = periodId;
    }

    public String getProcessName(){
        logger.info(() -> ("****** The process name is : " + this.processName));
        return this.processName;
    }

    public void setProcessName(String processName){
        this.processName = processName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public ExcelManager getProcessesFile(){
        return this.processesFile.get();
    }

    public void unloadProcessFile(){
            this.processesFile.remove();
    }


    public SoftAssert getSoftAssert(){
        return this.softAssert;
    }

    public JSONObject getVerificationDataSet(String processName, String service){
        FileInputStream myFile = null;
        try {
            myFile = new FileInputStream(PathFinder.getPath("expected_results/expected_results_data.json"));
        } catch (FileNotFoundException e) {
            logger.error("File not found exception : ", e);
            e.printStackTrace();
        }
        JSONTokener tokener = new JSONTokener(myFile);
        JSONObject json = new JSONObject(tokener);
        return json.getJSONObject(processName).getJSONObject(service);
    }


    /***
     * This method compares the processIds before and after the  validation.
     * If the one after is bigger, then it will be return the processId
     * @param firstProcessId is the process Id gotten before the validation
     * @param lastProcessId is the process Id gotten after the validation
     * @return
     */
    public String verifyAndGetProcessId(int firstProcessId, int lastProcessId){
        if(lastProcessId>firstProcessId){
            return String.valueOf(lastProcessId);
        }else{
            logger.error(() -> "Something went wrong, It seems that the process id gotten after the validation ["+lastProcessId+"] is not bigger than the first one ["+firstProcessId+"].");
            throw new NoneProcessIdCollectingException("Something went wrong, It seems that the process id gotten after the validation ["+lastProcessId+"] is not bigger than the first one ["+firstProcessId+"].");
        }
    }


}
