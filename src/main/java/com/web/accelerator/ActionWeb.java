/**
 * This class  contains the methods to perform various function on AUT
 * 
 * @author Subrato
 * @since May 2020
 */

package com.web.accelerator;
import com.mobile.base.DriverScript;

import org.openqa.selenium.support.ui.Select;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.mobile.support.ReportUtil;
import com.mobile.utilities.*;

public class ActionWeb extends DriverScript {

    /*
     ####################################################################################
     ##############################
     # Function Name : sendKeys
     # No of Parameter : 2
     # Description   : To Enter the data in Textfield
     # Developed on : 04/10/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean sendKeys(String locator, String value) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                webDriver.findElement(By.xpath(locator)).clear();
                webDriver.findElement(By.xpath(locator)).sendKeys(value);
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'sendKeys' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'sendKeys' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            return false;
        }
    }
    /*
     ####################################################################################
     ##############################
     # Function Name : clearText
     # No of Parameter : 2
     # Description   : To clear the text in textbox
     # Developed on : 11/06/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean clearText(String locator) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                webDriver.findElement(By.xpath(locator)).clear();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            return false;
        }
    }
    /*
     ####################################################################################
     ##############################
     # Function Name : sendKeysByJavaScriptExecutor
     # No of Parameter : 2
     # Description   : To Enter the data in Textfield
     # Developed on : 04/10/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean sendKeysJS(String locator, String value) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                JavascriptExecutor jse = (JavascriptExecutor) webDriver;
                jse.executeScript("arguments[0].value='" + value + "';", webDriver.findElement(By.xpath(locator)));
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'sendKeysJS' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'sendKeysJS' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            return false;
        }
    }


    /*
     ####################################################################################
     ##############################
     # Function Name : sendKeysActions
     # No of Parameter : 2
     # Description   : To Enter the data in Textfield
     # Developed on : 10/30/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean sendKeysActions(String locator, String value) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                Actions actions = new Actions(webDriver);
                actions.moveToElement(webDriver.findElement(By.xpath(locator)));
                actions.click();
                actions.sendKeys(value);
                actions.build().perform();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'sendKeysActions' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'sendKeysActions' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to enter: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            return false;
        }
    }


    /*
     ####################################################################################
     ##############################
     # FunctionName : selectByValue
     # No of Parameter : 3
     # Description : Function to select a value from a DropDown based on value.
     # Developed on : 07/13/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean selectByValue(String locator, String value) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                Select select = new Select(webDriver.findElement(By.xpath(locator)));
                select.selectByValue(value);
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'selectByValue' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'selectByValue' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            return false;
        }
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : selectByVisibleText
     # No of Parameter : 3
     # Description : Function to select a value from a DropDown based on value.
     # Developed on : 07/23/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean selectByVisibleText(String locator, String value) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                Select select = new Select(webDriver.findElement(By.xpath(locator)));
                select.selectByVisibleText(value);
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'selectByVisibleText' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(value));
            } else {
                ReportUtil.markFailed("'selectByVisibleText' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(value) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            t.printStackTrace();
            return false;
        }
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : selectValueByPartialText
     # No of Parameter : 3
     # Description : Function to select a value from a DropDown based on value.
     # Developed on : 07/23/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean selectValueByPartialText(String locator, String patialText) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                webDriver.findElement(By.xpath(locator + "/option[contains(text(),'" + patialText + "')]")).click();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            if (screenName == null) {
                ReportUtil.markFailed("'selectValueByPartialText' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(patialText));
            } else {
                ReportUtil.markFailed("'selectByVisibleText' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or value to select: " + Util.printStringInDoubleQuotes(patialText) + " in screen: " + Util.printStringInDoubleQuotes(screenName));
            }
            return false;
        }
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : selectByIndex
     # No of Parameter : 3
     # Description : Function to select a value from a DropDown based on value.
     # Developed on : 07/23/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean selectByIndex(String locator, int index) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                Select select = new Select(webDriver.findElement(By.xpath(locator)));
                select.selectByIndex(index);
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            ReportUtil.markFailed("'selectByIndex' action is failed due to either element: " + Util.printStringInDoubleQuotes(locator) + " or index to select: " + Util.printStringInDoubleQuotes(String.valueOf(index)));
            return false;
        }
    }


    /*
     ####################################################################################
     ##############################
     # Function Name : click
     # No of Parameter : 2
     # Description   : To click Button or Tab or Link
     # Developed on : 07/11/2018
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean click(String locator) {
        try {
            if (TestWeb.isDisplayed(locator)) {
                webDriver.findElement(By.xpath(locator)).click();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            return false;
        }

    }


    /*
     ####################################################################################
     ##############################
     # Function Name : doubleClick
     # No of Parameter : 2
     # Description   : To double click Button or Tab or Link
     # Developed on : 07/11/2018
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean doubleClick(String locator) {
        try {
            if (TestWeb.isDisplayed(locator)) {
                Util.pause(1);
                Actions action = new Actions(webDriver);
                WebElement element = webDriver.findElement(By.xpath(locator));
                action.doubleClick(element).perform();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            return false;
        }

    }

    /*
     ####################################################################################
     ##############################
     # Function Name : mouseHover
     # No of Parameter : 1
     # Description   : Mouse hovering on element
     # Developed on : 09/02/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean mouseHover(String locator) {
        try {
            if (TestWeb.isDisplayed(locator)) {
                Actions action = new Actions(webDriver);
                WebElement element = webDriver.findElement(By.xpath(locator));
                action.moveToElement(element).perform();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            return false;
        }

    }

    /*
     ####################################################################################
     ##############################
     # Function Name : mouseHoverClick
     # No of Parameter : 1
     # Description   : Mouse hovering on element and click
     # Developed on : 09/02/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean mouseHoverClick(String locator) {
        try {
            if (TestWeb.isDisplayed(locator)) {
                Actions action = new Actions(webDriver);
                WebElement element = webDriver.findElement(By.xpath(locator));
                action.moveToElement(element).click().perform();
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            return false;
        }

    }


    /*
     ####################################################################################
     ##############################
     # FunctionName : clickElement
     # No of Parameter : 2
     # Description : To click on an element
     # Developed on : 07/17/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean clickElement(String locator) {
        boolean clickElement = false;
        try {
            if (TestWeb.isDisplayed(locator)) {
                WebElement element = webDriver.findElement(By.xpath(locator));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
                clickElement = true;
            } else {
                clickElement = false;
            }
        } catch (Throwable t) {
            clickElement = false;
        }
        return clickElement;
    }


    /*
     ####################################################################################
     ##############################
     # FunctionName : clickPerform
     # No of Parameter : 2
     # Description : To click on an element
     # Developed on : 10/10/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean clickPerform(String locator) {
        boolean clickPerform = false;
        try {
            if (TestWeb.isDisplayed(locator)) {
                WebElement element = webDriver.findElement(By.xpath(locator));
                Actions builder = new Actions(webDriver);
                builder.click(element).build().perform();
                clickPerform = true;
            } else {
                clickPerform = false;
            }
        } catch (Throwable t) {
            clickPerform = false;
        }
        return clickPerform;
    }


    /*
     ####################################################################################
     ##############################
     # FunctionName : getText
     # No of Parameter : 2
     # Description : To get the text of an element
     # Developed on : 07/24/2018
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static String getText(String locator) {
        try {
            locator = locator + "[not(@disabled)]";
            if (TestWeb.isDisplayed(locator)) {
                return webDriver.findElement(By.xpath(locator)).getText().trim();
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
     # No of Parameter : 1
     # Description : To scroll up to the element
     # Developed on : 04/15/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean scrollElement(String locator) {
        boolean scrollElement = false;
        try {
            if (Util.getProperty("HeadlessBrowser").toUpperCase().equals("YES")) {
                if (TestWeb.isDisplayed(locator)) {
                    WebElement element = webDriver.findElement(By.xpath(locator));
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    js.executeScript("arguments[0].scrollIntoView(true);", element);
                    scrollElement = true;
                } else {
                    scrollElement = false;
                }
            } else {
                scrollElement = true;
            }

        } catch (Throwable t) {
            scrollElement = false;
        }
        return scrollElement;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : keyPressEnter
     # No of Parameter : 2
     # Description : To click on an element
     # Developed on : 04/18/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean keyPressEnter() throws AWTException {
        boolean keyPressEnter = false;
        Robot robot = new Robot();
        try {
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            keyPressEnter = true;
        } catch (Throwable t) {
            keyPressEnter = false;
        }
        return keyPressEnter;
    }

    /*
     ####################################################################################
     ##############################
     # Function Name : isAlertAccepted
     # No of Parameter : 0
     # Description   : Alert acceptance
     # Developed on : 05/03/2019
     # Author : Subrato Sarkar
     ####################################################################################
     ##############################
     */
    public static boolean isAlertAccepted() {
        boolean alertAccept = false;
        try {
            webDriver.switchTo().alert().accept();
            alertAccept = true;
        } catch (Throwable t) {
            alertAccept = false;
        }
        return alertAccept;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : getAttribute
     # No of Parameter : 2
     # Description : Get the attribute of the element
     # Developed on : 05/20/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static String getAttribute(String locator, String attribute) {
        try {
            if (TestWeb.isDisplayed(locator)) {
                return webDriver.findElement(By.xpath(locator)).getAttribute(attribute).trim();
            } else {
                return null;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }


    /*
     ####################################################################################
     ##############################
     # FunctionName : isExistTableContent
     # No of Parameter : 2
     # Description : Verify existance of table content
     # Developed on : 10/15/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static int isExistTableContent(String locator, int timeout) {
        int rowCount = 0;
        try {
            if (TestWeb.isDisplayed(locator, timeout)) {
                int row = webDriver.findElements(By.xpath(locator)).size();
                if (row > 0) {
                    rowCount = row;
                } else {
                    rowCount = 0;
                }
            }
        } catch (Throwable t) {
            rowCount = 0;
        }
        return rowCount;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : isExistTableContent
     # No of Parameter : 2
     # Description : Verify existance of table content
     # Developed on : 10/15/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static int isExistTableContent(String locator) {
        int rowCount = 0;
        try {
            if (TestWeb.isDisplayed(locator)) {
                int row = webDriver.findElements(By.xpath(locator)).size();
                if (row > 0) {
                    rowCount = row;
                } else {
                    rowCount = 0;
                }
            }
        } catch (Throwable t) {
            rowCount = 0;
        }
        return rowCount;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : multiClickElement
     # No of Parameter : 2
     # Description : Multiple click
     # Developed on : 11/06/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean multiClickElement(String locatorToClick, String locatorToVerifyAfterClick) {
        boolean multiClickElement = false;
        for (int i = 0; i < 3; i++) {
            ActionWeb.mouseHover(locatorToClick);
            ActionWeb.clickElement(locatorToClick);
            if (TestWeb.isDisplayed(locatorToVerifyAfterClick, 500)) {
                multiClickElement = true;
                break;
            }
        }
        return multiClickElement;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : multiClickPerform
     # No of Parameter : 2
     # Description : Multiple click
     # Developed on : 11/06/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean multiClickPerform(String locatorToClick, String locatorToVerifyAfterClick) {
        boolean multiClickPerform = false;
        for (int i = 0; i < 3; i++) {
            ActionWeb.mouseHover(locatorToClick);
            ActionWeb.clickPerform(locatorToClick);
            if (TestWeb.isDisplayed(locatorToVerifyAfterClick, 500)) {
                multiClickPerform = true;
                break;
            }
        }
        return multiClickPerform;
    }

    /*
     ####################################################################################
     ##############################
     # FunctionName : isSelected
     # No of Parameter : 2
     # Description : Selenium how to check the drop down list item is selected
     # Developed on : 11/20/2019
     # Author : Subrato
     ####################################################################################
     #############################
     */
    public static boolean isSelected(String locator) {
        boolean isSelected = false;
        try {
            if (TestWeb.isDisplayed(locator)) {
                WebElement element = webDriver.findElement(By.xpath(locator));
                if (element.isSelected()) {
                    isSelected = true;
                } else {
                    isSelected = false;
                }
            }
        } catch (Throwable t) {
            isSelected = false;
        }
        return isSelected;
    }

    public static List dropDownList(String locator) {
        List listDropDown = null;
        try {
            locator = locator + "[not(@disabled)]//option";
            if (TestWeb.isDisplayed(locator)) {
                for (int i = 1; i <= webDriver.findElements(By.xpath(locator)).size(); i++) {
                    listDropDown.add(getText(locator + "[" + i + "]"));
                }
            } else {
                listDropDown = null;
            }
        } catch (Throwable t) {
            listDropDown = null;
        }
        return listDropDown;
    }

}