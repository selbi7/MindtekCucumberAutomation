package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"json:target/cucumber.json","return:target/rerun.txt"},
        features = "src/test/resources/features",
        glue ="steps",
        tags = "@regression", // tags also can be noted as "@etsyApplication or/and @otherCrapApp" having multiple tags by using "or"/"and"
        dryRun = false
)

public class Runner {



}

