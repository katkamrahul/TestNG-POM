package utilities;

//import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String repName;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 	// time stamp
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); 					// location of report
		sparkReporter.config().setDocumentTitle("nopCommerce Automation Report"); 			// Title of report
		sparkReporter.config().setReportName("Opencart Functional Testing"); 				// Name of the report
		sparkReporter.config().setTheme(Theme.DARK); 										// Theme of the report (DARK / STANDARD)

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "nopCommerce");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("Sub Module", "Login");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("Tester name", "Rahul");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		try {
			String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + result.getName() + ".png";
			test.addScreenCaptureFromPath(screenshotPath);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); 				// test=extent.createTest(result.getTestContext().getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		/*
		 * try { //Create the email message URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465); email.setAuthenticator(new
		 * DefaultAuthenticator("katkam.rahul@gmail.com", "password")); //Email
		 * username/password email.setSSLOnConnect(true);
		 * email.setFrom("katkam.rahul@gmail.com"); //Sender
		 * email.setSubject("Test Results"); //Email subject
		 * email.setMsg("Please find Attached Report...."); //Email body message
		 * email.addTo("qateam@gmail.com"); //Receiver email.attach(url,
		 * "extent report", "please check report..."); //Attach URL of the report
		 * email.send(); //Send the email } catch(Exception e){ e.printStackTrace(); }
		 */
	}
}