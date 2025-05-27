package demo.web_demo;

import demo.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileBrowserType;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IOSWebDemoTest extends BaseTest {

    protected IOSDriver driver = null;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .withBrowserName(MobileBrowserType.SAFARI)
                .amend("digitalai:testName", "iOS Web Demo")
                .amend("digitalai:accessKey", getProperty("cloud.accessKey"))
                .amend("digitalai:deviceQuery", "@os='ios'")
                .amend("digitalai:appiumVersion", "2.18.0");
        driver = new IOSDriver(new URL(getProperty("cloud.url") + "/wd/hub"), options);
    }

    @Test
    public void iOSWebDemo() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.get("https://demo-bank.ct.digital.ai");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='login']")));

        driver.findElement(By.xpath("//*[@data-auto='username']//input")).sendKeys("company");
        driver.findElement(By.xpath("//*[@data-auto='password']//input")).sendKeys("company");
        driver.findElement(By.xpath("//*[@data-auto='login']")).click();

        driver.findElement(By.xpath("//*[@data-auto='transfer-funds']")).click();

        driver.findElement(By.xpath("//input[@name='NAME']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@name='PHONE']")).sendKeys("1-234-5678");
        driver.findElement(By.xpath("//input[@name='AMOUNT']")).sendKeys("1000");
        driver.findElement(By.xpath("//*[@data-auto='country']")).click();
        driver.findElement(By.xpath("//*[text()='India']")).click();
        driver.findElement(By.xpath("//*[@data-auto='transfer-button']")).click();
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }
}
