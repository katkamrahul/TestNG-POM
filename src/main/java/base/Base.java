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

import pages.HomePage;
import pages.LoginPage;
import pages.RegisterAccountPage;
import pages.RegisterAccountSuccessPage;
import pages.UserLoginPage;

public class Base {
	public static WebDriver driver;
	public Logger log;
	public ResourceBundle rb;
	
	public HomePage hp;
	public RegisterAccountPage rap;
	public RegisterAccountSuccessPage rasp;
	public LoginPage lp;
	public UserLoginPage ulp;	

	@Parameters ({"browser"})
	@BeforeClass (groups = {"sanity", "regression"})
	public void setup(String browser) {
		log = LogManager.getLogger(this.getClass());
		rb = ResourceBundle.getBundle("config");
		
		log.info("----- TEST CASE START :  " +this.getClass());					//Write this line in log file
		if(browser.equals("chrome")) {
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Chrome browser launched");								//Write this line in log file
		}
		
		else if(browser.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			System.setProperty("webdriver.gecko.driver", "browserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox browser launched");								//Write this line in log file
		}
		
		else if(browser.equals("edge")) {
			//WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
			driver = new EdgeDriver();
			log.info("Edge browser launched");									//Write this line in log file
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));		//Implicit wait of 30 sec's
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));		//Page load time out of 30 sec's
		driver.manage().deleteAllCookies();										//Delete all cookies
		driver.get(rb.getString("url"));										//Open the URL in browser
		log.info("URL launched, user is on homepage");							//Write this line in log file
		driver.manage().window().maximize();									//Maximise browser window
		
		hp = new HomePage();
		rap = new RegisterAccountPage();
		rasp = new RegisterAccountSuccessPage();
		lp = new LoginPage();
		ulp = new UserLoginPage();
	}
	
	@AfterClass (groups = {"sanity", "regression"})
	public void tearDown() {
		driver.quit();
		log.info("All opened browsers/windows closed");							//Write this line in log file
		log.info("----- TEST CASE END :  " +this.getClass());					//Write this line in log file
	}
	
	public void captureScreen(WebDriver driver, String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" +name+ ".png");
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