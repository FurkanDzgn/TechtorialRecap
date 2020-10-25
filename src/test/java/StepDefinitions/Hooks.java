package StepDefinitions;

import Utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    WebDriver driver = Driver.getDriver();

    @Before
    public void setUp(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshoot =  ((TakesScreenshot)Driver.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshoot, "image/png",scenario.getName());
        }
 //       driver.quit();
    }

}
