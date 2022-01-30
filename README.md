Getting started:
------------------------------
- boilerplate-appium
- Author: Subrato Sarkar
- App: Android

Repository url:
----------------------------------------
- https://github.com/subrato29/appium-framework.git

Framework Nature and Feature:
-----------------------------------------------------------------------------------------------------------------------------------------------------
- Maven
- Page Object Model
- TestNG
- Locators map
- Hybrid
- Runs on Emulator and Mobile center
- Handle web component as well

Configuration files:
-----------------------------------------------------------------------------------------------------------------------------------------------------
- globalvalue.properties
- ===============================================================	
	- DevicePlatform => Value is either 'MC' or 'emulator'.
		- MC => Execution will happen in Mobile center
		- emulator => Execution will happen in Emulator

	- MC_Password => Put your MC password in encrypted form (To encrypt your MC password: 'encryptionmanager' package)
	
	- ExecutionPlatform => Value is either 'local' or 'jenkins'

-config.properties
===============================================================
	- android parameters
		- DeviceID =
		- DeviceName =

	- IOS parameter	
		- IOS_UDID =
		- IOS_DeviceID =

	- If value is "Yes", then script will exit on a single failure, if you do not want to do that, then make this parameter blank or any value other than 'Yes'
		- FailureAndExit =

	- Putting 'Yes' of below parameters, your run results will take screenshot of the test steps where-ever you put your validation messages.
		- ScreenshotWarning =
		- ScreenshotFail =
		- ScreenshotPass =

How to run your TC:
-----------------------------------------------------------------------------------------------------------------------------------------------------
	- All your test script should be 'testscripts' package.
	- You can directly run <testcase>.java file
	- You can 'TestNgRunner.java' file available in 'com.runner.testng' package, which creates/update testng.xml file and run the test script automatically.


Framework:
-----------------------------------------------------------------------------------------------------------------------------------------------------
Page objects:
===============================================================
	- 'pageobjects' package

ActionEngine:
===============================================================
	- Appium actions will be mapped here
	
ActionWeb:
===============================================================
	- Selenium actions will be mapped here

com.app.keywords:
===============================================================
	- All app specific navigations(keywords) will be mapped here 

Current OS support:
-----------------------------------------------------------------------------------------------------------------------------------------------------
	-Android

Packages:
==================================================================
	-com.mobile => Having mobile component
	-com.web => Having web component

