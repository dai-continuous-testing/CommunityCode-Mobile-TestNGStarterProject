package demo.native_demo;

import demo.BaseTest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IOSNativeDemoTest extends BaseTest {

    protected IOSDriver driver = null;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setApp("cloud:com.experitest.ExperiBank")
                .setBundleId("com.experitest.ExperiBank")
                .amend("digitalai:testName", "iOS Native Demo")
                .amend("digitalai:accessKey", getProperty("cloud.accessKey"))
                .amend("digitalai:deviceQuery", "@os='ios'")
                .amend("digitalai:appiumVersion", "2.18.0");
        driver = new IOSDriver(new URL(getProperty("cloud.url") + "/wd/hub"), options);
    }

    @Test
    public void iOSNativeDemo() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElement(By.xpath("//*[@name='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@name='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@name='loginButton']")).click();
        driver.findElement(By.xpath("//*[@name='makePaymentButton']")).click();
        driver.findElement(By.xpath("//*[@name='phoneTextField']")).sendKeys("0501234567");
        driver.findElement(By.xpath("//*[@name='nameTextField']")).sendKeys("John Snow");
        driver.findElement(By.xpath("//*[@name='amountTextField']")).sendKeys("50");
        driver.findElement(By.xpath("//*[@name='countryButton']")).click();
        driver.findElement(By.xpath("//*[@name='Switzerland']")).click();
        driver.findElement(By.xpath("//*[@name='sendPaymentButton']")).click();
        driver.findElement(By.xpath("//*[@name='Yes']")).click();
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
        driver.quit();
    }
}
