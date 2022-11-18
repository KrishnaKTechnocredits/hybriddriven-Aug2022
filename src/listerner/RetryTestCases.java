package listerner;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class RetryTestCases implements IRetryAnalyzer, IAnnotationTransformer {

	static int retryCount = 0;
	static int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			if (retryCount < maxRetryCount) {
				retryCount++;
				return true;
			}
		}
		return false;
	}

	@Override
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod,
			Class<?> occurringClazz) {
		testAnnotation.setRetryAnalyzer(RetryTestCases.class);
	}

}
