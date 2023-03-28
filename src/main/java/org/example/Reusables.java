package org.example;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Reusables
{


    public WebDriver openBrowser(WebDriver driver, String locator, String testData, String criteria, ExtentTest testLogger)
    {
        try
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            testLogger.log(Status.PASS, criteria+" Successful");
        }
        catch(Exception e)
        {
            System.out.println(criteria+" Failed");
            testLogger.log(Status.FAIL, criteria+" Failed");
        }
        return driver;
    }

    public void navigateToUrl(WebDriver driver, String locator, String testData, String criteria, ExtentTest testLogger)
    {
        try
        {
            driver.get(testData);
            testLogger.log(Status.PASS, criteria+" to "+testData+" Successful");
        }
        catch (Exception e)
        {
            System.out.println(criteria+" to "+testData+" Failed");
            testLogger.log(Status.FAIL, criteria+" to "+testData+" Failed");
        }
    }

    public void clickElement(WebDriver driver, String locator, String testData, String criteria, ExtentTest testLogger)
    {
        try
        {
            driver.findElement(By.xpath(locator)).click();
            testLogger.log(Status.PASS, criteria+" is Successful");
        }
        catch(Exception e)
        {
            System.out.println(criteria+" Failed");
            testLogger.log(Status.FAIL, criteria+" Failed");
        }
    }

    public void enterData(WebDriver driver, String locator, String testData, String criteria, ExtentTest testLogger)
    {
        try
        {
            driver.findElement(By.xpath(locator)).sendKeys(testData);
            testLogger.log(Status.PASS, criteria+" is Successful");
        }
        catch(Exception e)
        {
            System.out.println(criteria+" Failed");
            testLogger.log(Status.FAIL, criteria+" Failed");
        }
    }



    public void closeBrowser(WebDriver driver, String locator, String testData, String criteria, ExtentTest testLogger)
    {
        try
        {
            driver.quit();
            testLogger.log(Status.PASS, criteria+" Successful");
        }
        catch(Exception e)
        {
            System.out.println("Failed closing browser session");
            testLogger.log(Status.FAIL, criteria+" Failed");
        }
    }
}