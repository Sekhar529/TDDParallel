package TestCases;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.TestBase;


public class TC1 extends TestBase {

	@Test(groups= {"smoke"})
	public void step1(Method m) {
		System.out.println("STEP 1 In TESTCASE 1");
		//log = reporter.createTest(m.getName());
		//node.createNode(m.getName());
		//log = reporter.createTest(m.getName());
		Assert.assertEquals(driver.getTitle(), "Fresherslive - Exclusive Job Portal for Freshers in India");
	}
	@Test(groups= {"regression"})
	public void step2(Method m) {
		System.out.println("STEP 2 In TESTCASE 1");
		//log = reporter.createTest(m.getName());
		//log.createNode(m.getName());
		//log = reporter.createTest(m.getName());
		Assert.assertEquals(driver.getTitle(), "Fresherslive - Exclusive Job Portal for Freshers in India");
	}
}
