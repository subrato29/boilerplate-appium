package com.mobile.reports;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.mobile.base.DriverScript;
import com.mobile.utilities.Util;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    private static ExtentReports extent;
    public static String dynamicHtmlReportPath;
    public static String reportFolderPath = null;

    public static ExtentReports getInstance() {
        if (extent == null) {
            dynamicHtmlReportPath = htmlReportPath();
            extent.loadConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"));
            try {
                extent
                    .addSystemInfo("Environment", Util.getProperty("test_environment"))
                    .addSystemInfo("Automation tools used", Util.getProperty("automation_tool_used"))
                    .addSystemInfo("Nature of AUT", Util.getProperty("nature_of_aut"))
                    .addSystemInfo("Name of the AUT", Util.getProperty("name_of_aut"))
                    .addSystemInfo("Execution Platform", DriverScript.executionPlatform)
                    .addSystemInfo("Mobile OS Platform", DriverScript.mobileOSPlatform)
                    .addSystemInfo("Device Platform", DriverScript.devicePlatform);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return extent;
    }


    public static String htmlReportPath() {
        String reportPrefix = DriverScript.testCaseId + "_Report_";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String strDate = sdf.format(cal.getTime());
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        sdf1.applyPattern("MM/dd/yyyy HH:mm:ss");
        Date date = null;
        try {
            date = sdf1.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String strDateNow = sdf1.format(date);
        String timeFormat = strDateNow.toString().replace("/", "_").replace(":", "_").replace(" ", "_");
        String fileName = reportPrefix + timeFormat + ".html";
        reportFolderPath = System.getProperty("user.dir") + File.separator + "Results" + File.separator + reportPrefix + timeFormat;
        File dir = new File(reportFolderPath);
        dir.mkdir();
        extent = new ExtentReports(reportFolderPath + File.separator + fileName);
        return (reportFolderPath + File.separator + fileName);
    }

    /*
     ####################################################################################
     ##############################
     # Function Name : screenshotCapture
     # Description   : This function will take screenshot of the mobile app
     # Developed on : 05/11/2020
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static String screeshotCapture() throws Exception {
        String pathOfScreenshotCaptured;
        File source_file = null;
        try {
            Util.createNewDirectory(reportFolderPath + "/Screenshot");
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Calendar now = Calendar.getInstance();
            String screenshotFileExtension = "Screenshot_" + formatter.format(now.getTime()).toString().replace("/", "_").replace(":", "_").replace(" ", "_") + ".jpg";
            if (DriverScript.mobileDriver != null) {
                source_file = ((TakesScreenshot) DriverScript.mobileDriver).getScreenshotAs(OutputType.FILE);
            } else if (DriverScript.webDriver != null) {
                source_file = ((TakesScreenshot) DriverScript.webDriver).getScreenshotAs(OutputType.FILE);
            }
            FileUtils.copyFile(source_file, new File(reportFolderPath + "/Screenshot/" + screenshotFileExtension));
            pathOfScreenshotCaptured = "Screenshot/" + screenshotFileExtension;
        } catch (Throwable t) {
            pathOfScreenshotCaptured = null;
        }
        return pathOfScreenshotCaptured;
    }

}