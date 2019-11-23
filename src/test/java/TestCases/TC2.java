package TestCases;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;

public class TC2 extends TestBase {

	@Test(groups= {"smoke"})
	public void step1(Method m) {
		System.out.println("STEP 1 in TESTCASE 2");
		//log = reporter.createTest(m.getName());
		//log.createNode(m.getName());
		
		//Assert.assertEquals(true,driver.findElement(By.xpath("//a[@class='siteLogo']")).isDisplayed());
		Assert.assertEquals(true, false);
	}
	@Test(groups= {"regression"})
	public void step2(Method m) {
		System.out.println("STEP 2 in TESTCASE 2");
		//log = reporter.createTest(m.getName());
		//log.createNode(m.getName());
		
		Assert.assertEquals(true,driver.findElement(By.xpath("//a[@class='siteLogo']")).isDisplayed());
		//Assert.assertEquals(true, false);
	}
}
