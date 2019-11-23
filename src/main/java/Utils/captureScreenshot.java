package Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.utils.FileUtil;
import com.google.common.io.Files;

public class captureScreenshot {
	
	private WebDriver driver=null;
	
	public captureScreenshot(WebDriver driver) {
		this.driver = driver;
	}

	public  String takeScreenshot(String screenshotName) throws IOException 
	{
		String dt = new SimpleDateFormat("MM dd yyyy hh mm ss").format(new Date());
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		dt = dt.replaceAll(" ", "-");
		String reportLoc = System.getProperty("user.dir")+"\\Reports\\Screenshots\\"+screenshotName+"_"+dt;
		File destFile = new File(reportLoc+".png");
		Files.copy(sourceFile, destFile);
		//Files.copy(sourceFile,destFile.toPath());
		return reportLoc;
	
	}
}
