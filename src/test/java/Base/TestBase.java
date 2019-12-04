package Base;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utils.captureScreenshot;

public class TestBase
{
	public  ExtentHtmlReporter htmlReporter;
	public  static ExtentReports reporter;//don't remove static here otherwise you will get null pointer exception
	public  ExtentTest test;
	public  ExtentTest parentNode;
	public  ExtentTest childNode;
	public 	WebDriver driver= null;
	
	String date = new SimpleDateFormat("dd_MM_yy HH-mm-ss").format(new Date());	
	String workingDir = System.getProperty("user.dir");
	String finalPath = workingDir+"\\Reports\\"+date+"\\extentReport.html";
	
	public final String reporterPath = finalPath;

	/* --- Before class taking considering the last ran test into the report while running at Before Class level ---*/ 
	//@BeforeClass
	//@BeforeTest
	@BeforeSuite(alwaysRun=true,description="test")
	public void extentReportSetup() 
	{
		System.out.println("===== I am Befor Test Method==== ");
		
		htmlReporter = new ExtentHtmlReporter(reporterPath);
		htmlReporter.config().setDocumentTitle("Free Job Portal");
		htmlReporter.config().setReportName("ExtentReporter");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		reporter = new ExtentReports();
		reporter.attachReporter(htmlReporter);
		
		reporter.setSystemInfo("Host","localhost");
		reporter.setSystemInfo("OS",System.getProperty("OS"));
		reporter.setSystemInfo("User","Sekhar");
	}
	
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass(ITestContext context)
	{ 
		String groupName = context.getCurrentXmlTest().getIncludedGroups().get(0).toUpperCase();
		this.test = reporter.createTest(groupName);
		//this.test = reporter.createTest(getClass().getName());
		//test.pass(getClass().getName().concat(" Started "));
		
	}
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun=true)
	public void setupMetod(Method m, String nameOfBrowser) throws IOException
	{
		System.out.println("===== I am BeforeMethod =====");
		System.out.println(m.getName()+" Started In "+nameOfBrowser);
		
		this.parentNode = test.createNode(getClass().getName());
		this.childNode  = parentNode.createNode(m.getName()+"_"+nameOfBrowser);
		
		//childTest.createNode(m.getName()+"_"+nameOfBrowser);
		this.driver = new BrowsersBase().launchBrowser(nameOfBrowser);
	}
	
	@AfterMethod (alwaysRun=true)
	public void teardownMetod(ITestResult res) throws IOException {
		System.out.println("===== I am after method Method ======");
		
		if (res.getStatus()==ITestResult.SUCCESS) {
			childNode.log(Status.PASS, "TestCase " +res.getName()+" PASSED");	
			parentNode.pass("PASS");
			test.pass("PASSED");
		}
		
		else if(res.getStatus()==ITestResult.FAILURE) {
			childNode.log(Status.FAIL, "TestCase " +res.getName()+" FAILED");
			childNode.log(Status.FAIL, "Exception thrown is: "+res.getThrowable());
			String imgPath = new captureScreenshot(driver).takeScreenshot(res.getName());
			//System.out.print("image was stored in path:"+imgPath);
			imgPath = imgPath+".png";
			System.out.println("with name fullpath:"+imgPath);
			childNode.log(Status.FAIL,"failed screenCapture found here: ",MediaEntityBuilder.createScreenCaptureFromPath(imgPath,"screenshot").build());
			/*try {
			childNode.addScreencastFromPath(imgPath);}catch(IOException e) {}*/
			parentNode.fail("FAILED");
			//test.fail("FAILED").addScreencastFromPath(imgPath);
		}
		
		 
		else if (res.getStatus()==ITestResult.SKIP) {
			childNode.log(Status.SKIP, "TestCase " +res.getName()+" SKIPPED");
			parentNode.skip("SKIPED");
			test.skip("SKIPED");
		}
		
		reporter.flush();
		driver.close();
		//driver=null;
	}
	
	@AfterClass(alwaysRun=true)
	public void flushReport()
	{
		//reporter.flush();
	}
	@AfterTest(alwaysRun=true)
	public void teardownTest() {
		System.out.println("===== I am AfterTest =======");
		System.out.println(" ");
		//reporter.flush();
		driver.quit();
		driver=null;
	}
	
}
