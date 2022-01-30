package com.mobile.base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.mobile.accelerator.*;
import com.mobile.support.ReportUtil;
import com.mobile.support.Xls_Reader;
import com.mobile.utilities.Constants;
import com.mobile.utilities.Util;
import com.mobile.utilities.Zip;
import com.web.accelerator.TestWeb;
import com.web.support.WebDriverFactory;

public class DriverScript {

    public static String TEST_DATA_PATH = System.getProperty("user.dir") + "/src/main/java/com/mobile/data";
    public static Xls_Reader xls = null, xlsController = new Xls_Reader(TEST_DATA_PATH + File.separator + "testcontroller.xlsx");
    public static WebDriver webDriver = null;
    public static RemoteWebDriver mobileDriver = null;
    public static int rowNum = 2, rowNumController = 2;
    public static int rowNumExecutableTC = 2;
    public static int count = 0;
    public static String testCaseName;
    public static String testCaseId, testType;
    public static SoftAssert softAssert;
    public static int errorCount = 0;
    public static DesiredCapabilities capabilities = null;
    public static boolean continueRun = false;
    public static boolean logout = false;
    public static String mobileOSPlatform = null, executionPlatform = null, devicePlatform = null;
    public static int maxTimeOut = 0;
    public static boolean isMobileStarted = false, isWebStarted = false;
    public static String BROWSER = null, baseUrl = null, testEnv = null, screenName = null;

