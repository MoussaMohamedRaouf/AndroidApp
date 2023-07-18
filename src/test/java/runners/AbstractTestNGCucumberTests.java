package runners;


import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import listeners.TestNGListener;
import listeners.TestRetryListener;
import org.apiguardian.api.API;
import org.testng.annotations.*;

@API(
        status = API.Status.STABLE
)
@Listeners(TestNGListener.class)
public class AbstractTestNGCucumberTests {
    public TestNGCucumberRunner testNGCucumberRunner;
    @BeforeClass(alwaysRun = true)

    public void setUpClass(){
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios",
            retryAnalyzer = TestRetryListener.class

    )

    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper){
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

@AfterClass(alwaysRun = true)
    public void tearDown(){
        if (this.testNGCucumberRunner!=null){
            this.testNGCucumberRunner.finish();
        }
}


}
