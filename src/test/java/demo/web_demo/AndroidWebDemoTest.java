package demo.web_demo;

import demo.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileBrowserType;
import java.net.MalformedURLException;
import java.net.URL;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidWebDemoTest extends BaseTest {
	
	protected AndroidDriver<AndroidElement> driver = null;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		dc.setCapability("testName", "Android Web Demo");
		dc.setCapability("accessKey", getProperty("cloud.accessKey"));
		dc.setCapability("deviceQuery", "@os='android'");
		dc.setCapability("appiumVersion", "1.22.3");
		
		dc.setBrowserName(MobileBrowserType.CHROME);
		
		driver = new AndroidDriver<>(new URL(new URL(getProperty("cloud.url")), "/wd/hub"), dc);
	}
	
	@Test
	public void androidWebDemo() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		driver.get("https://demo-bank.ct.digital.ai");
		
		new WebDriverWait(driver, 60)
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
