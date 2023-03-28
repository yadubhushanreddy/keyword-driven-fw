package org.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.example.ExtentHtmlRepo;
import org.example.Reusables;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Driver
{
    public static void main(String[] args) throws BiffException, IOException, InvocationTargetException, IllegalAccessException {
        //Open Excel file
        File file=new File("Tests.xls");
        Workbook workbook=Workbook.getWorkbook(file);
        Sheet testCasesSheet=workbook.getSheet("TestCases");
        int testCasesRowCount=testCasesSheet.getRows();
        Sheet testStepsSheet=workbook.getSheet("TestSteps");
        int testStepsRowCount=testStepsSheet.getRows();
        //Create objects for Reusables and reflection Method
        Reusables reuse=new Reusables();
        Method m[]=reuse.getClass().getMethods();
        ExtentReports htmlReport = ExtentHtmlRepo.initiateHtmlReport();
        ExtentTest testLogger = null;
        for(int i=1;i<testCasesRowCount; i++)
        {
            String testCaseName=testCasesSheet.getCell(0,i).getContents();
            String mode=testCasesSheet.getCell(2,i).getContents();
            if(mode.equalsIgnoreCase("yes"))
            {
                testLogger = htmlReport.createTest(testCaseName);
                WebDriver driver = null;
                for(int j=1;j<testStepsRowCount;j++)
                {
                    String tid=testStepsSheet.getCell(0,j).getContents();
                    if(testCaseName.equalsIgnoreCase(tid))
                    {
                        String taction=testStepsSheet.getCell(2,j).getContents();
                        String objdesc=testStepsSheet.getCell(3,j).getContents();
                        String testdata=testStepsSheet.getCell(4,j).getContents();
                        String criteria=testStepsSheet.getCell(5,j).getContents();
                            for (int k = 0; k < m.length; k++) {
                                if (m[k].getName().equals(taction))
                                {
                                    if(taction.equalsIgnoreCase("openBrowser"))
                                    {
                                        Object driverReturned = m[k].invoke(reuse, driver, objdesc, testdata, criteria, testLogger);
                                        driver = (WebDriver) driverReturned;
                                        break;
                                    }
                                    else {
                                        m[k].invoke(reuse, driver, objdesc, testdata, criteria, testLogger);
                                        break;
                                    }
                                }
                            }
                        }
                }
            }
        }
        htmlReport.flush();
        workbook.close();
    }
}