    static TreeMap < Integer, String > executableTCIndex = new TreeMap < Integer, String > ();
    public static int getRowNumForExecutableTestCases() {
        while (rowNumExecutableTC <= xlsController.getRowCount(Constants.TEST_DATA)) {
            if (xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNumExecutableTC).toUpperCase().equals(Constants.TEST_CASE_RUNMODE_YES)) {
                count++;
            }
            rowNumExecutableTC++;
        }
        rowNumExecutableTC = 2;
        return count;
    }

    public static boolean isTestCaseRunnable(String tcId) {
        boolean isTestCaseRunnable = false;
        isMobileStarted = false;
        continueRun = false;
        rowNumController = xlsController.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
        rowNum = rowNumController;
        testCaseId = tcId;
        testCaseName = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
        testType = xlsController.getCellData(Constants.TEST_DATA, "TestType", rowNum);
        if (xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
            TestEngine.quit();
            TestWeb.quit();
            isMobileStarted = true;
            mobileDriver = null;
            if (mobileDriver == null) {
                try {
                    String gridurl = "";
                    String userName = null, pw = null;
                    String deviceName = null, deviceId = null, udid = null;
                    String packageName = null, appActivity = null, bundleId = null, bundleActivity = null;

                    int implicitWait = Integer.parseInt(Util.getProperty("MobileImplicitWait").toString());

                    if (Util.ucase(mobileOSPlatform).equals("ANDROID") || Util.ucase(mobileOSPlatform).equals("IOS")) {
                        if (devicePlatform.toUpperCase().equals("MC")) {
                            userName = Util.getGlobalProperty("MC_Username");
                            pw = Util.decrypt(Util.getGlobalProperty("MC_Password"));
                            if (Util.ucase(mobileOSPlatform).equals("ANDROID")) {
                                boolean appInstalled = true;
                                if (appInstalled) {
                                    packageName = "com.***.***";
                                    appActivity = "com.***.***.MainActivity";
                                    deviceId = Util.getProperty("DeviceID");
                                    deviceName = Util.getProperty("DeviceName");
                                }
                            } else {
                                boolean appInstalled = true;
                                if (appInstalled) {
                                    bundleId = "com.***.***";
                                    bundleActivity = "com.***.***.MainActivity";
                                    udid = Util.getProperty("IOS_UDID");
                                    deviceId = Util.getProperty("IOS_DeviceID");
                                }
                            }
                        } else if (devicePlatform.toUpperCase().equals("E")) {
                            packageName = "com.***.***";
                            appActivity = "com.***.***.MainActivity";
                            deviceId = "emulator-5554";
                            deviceName = "NA";
                        }
                        if (Util.ucase(mobileOSPlatform).equals("ANDROID")) {
                            capabilities = DesiredCapabilities.android();
                            capabilities.setCapability("deviceType", "androiddevice");
                            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "androiddevice");
                            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, packageName);
                            capabilities.setCapability("appPackage", packageName);
                            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
                            capabilities.setCapability(AndroidMobileCapabilityType.DEVICE_READY_TIMEOUT, 400);
                            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
                            capabilities.setCapability(MobileCapabilityType.NO_RESET, "True");
                            capabilities.setCapability(MobileCapabilityType.FULL_RESET, "False");
                            capabilities.setCapability("automationName", "appium");
                            capabilities.setCapability("udid", deviceId);
                            capabilities.setCapability("deviceUDID", deviceId);
                            capabilities.setCapability("deviceName", deviceName);
                            capabilities.setCapability("resetKeyboard", true);
                            capabilities.setCapability("unicodeKeyboard", true);
                            if (devicePlatform.toUpperCase().equals("MC")) {
                                capabilities.setCapability("userName", userName);
                                capabilities.setCapability("password", pw);
                            }
                            capabilities.setCapability("skipUnlock", true);
                            capabilities.setCapability("autoAcceptAlerts", true);
                            capabilities.setCapability("deviceOrientation", "portrait");
                            capabilities.setCapability("autoAcceptAlerts", true);
                            capabilities.setCapability("autoDismissAlerts", true);
                            System.out.println("Username: " + userName);
                        } else if (Util.ucase(mobileOSPlatform).equals("IOS")) {
                            capabilities = DesiredCapabilities.iphone();
                            capabilities.setCapability("deviceName", "iPhone X");
                            capabilities.setCapability("deviceUDID", udid);
                            capabilities.setCapability("userName", userName);
                            capabilities.setCapability("password", pw);
                            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
                            capabilities.setCapability("deviceManufacturer", "Apple");
                            capabilities.setCapability("deviceModel", "iPhone");
                            capabilities.setCapability("useNewWDA", false);
                            capabilities.setCapability("bundleId", bundleId);
                            capabilities.setCapability(MobileCapabilityType.PLATFORM, "MAC");
                            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                            capabilities.setCapability("automationName", "XCUITest");
                            capabilities.setCapability("udid", deviceId);
                            capabilities.setCapability("deviceUDID", deviceId);
                            capabilities.setCapability("resetKeyboard", true);
                            capabilities.setCapability("unicodeKeyboard", true);
                            capabilities = DesiredCapabilities.iphone();
                            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
                            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.3.1");
                            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                            capabilities.setCapability("udid", udid);
                            capabilities.setCapability("bundleId", bundleId);
                            if (devicePlatform.toUpperCase().equals("MC")) {
                                capabilities.setCapability("userName", userName);
                                capabilities.setCapability("password", pw);
                            }
                            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
                        }
                        if (Util.ucase(executionPlatform).equals("LOCAL")) {
                            if (devicePlatform.toUpperCase().equals("MC")) {
                                gridurl = "https://***.***.com:443/wd/hub";
                                System.out.println(gridurl + " ======= mobile center");
                            } else if (devicePlatform.toUpperCase().equals("E")) {
                                gridurl = "http://127.0.0.1:4723/wd/hub";
                                System.out.println(gridurl + " ======= emulator");
                            }
                            System.out.println(capabilities);

                            if (Util.ucase(mobileOSPlatform).equals("ANDROID")) {
                                mobileDriver = new AndroidDriver < AndroidElement > (new URL(gridurl), capabilities);
                                mobileDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
                                continueRun = true;
                                System.out.println("Android driver is instantiated ...................................................");
                                isTestCaseRunnable = true;
                            } else {
                                mobileDriver = new IOSDriver < IOSElement > (new URL(gridurl), capabilities);
                                mobileDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
                                continueRun = true;
                                System.out.println("IOS driver is instantiated..........................................................");
                                isTestCaseRunnable = true;
                            }
                            System.out.println("Test case picked up==============================" + testCaseId + "==============================" + testCaseName);
                            System.out.println("===================================================================================================");
                            maxTimeOut = getMaxTimeOut();
                            xls = new Xls_Reader(TEST_DATA_PATH + File.separator + getTestDataSheetName() + ".xlsx");
                            rowNum = xls.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, testCaseId);
                        }
                    }
                } catch (Throwable t) {
                    continueRun = true;
                    ReportUtil.markFailed("MobileDriver has not been instantiated.");
                    t.printStackTrace();
                }
            }
        } else {
            System.out.println("Please check the runmode of TestCaseID '" + testCaseId + "'");
            mobileDriver = null;
            isTestCaseRunnable = false;
        }
        return isTestCaseRunnable;
    }

    public static final int countOfExecutableTestCases = getRowNumForExecutableTestCases();


    public static int getMaxTimeOut() {
        int maxTimeOut;
        try {
            maxTimeOut = Integer.parseInt(Util.getProperty("MaxTimeOut"));
        } catch (Throwable t) {
            maxTimeOut = 2;
        }
        return maxTimeOut;
    }

    public static String getTestDataSheetName() {
        String testDataSheet = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_DATA_SHEET_NAME, rowNum);
        return testDataSheet;
    }


    @BeforeMethod
    public void beforeMethod() {

    }

    @AfterMethod
    public void afterMethod() {
        TestEngine.quit();
        TestWeb.quit();
        ReportUtil.test = null;
    }


    @AfterClass()
    public void afterClass() throws IOException {
        TestEngine.quit();
        TestWeb.quit();
        Util.openHTMLReport();
        Zip.zipFile();
    }


    @BeforeClass()
    public void init() throws IOException {
        executionPlatform = Util.getGlobalProperty("ExecutionPlatform");
        mobileOSPlatform = Util.getProperty("MobileOSPlatform");
        devicePlatform = Util.getGlobalProperty("DevicePlatform");
    }

    /**
     * 
     * @param tcId
     * @param app =>
     * @return
     */
    public static boolean isRunnableWeb(String tcId, String app) {
        boolean isTestCaseRunnable = false;
        try {
            BROWSER = Util.getProperty("Browser");
        } catch (IOException e) {}
        isWebStarted = false;
        continueRun = false;
        rowNumController = xlsController.getCellRowNum(Constants.TEST_DATA, Constants.TEST_CASE_ID, tcId);
        rowNum = rowNumController;
        testCaseId = tcId;
        testCaseName = xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_NAME, rowNum);
        if (xlsController.getCellData(Constants.TEST_DATA, Constants.TEST_CASE_RUNMODE, rowNum).equalsIgnoreCase(Constants.TEST_CASE_RUNMODE_YES)) {
            TestEngine.quit();
            TestWeb.quit();
            isWebStarted = true;
            webDriver = null;
            if (webDriver == null) {
                try {
                    testEnv = Util.getProperty("test_environment").toUpperCase();
                } catch (IOException e) {}
                isTestCaseRunnable = true;
                xls = new Xls_Reader(TEST_DATA_PATH + File.separator + getTestDataSheetName() + ".xlsx");
                initializeTest();
            }
        } else {
            webDriver = null;
            isTestCaseRunnable = false;
        }
        return isTestCaseRunnable;
    }

    public static void initializeTest() {
        continueRun = true;
        try {
            if (Util.getGlobalProperty("ExecutionPlatform").toUpperCase().equals("JENKINS")) {
                System.setProperty("webdriver.chrome.silentOutput", "true");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
                ChromeOptions opt = new ChromeOptions();
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("chromeOptions", opt);
                capabilities.setBrowserName("chrome");
                opt.addArguments("--headless", "--no-sandbox", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
                webDriver = new ChromeDriver(opt);
                TestWeb.deleteCookie();
                webDriver.get(baseUrl);
                System.out.println("WebDriver is instantiated..........");
            } else {
                try {
                    if (Util.getProperty("HeadlessBrowser").toUpperCase().equals("YES")) {
                        System.setProperty("webdriver.chrome.silentOutput", "true");
                        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
                        ChromeOptions opt = new ChromeOptions();
                        opt.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
                        webDriver = new ChromeDriver(opt);
                        webDriver.get(baseUrl);
                        System.out.println("WebDriver is instantiated..........");
                    } else {
                        WebDriverFactory.initialize();
                        System.out.println("WebDriver is instantiated..........");
                    }
                } catch (IOException e) {
                    continueRun = false;
                    ReportUtil.markFailed("WebDriver has not been instantiated.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            continueRun = false;
            ReportUtil.markFailed("WebDriver has not been instantiated.");
            e.printStackTrace();
        }
    }

}