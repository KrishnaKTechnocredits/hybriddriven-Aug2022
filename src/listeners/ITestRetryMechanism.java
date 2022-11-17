package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class ITestRetryMechanism implements IRetryAnalyzer, IAnnotationTransformer {

	static int retryCount = 0;
	static int maxRetryCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			if (retryCount <= maxRetryCount) {
				retryCount++;
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod,
			Class<?> occurringClazz) {
		annotation.setRetryAnalyzer(ITestRetryMechanism.class);
	}

}
