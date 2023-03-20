import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IOSExampleTest extends BaseTest {
	
	protected IOSDriver<IOSElement> driver = null;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		dc.setCapability("testName", "iOS Native Demo");
		dc.setCapability("accessKey", getProperty("cloud.accessKey"));
		dc.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
		dc.setCapability("appiumVersion", "1.22.3");
		driver = new IOSDriver<>(new URL(new URL(getProperty("cloud.url")), "/wd/hub"), dc);
	}
	
	@Test
	public void quickStartiOSNativeDemo() {
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