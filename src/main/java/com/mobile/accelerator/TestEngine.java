/**
 * This class  contains generic function that's specific to Selenium RemoteWebDriver and Test set on AUT
 * 
 * @author Subrato
 * @since July 2018
 */

package com.mobile.accelerator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.mobile.base.DriverScript;
import com.mobile.utilities.Util;

public class TestEngine extends DriverScript {

    public static void implicitWait(int timeUnitSeconds) {
        mobileDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void maximizeWindow() {
        mobileDriver.manage().window().maximize();
    }


    public static String getPageSource() {
        return mobileDriver.getPageSource();
    }

    public static String getTitleOfWebPage() {
        return mobileDriver.getTitle();
    }

    public static void navigateBack() {
        mobileDriver.navigate().back();
    }

    public static void navigateForward() {
        mobileDriver.navigate().forward();
    }

    public static void navigateURL(String url) {
        mobileDriver.navigate().to(url);
    }

    public static void deleteCookie() {
        mobileDriver.manage().deleteAllCookies();
    }

    public static String getCurrentUrl() {
        return mobileDriver.getCurrentUrl();
    }

    public static void quit() {
        try {
            continueRun = false;
            mobileDriver.quit();
            mobileDriver = null;
            System.out.println("MobileDriver is terminated...........................");
        } catch (Throwable t) {}
    }

    public static void closeApp() {
        try {
            continueRun = false;
            mobileDriver.close();
            mobileDriver = null;
            System.out.println("Mobile app is closed if already opened...........................");
        } catch (Throwable t) {}
    }


    public static boolean isElementExist(String xpathElement) {
        int nTime = 1;
        boolean isElementExist = false;
        while (nTime <= maxTimeOut) {
            try {
                if (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0) {
                    nTime = maxTimeOut + 1;
                    isElementExist = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isElementExist = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isElementExist = false;
            }
        }
        if (!isElementExist) {
            System.out.println(xpathElement + " is NOT present in DOM");
        }
        return isElementExist;
    }


    public static boolean isElementExist(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isElementExist = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0) {
                    nTime = nEndTime + 1;
                    isElementExist = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isElementExist = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isElementExist = false;
            }
        }
        if (!isElementExist) {
            System.out.println(xpathElement + " is NOT present in DOM");
        }
        return isElementExist;
    }

    public static boolean findElementByXPath(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean findElementByXPath = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElementByXPath(xpathElement).isDisplayed()) {
                    nTime = nEndTime + 1;
                    findElementByXPath = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    findElementByXPath = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                findElementByXPath = false;
            }
        }
        if (!findElementByXPath) {
            System.out.println(xpathElement + " is NOT present in DOM");
        }
        return findElementByXPath;
    }


    public static boolean isDisplayed(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isDisplayed = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
                    nTime = nEndTime + 1;
                    isDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isDisplayed = false;
            }
        }
        if (!isDisplayed) {
            System.out.println(xpathElement + " is NOT present in DOM");
        }
        return isDisplayed;
    }

    public static boolean isDisplayed(String xpathElement) {
        int nTime = 1;
        boolean isDisplayed = false;
        while (nTime <= maxTimeOut) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0)) {

                    nTime = maxTimeOut + 1;
                    isDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isDisplayed = false;
            }
        }
        if (!isDisplayed) {
            System.out.println(xpathElement + " is NOT displayed in DOM");
        }
        return isDisplayed;
    }


    public static boolean resolveStateElement(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean resolveStateElement = false;
        while (nTime <= nEndTime) {
            Util.pause(1);
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
                    //System.out.println(xpathElement+" is present in DOM");
                    nTime = nEndTime + 1;;
                    resolveStateElement = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    resolveStateElement = false;
                }
            } catch (StaleElementReferenceException e) {
                nTime = nTime + 1;
                resolveStateElement = false;
            }
        }
        return resolveStateElement;
    }


    public static boolean isEnabled(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isEnabled = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isEnabled() && (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
                    //System.out.println(xpathElement+" is enabled in DOM");
                    nTime = nEndTime + 1;
                    isEnabled = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isEnabled = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isEnabled = false;
            }
        }
        if (!isEnabled) {
            System.out.println(xpathElement + " is NOT enabled in DOM");
        }
        return isEnabled;
    }


    public static boolean isEnabled(String xpathElement) {
        int nTime = 1;
        boolean isEnabled = false;
        while (nTime <= maxTimeOut) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isEnabled() && (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
                    //System.out.println(xpathElement+" is enabled in DOM");
                    nTime = maxTimeOut + 1;
                    isEnabled = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isEnabled = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isEnabled = false;
            }
        }
        if (!isEnabled) {
            System.out.println(xpathElement + " is NOT enabled in DOM");
        }
        return isEnabled;
    }


    public static boolean isEnabledAndDisplayed(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isEnabledAndDisplayed = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isEnabled() && mobileDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
                    nTime = nEndTime + 1;
                    isEnabledAndDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isEnabledAndDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isEnabledAndDisplayed = false;
            }
        }
        return isEnabledAndDisplayed;
    }


    public static boolean isEnabledAndDisplayed(String xpathElement) {
        int nTime = 1;
        boolean isEnabledAndDisplayed = false;
        while (nTime <= maxTimeOut) {
            try {
                if (mobileDriver.findElement(By.xpath(xpathElement)).isEnabled() && mobileDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
                    nTime = maxTimeOut + 1;
                    isEnabledAndDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isEnabledAndDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isEnabledAndDisplayed = false;
            }
        }
        return isEnabledAndDisplayed;
    }


    public static boolean isExist(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isExist = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElements(By.xpath(xpathElement)).size() > 0) {
                    nTime = nEndTime + 1;
                    isExist = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isExist = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isExist = false;
            }
        }
        if (!isExist) {
            System.out.println(xpathElement + " is NOT present in DOM");
        }
        return isExist;
    }


    public static boolean isNotExist(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isNotExist = false;
        while (nTime <= nEndTime) {
            try {
                if (mobileDriver.findElements(By.xpath(xpathElement)).size() == 0) {
                    nTime = nEndTime + 1;
                    isNotExist = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isNotExist = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isNotExist = false;
            }
        }
        if (!isNotExist) {
            System.out.println(xpathElement + " is still present in DOM");
        }
        return isNotExist;
    }

}