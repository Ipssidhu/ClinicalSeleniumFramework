package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("❌ Test Failed: " + result.getName());

        // Get the test class instance
        Object testClass = result.getInstance();

        // Cast to BaseTest to access driver
        BaseTest baseTest = (BaseTest) testClass;

        // Capture screenshot
        ScreenshotUtil.takeScreenshot(baseTest.driver, result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test Passed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶️ Test Started: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 Test execution finished");
    }
}