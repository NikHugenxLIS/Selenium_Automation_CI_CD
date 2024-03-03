package com.web.commonUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends ScreenShotUtility implements ITestListener
{
    ExtentTest test;
    ExtentReports extent = ExtentReporter.getReporteObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  // Thread safe --->>
    @Override
    public void onTestStart(ITestResult result)
    {
         test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);  // Assign unique id () -test
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        String filePath ;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(),driver);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
       // extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

    }

    @Override
    public void onTestSkipped(ITestResult result)
    {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
}
