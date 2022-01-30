/**
 * This class  contains the methods to perform various function on AUT
 * 
 * @author Subrato
 * @since May 2020
 */

package com.mobile.accelerator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.mobile.base.DriverScript;
import com.mobile.support.ReportUtil;
import com.mobile.utilities.Util;

public class ActionEngine extends DriverScript {

    static SoftAssert softAssert;
    /*		
    ####################################################################################
    ##############################
    # Function Name : sendKeys
    # No of Parameter : 3
    # Description   : To Enter the data in Textfield
    # Developed on : 05/11/2020
    # Developed By : Subrato Sarkar
    ####################################################################################
    ##############################	
    */
    public static boolean sendKeys(String xpath, String value) {
        boolean sendKeys = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                hideKeyboard();
                mobileDriver.findElement(By.xpath(xpath)).sendKeys(value);
                hideKeyboard();
                sendKeys = true;
            } else {
                if (screenName == null) {
                    ReportUtil.markFailed(Util.printStringInDoubleQuotes(value) + " is not entered as the element " + Util.printStringInDoubleQuotes(xpath) + " is not present in DOM");
                } else {
                    ReportUtil.markFailed(Util.printStringInDoubleQuotes(value) + " is not entered as the element " + Util.printStringInDoubleQuotes(xpath) + " is not present in screen: " + Util.printStringInDoubleQuotes(screenName));
                }
                sendKeys = false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'sendKeys' action is failed due to either element: " + Util.printStringInDoubleQuotes(xpath) + " or value to enter: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'sendKeys' action is failed due to either element: " + Util.printStringInDoubleQuotes(xpath) + " or value to enter: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            sendKeys = false;
        }
        return sendKeys;
    }

    /*		
    ####################################################################################
    ##############################
    # Function Name : clear
    # No of Parameter : 1
    # Description   : To clear to the text field
    # Developed on : 08/14/2020
    # Developed By : Subrato Sarkar
    ####################################################################################
    ##############################	
    */
    public static boolean clear(String xpath) {
        boolean sendKeys = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                hideKeyboard();
                mobileDriver.findElement(By.xpath(xpath)).clear();
                hideKeyboard();
                sendKeys = true;
            } else {
                sendKeys = false;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            sendKeys = false;
        }
        return sendKeys;
    }


    public static boolean sendKeysJS(String xpath, String value) {
        boolean sendKeys = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                mobileDriver.findElement(By.xpath(xpath)).clear();
                hideKeyboard();
                JavascriptExecutor jse = (JavascriptExecutor) mobileDriver;
                jse.executeScript("arguments[0].value='" + value + "';", mobileDriver.findElement(By.xpath(xpath)));
                hideKeyboard();
                sendKeys = true;
            } else {
                if (screenName == null) {
                    ReportUtil.markFailed(Util.printStringInDoubleQuotes(value) + " is not entered as the element " + Util.printStringInDoubleQuotes(xpath) + " is not present in DOM");
                } else {
                    ReportUtil.markFailed(Util.printStringInDoubleQuotes(value) + " is not entered as the element " + Util.printStringInDoubleQuotes(xpath) + " is not present in screen: " + Util.printStringInDoubleQuotes(screenName));
                }
                sendKeys = false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'sendKeysJS' action is failed due to either element: " + Util.printStringInDoubleQuotes(xpath) + " or value to enter: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'sendKeysJS' action is failed due to either element: " + Util.printStringInDoubleQuotes(xpath) + " or value to enter: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            sendKeys = false;
        }
        return sendKeys;
    }

    /*		
    ####################################################################################
    ##############################
    # Function Name : click
    # No of Parameter : 2
    # Description   : To click Button or Tab or Link
    # Developed on : 05/11/2020
    # Developed By : Subrato Sarkar
    ####################################################################################
    ##############################	
    */
    public static boolean click(String xpath) {
        boolean click = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                mobileDriver.findElement(By.xpath(xpath)).click();
                click = true;
            } else {
                if (screenName == null) {
                    ReportUtil.markFailed("Element " + xpath + " is NOT clicked");
                } else {
                    ReportUtil.markFailed("Element " + xpath + " is NOT clicked" + " in screen: " + Util.printStringInDoubleQuotes(screenName));
                }
                click = false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("Element " + xpath + " is NOT clicked");
            } else {
                ReportUtil.markFailed("Element " + xpath + " is NOT clicked" + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            click = false;
            t.printStackTrace();
        }
        return click;
    }

    /*		
    ####################################################################################
    ##############################
    # Function Name : doubleClick
    # No of Parameter : 2
    # Description   : To click Button or Tab or Link
    # Developed on : 05/11/2020
    # Developed By : Subrato Sarkar
    ####################################################################################
    ##############################	
    */
    public static boolean doubleClick(String xpath) {
        boolean doubleClick = false;
        doubleClick = TestEngine.isElementExist(xpath);
        try {
            if (doubleClick) {
                Actions action = new Actions(mobileDriver);
                WebElement element = mobileDriver.findElement(By.xpath(xpath));
                action.moveToElement(element);
                action.doubleClick(element).perform();
                doubleClick = true;
            } else {
                ReportUtil.markFailed("Element " + xpath + " is NOT double clicked");
                doubleClick = false;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            ReportUtil.markFailed("The action 'doubleClick' is failed");
            doubleClick = false;
        }
        return doubleClick;
    }


    /*		
    ####################################################################################
    ##############################
     # FunctionName : mouseHover
     # No of Parameter : 2
     # Description  : Function for Move To Element 
     # Developed on : 05/11/2020
     # Developed By : Subrato
     ####################################################################################
     #############################	
    */
    public static boolean mouseHover(String locator) {
        boolean mouseHover;
        try {
            if (TestEngine.isElementExist(locator)) {
                WebElement element = mobileDriver.findElement(By.xpath(locator));
                Actions action = new Actions(mobileDriver);
                action.moveToElement(element).build().perform();
                mouseHover = true;
            } else {
                mouseHover = false;
            }
        } catch (Throwable t) {
            ReportUtil.markFailed("The action 'mouseHover' is failed");
            mouseHover = false;
        }
        return mouseHover;
    }

    /*		
    ####################################################################################
    ##############################
     # FunctionName : switchToFrameByWebElement
     # No of Parameter : 2
     # Description  : Method for switch to a frame based on WebElement.
     # Developed on : 05/11/2020
     # Developed By : Subrato 
     ####################################################################################
     #############################	
    */
    public static boolean switchToFrameByWebElement(String locator) {
        boolean switchToFrameByWebElement = false;
        try {
            WebElement element = mobileDriver.findElement(By.xpath(locator));
            mobileDriver.switchTo().frame(element);
            switchToFrameByWebElement = true;
        } catch (Throwable t) {
            t.printStackTrace();
            ReportUtil.markFailed("The action 'switchToFrameByWebElement' is failed");
            switchToFrameByWebElement = false;
        }
        return switchToFrameByWebElement;
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : clickElement
		 # No of Parameter : 2
		 # Description : To click on an element
		 # Developed on : 05/17/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static boolean clickElement(String xpath) {
        boolean clickElement = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                MobileElement element = (MobileElement) mobileDriver.findElement(By.xpath(xpath));
                ((JavascriptExecutor) mobileDriver).executeScript("arguments[0].click();", element);
                clickElement = true;
            } else {
                ReportUtil.markFailed("Element " + xpath + " is NOT clicked");
                clickElement = false;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            ReportUtil.markFailed("The action 'clickElement' is failed");
            clickElement = false;
        }
        return clickElement;
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : keyPressEnter
		 # No of Parameter : 2
		 # Description : To click on an element
		 # Developed on : 05/20/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static boolean keyPressEnter(String xpath) throws AWTException {
        boolean keyPressEnter = false;
        Robot robot = new Robot();
        try {
            if (TestEngine.isElementExist(xpath)) {
                mouseHover(xpath);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                keyPressEnter = true;
            } else {
                ReportUtil.markFailed("Element " + xpath + " is NOT clicked");
            }
        } catch (Throwable t) {
            keyPressEnter = false;
        }
        return keyPressEnter;
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : getText
		 # No of Parameter : 2
		 # Description : To get the text of an element
		 # Developed on : 05/24/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static String getText(String xpath) {
        try {
            if (TestEngine.isElementExist(xpath)) {
                return mobileDriver.findElement(By.xpath(xpath)).getText().trim();
            } else {
                return null;
            }
        } catch (Throwable t) {
            return null;
        }
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : scrollElement
		 # No of Parameter : 2
		 # Description : To scroll up to the element
		 # Developed on : 05/24/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static boolean scrollElement(String locator, String value) {
        boolean scrollElement = false;
        try {
            @SuppressWarnings("rawtypes")
            AndroidDriver mobileDriver = (AndroidDriver) DriverScript.mobileDriver;
            mobileDriver.findElementByAndroidUIAutomator("new UiScrollable(newUiSelector()).scrollIntoView(new UiSelector().text(\"" + value + "\"));");
            if (TestEngine.isElementExist(locator)) {
                scrollElement = true;
            } else {
                scrollElement = false;
            }
        } catch (Throwable t) {
            scrollElement = false;
        }
        return scrollElement;
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : singleTap
		 # No of Parameter : 2
		 # Description : To scroll up to the element
		 # Developed on : 05/24/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static boolean tap(String xpath) {
        boolean tap = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                TouchAction touchAction = new TouchAction((PerformsTouchActions) mobileDriver);
                @SuppressWarnings("unused")
                TouchAction perform = touchAction.tap(mobileDriver.findElement(By.xpath(xpath))).perform();
                tap = true;
            } else {
                if (screenName == null) {
                    ReportUtil.markFailed("Element : " + xpath + " is not tapped");
                } else {
                    ReportUtil.markFailed("Element : " + xpath + " is not tapped" + " in screen: " + Util.printStringInDoubleQuotes(screenName));
                }
                tap = false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("Element : " + xpath + " is not tapped");
            } else {
                ReportUtil.markFailed("Element : " + xpath + " is not tapped" + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            tap = false;
            t.printStackTrace();
        }
        return tap;
    }


    @SuppressWarnings("rawtypes")
    public static void hideKeyboard() {
        try {
            ((AppiumDriver) mobileDriver).hideKeyboard();
        } catch (Throwable t) {}
    }

    /*		
		####################################################################################
		##############################
		 # FunctionName : touchAndTap
		 # No of Parameter : 1
		 # Description : 
		 # Developed on : 05/19/2020
		 # Developed By : Subrato
		 ####################################################################################
	     #############################	
	*/
    public static boolean touchAndTap(String xpath) {
        boolean touchAndTap = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                TouchAction Action = new TouchAction((PerformsTouchActions) mobileDriver);
                Action.tap(mobileDriver.findElement(By.xpath(xpath))).perform();
                touchAndTap = true;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            ReportUtil.markFailed("The action 'touchAndTap' is failed");
            touchAndTap = false;
        }
        return touchAndTap;
    }


    /*		
    ####################################################################################
    ##############################
     # FunctionName : press
     # No of Parameter : 1
     # Description : 
     # Developed on : 05/19/2020
     # Developed By : Subrato
     ####################################################################################
     #############################	
    */
    public static boolean press(String xpath) {
        boolean press = false;
        try {
            if (TestEngine.isElementExist(xpath)) {
                TouchAction Action = new TouchAction((PerformsTouchActions) mobileDriver);
                Action.press(mobileDriver.findElement(By.xpath(xpath))).perform();
                press = true;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            ReportUtil.markFailed("The action 'press' is failed");
            press = false;
        }
        return press;
    }


    /**
     * @keyword: swipeDown
     * @param: NA
     * @description:  
     * @date: 06/24/2020
     * @author: Surato Sarkar
     */
    public static void swipeDown() {
        try {
            @SuppressWarnings("rawtypes")
            AndroidDriver mobileDriver = (AndroidDriver) DriverScript.mobileDriver;
            Dimension dim = mobileDriver.manage().window().getSize();
            int h = dim.getHeight();
            int w = dim.getWidth();
            int x = w / 2;

            int top_y = (int)(h * 0.8);
            int bottom_y = (int)(h * 0.2);

            TouchAction action = new TouchAction(mobileDriver);
            action.longPress(x, top_y).moveTo(x, bottom_y).release().perform();
            Util.pause(1);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


}