/**
 * This class  contains generic function that's specific to Selenium RemoteWebDriver and Test set on AUT
 * 
 * @author Subrato
 * @since July 2018
 */

package com.web.accelerator;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.mobile.base.DriverScript;
import com.mobile.utilities.Util;

public class TestWeb extends DriverScript {

    public static int maxTimeOut = 100;
    public static boolean isElementExist(String xpathElement) {
        int nTime = 1;
        boolean isElementExist = false;
        while (nTime <= 100) {
            try {
                if (webDriver.findElements(By.xpath(xpathElement)).size() > 0) {
                    nTime = 101;
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
            System.out.println(xpathElement + " is NOT present in DOM============web");
        }
        return isElementExist;
    }


    public static boolean isElementExist(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isElementExist = false;
        while (nTime <= nEndTime) {
            try {
                if (webDriver.findElements(By.xpath(xpathElement)).size() > 0) {
                    nTime = nEndTime + 1;;
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
            System.out.println(xpathElement + " is NOT present in DOM============web");
        }
        return isElementExist;
    }


    public static boolean isDisplayed(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isDisplayed = false;
        while (nTime <= nEndTime) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (webDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
                    nTime = nEndTime + 1;;
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
            System.out.println(xpathElement + " is NOT displayed in DOM============web");
        }
        return isDisplayed;
    }

    public static boolean isDisplayed(String xpathElement) {
        int nTime = 1;
        boolean isDisplayed = false;
        while (nTime <= maxTimeOut) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (webDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
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
            System.out.println(xpathElement + " is NOT displayed in DOM============web");
        }
        return isDisplayed;
    }


    public static boolean resolveStateElement(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean resolveStateElement = false;
        while (nTime <= nEndTime) {
            Util.pause(1);
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isDisplayed() && (webDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
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
        if (!resolveStateElement) {
            System.out.println(xpathElement + " is NOT present in DOM============web");
        }
        return resolveStateElement;
    }


    public static boolean isEnabled(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isEnabled = false;
        while (nTime <= nEndTime) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isEnabled() && (webDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
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
            System.out.println(xpathElement + " is NOT enabled in DOM============web");
        }
        return isEnabled;
    }


    public static boolean isNotEnabled(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isNotEnabled = false;
        while (nTime <= nEndTime) {
            try {
                if (!webDriver.findElement(By.xpath(xpathElement)).isEnabled()) {
                    nTime = nEndTime + 1;
                    isNotEnabled = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isNotEnabled = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isNotEnabled = false;
            }
        }
        if (!isNotEnabled) {
            System.out.println(xpathElement + " is still enabled in DOM============web");
        }
        return isNotEnabled;
    }


    public static boolean isEnabled(String xpathElement) {
        int nTime = 1;
        boolean isEnabled = false;
        while (nTime <= maxTimeOut) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isEnabled() && (webDriver.findElements(By.xpath(xpathElement)).size() > 0)) {
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
            System.out.println(xpathElement + " is NOT enabled in DOM============web");
        }
        return isEnabled;
    }


    public static boolean isNotEnabled(String xpathElement) {
        int nTime = 1;
        boolean isNotEnabled = false;
        while (nTime <= maxTimeOut) {
            try {
                if (!webDriver.findElement(By.xpath(xpathElement)).isEnabled()) {
                    nTime = maxTimeOut + 1;
                    isNotEnabled = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isNotEnabled = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isNotEnabled = false;
            }
        }
        if (!isNotEnabled) {
            System.out.println(xpathElement + " is still enabled in DOM============web");
        }
        return isNotEnabled;
    }


    public static boolean isEnabledAndDisplayed(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isEnabledAndDisplayed = false;
        while (nTime <= nEndTime) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isEnabled() && webDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
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


    public static boolean isNotEnabledAndDisplayed(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isNotEnabledAndDisplayed = false;
        while (nTime <= nEndTime) {
            try {
                if (!webDriver.findElement(By.xpath(xpathElement)).isEnabled() && !webDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
                    nTime = nEndTime + 1;
                    isNotEnabledAndDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isNotEnabledAndDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isNotEnabledAndDisplayed = false;
            }
        }
        return isNotEnabledAndDisplayed;
    }


    public static boolean isEnabledAndDisplayed(String xpathElement) {
        int nTime = 1;
        boolean isEnabledAndDisplayed = false;
        while (nTime <= maxTimeOut) {
            try {
                if (webDriver.findElement(By.xpath(xpathElement)).isEnabled() && webDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
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


    public static boolean isNotEnabledAndDisplayed(String xpathElement) {
        int nTime = 1;
        boolean isNotEnabledAndDisplayed = false;
        while (nTime <= maxTimeOut) {
            try {
                if (!webDriver.findElement(By.xpath(xpathElement)).isEnabled() && !webDriver.findElement(By.xpath(xpathElement)).isDisplayed()) {
                    nTime = maxTimeOut + 1;
                    isNotEnabledAndDisplayed = true;
                    break;
                } else {
                    nTime = nTime + 1;
                    isNotEnabledAndDisplayed = false;
                }
            } catch (Exception e) {
                nTime = nTime + 1;
                isNotEnabledAndDisplayed = false;
            }
        }
        return isNotEnabledAndDisplayed;
    }

    public static boolean isNotExist(String xpathElement, int nEndTime) {
        int nTime = 1;
        boolean isNotExist = false;
        while (nTime <= nEndTime) {
            try {
                if (webDriver.findElements(By.xpath(xpathElement)).size() <= 0) {
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
            System.out.println(xpathElement + " is still exist in DOM============web");
        }
        return isNotExist;
    }

    public static void deleteCookie() {
        webDriver.manage().deleteAllCookies();
    }

    public static String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public static void quit() {
        try {
            continueRun = false;
            webDriver.quit();
            webDriver = null;
            System.out.println("WebDriver is terminated...........................");
        } catch (Throwable t) {}
    }


}