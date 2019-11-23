package Reporters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class extentReporter {
	
	public  ExtentHtmlReporter htmlReporter;
	public  ExtentReports reporter;
	public  ExtentTest log;
	
	DateFormat df = new SimpleDateFormat("MM_dd_yy HH-mm-ss");
	//Date dateobj = new Date();
	String date = df.format(new Date());
	String workingDir = System.getProperty("user.dir");
	String finalPath = workingDir+"\\Reports\\"+date+"\\extentReport.html";
	
	public final String reporterPath = finalPath;
	

}
