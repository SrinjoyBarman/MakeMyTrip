package demo;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {
	public static WebDriver driver;
	
	public static WebDriver getWebDriver() throws MalformedURLException {
		String nodeUrl= "http://192.168.1.4:6969/wd/hub";
		new DesiredCapabilities();
		DesiredCapabilities cap= DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		cap.setPlatform(Platform.WINDOWS);
		
		
//		String chromeProfilePath = "C:\\Users\\RIL\\AppData\\Local\\Google\\Chrome\\User Data\\";
		ChromeOptions chromeProfile = new ChromeOptions();
		cap.setCapability(ChromeOptions.CAPABILITY, chromeProfile);
//		chromeProfile.addArguments("user-data-dir=" + chromeProfilePath);
//		// Here you specify the actual profile folder (Profile 2)
//		chromeProfile.addArguments("profile-directory=Profile 1");
		

		driver= new RemoteWebDriver(new URL(nodeUrl),cap);
		driver.manage().window().maximize();
//		driver.get("https://www.urbanladder.com/bookshelf?src=explore_categories");
		return driver;
	}
}