package runner;

import io.cucumber.testng.*;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.util.Objects;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepDefinitions"},
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        tags = "@smoke"
)

//@Listeners({ExtentITestListenerClassAdapter.class})
public class TestRunner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(
            alwaysRun = true
    )
    public void setUpClass(ITestContext context) {
        XmlTest currentXmlTest = context.getCurrentXmlTest();
        Objects.requireNonNull(currentXmlTest);
        CucumberPropertiesProvider properties = currentXmlTest::getParameter;
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
    }

    @Test(
            groups = {"cucumber"},
            description = "Runs Cucumber Scenarios",
            dataProvider = "scenarios"
    )
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(
            alwaysRun = true
    )
    public void tearDownClass(ITestContext context) {
       if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }
}
