package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseC {
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@Parameters({"browser"})
	@BeforeClass(groups={"sanity", "regression"})
	public void setup(String browser) {
		rb = ResourceBundle.getBundle("config");								//Load resource bundle object with config.properties file
		logger = LogManager.getLogger(this.getClass());							//Logging Log4j2
		
		logger.info("----- TEST CASE START :  " +this.getClass());				//Write this line in log file
		if(browser.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Chrome browser launched");								//Write this line in log file
		}
		else if(browser.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", "browserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			logger.info("Firefox browser launched");							//Write this line in log file
		}
		else if(browser.equals("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
			driver = new EdgeDriver();
			logger.info("Edge browser launched");								//Write this line in log file
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		//Implicit wait of 30 sec's
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));		//Page load time out of 30 sec's
		driver.manage().deleteAllCookies();										//Delete all cookies
		driver.get(rb.getString("url"));										//Open the URL in browser
		logger.info("Home page displayed");										//Write this line in log file
		driver.manage().window().maximize();									//Maximise browser window
	}
	
	@AfterClass(groups={"sanity", "regression"})
	public void tearDown() {
		driver.quit();
		logger.info("All opened browsers/windows closed");						//Write this line in log file
		logger.info("----- TEST CASE END :  " +this.getClass());				//Write this line in log file
	}
	
	public void captureScreen(WebDriver driver, String tName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" +tName+ ".png");
		FileUtils.copyFile(source, target);
	}
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public int randomInteger() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString));
	}
}