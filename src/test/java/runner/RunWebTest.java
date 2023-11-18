package runner;

//Junit imports
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Cucumber options explanation <br>
 * <pre>"features" - Path to the feature files location
 * "glue" - Path to the steps definition files location
 * "tags" - Run only the test scenario/scenarios specified in the tag
 * "plugin" - Specified the custom plugins in junit.
 * *IMPORTANT! Generated HTML report located at "reports/cucumber/test_results.html"</pre>
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/Features"},
        glue = "cucumber",
//        tags = "@Login",
        plugin = {
                    "html:build/reports/cucumber/test_results.html",
                    "json:build/reports/cucumber/test_results.json"
        }
)
public class RunWebTest {
}
