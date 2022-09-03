package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Base {
public static WebDriver driver;
	
	@BeforeClass
	public void setup() {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();											//Write this line in log file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		//Implicit wait of 30 sec's
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));		//Page load time out of 30 sec's
		driver.manage().deleteAllCookies();										//Delete all cookies
		driver.get("https://demo.opencart.com/");								//Open the URL in browser
		driver.manage().window().maximize();									//Maximise browser window
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
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