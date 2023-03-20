import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
	
	protected DesiredCapabilities dc = new DesiredCapabilities();
	protected Properties cloudProperties = null;
	
	protected String getProperty(String property) {
		try {
			if (cloudProperties == null) {
				initCloudProperties();
			}
		} catch (Exception e) {
		    return null;
		}
		return cloudProperties.getProperty(property);
	}
	
	private void initCloudProperties() throws IOException {
		cloudProperties = new Properties();
		try (FileReader fr = new FileReader("cloud.properties")) {
			cloudProperties.load(fr);
		}
	}
}
