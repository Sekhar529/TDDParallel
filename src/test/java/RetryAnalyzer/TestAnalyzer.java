package RetryAnalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestAnalyzer implements IRetryAnalyzer{

	int counter = 0;
	int retryCount = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		if(counter<retryCount)
		{
			counter++;
			return true;
		}	
		return false;
	}

}
