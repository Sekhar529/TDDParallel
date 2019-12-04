package Base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class BrowsersBase {

	protected ThreadLocal<WebDriver> Threaddriver = new ThreadLocal<WebDriver>();
	private static Properties prop;
	private final String propFilePath = System.getProperty("user.dir")+"/src/main/java/Config/config.properties";
	
	public BrowsersBase() throws IOException {
		
		prop = new Properties();
		
		FileInputStream fis = new FileInputStream(propFilePath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		if(!bis.equals(null)){
			prop.load(bis);
		}
		else {
			System.out.println("Property file: config.properties not found in class path");
		}
		//prop.load(bis);
	}
	
	private void setDriver(WebDriver browser) {
		
		Threaddriver.set(browser);
		
	}
	private WebDriver getDriver() 
	{
		return Threaddriver.get();
	}
	
	public WebDriver launchBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("CH")) 
		{
			System.setProperty("webdriver.chrome.driver","../TDDParllel/Drivers/chromedriver.exe" );
			setDriver(new ChromeDriver() );
			WebDriver driver = getDriver();

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			driver.get(prop.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);	
			
		}
		
		else if(browserName.equalsIgnoreCase("IE")) 
		{
			System.setProperty("webdriver.ie.driver","../TDDParllel/Drivers/IEDriverServer.exe" );
			
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.ignoreZoomSettings().introduceFlakinessByIgnoringSecurityDomains().withInitialBrowserUrl("https://www.fresherslive.com");
				
			setDriver(new InternetExplorerDriver(options));
			WebDriver driver = getDriver();
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			driver.get(prop.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);						
		}
		

		else if(browserName.equalsIgnoreCase("FF")) 
		{
			System.setProperty("webdriver.gecko.driver","../TDDParllel/Drivers/geckodriver.exe" );
			
			FirefoxOptions options = new FirefoxOptions();
			options.setLegacy(true);
			
			setDriver(new FirefoxDriver());
			WebDriver driver = getDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			driver.get(prop.getProperty("URL"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);						
		}
		return getDriver();
	}
	
}

