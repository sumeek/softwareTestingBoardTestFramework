package company.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static WebDriver driver;

    public WebDriver initializeDriver() throws IOException {

        Properties properties = new Properties();

        FileReader fileReader = new FileReader(System.getProperty("user.dir") +
                "//src//main//java//company//resources//GlobalData.properties");

        properties.load(fileReader);

        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {

            ChromeOptions options = new ChromeOptions();

            options.addArguments("--remote-allow-origins=*");

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver(options);

            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        } else {

            //initialize other browser driver
        }
        return driver;
    }


    @Before
    public void launchApplication() throws IOException {

        driver = initializeDriver();

        driver.get("https://magento.softwaretestingboard.com/");

    }

    @After
    public void takeScraenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {

            TakesScreenshot ts = (TakesScreenshot) driver;

            byte[] src = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(src, "image/png", "screenshot");
        }
    }

    @After
    public void tearDown() {

        driver.close();

    }

}
