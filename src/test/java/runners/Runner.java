package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features.mobile/"},
        glue = {"steps"},
        plugin = {},
        dryRun = false,
        monochrome = true,
        tags = "@test"
)
public class Runner extends AbstractTestNGCucumberTests{
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(){ return super.scenarios();}
}
