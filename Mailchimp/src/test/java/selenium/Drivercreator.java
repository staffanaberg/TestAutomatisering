package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Drivercreator {

	
	public WebDriver createBrowser(String browser) {
		WebDriver driver;
		
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		return driver;
		
	}

	
}
