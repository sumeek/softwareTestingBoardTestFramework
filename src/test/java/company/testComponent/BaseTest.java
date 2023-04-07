package company.testComponent;

import company.pageObjects.ProductSelection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;

    public ProductSelection productSelection;

    public WebDriver initializeDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        return driver;

    }

    @BeforeMethod(alwaysRun = true)

    public ProductSelection launchApplication() {

        driver = initializeDriver();

        driver.get("https://magento.softwaretestingboard.com/");

        productSelection =new ProductSelection(driver);

        return productSelection;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        driver.close();

    }

}
