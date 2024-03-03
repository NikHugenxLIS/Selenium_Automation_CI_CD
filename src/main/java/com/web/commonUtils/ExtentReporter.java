package com.web.commonUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter
{
    public static ExtentReports getReporteObject()
    {
        //ExtentReports ExtentSparkReporter
        String path = System.getProperty("user.dir")+ "\\reports\\ index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Author", "Nikki Kumar");

        return extent;
    }
}
