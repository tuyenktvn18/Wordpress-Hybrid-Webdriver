package commons.reportConfig;

import commons.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureTestListener implements ITestListener {

    @Attachment(value = "Screenshot of {0}", type = "image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriverInstance();
        driver.getCurrentUrl();
        saveScreenshotPNG(iTestResult.getName(), driver);
        // saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

}