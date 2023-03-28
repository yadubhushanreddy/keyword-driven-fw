package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentHtmlRepo
{

    public static ExtentReports initiateHtmlReport()
    {
        ExtentReports extentReport = null;
        ExtentSparkReporter sparkReporter;
        try {
            extentReport = new ExtentReports();
            sparkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")) + "/TestRunReport.html");
            extentReport.attachReporter(sparkReporter);
        }
        catch(Exception e)
        {
            System.out.println("Setting up HTML report failed");
        }

        return extentReport;
    }





}
