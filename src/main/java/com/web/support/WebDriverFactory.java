package com.web.support;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.web.accelerator.*;
import com.mobile.utilities.*;
import com.mobile.base.DriverScript;

public class WebDriverFactory extends DriverScript {

    public static DesiredCapabilities capabilities = null;

    public static WebDriver initialize() throws NumberFormatException, IOException {
        int implicitWait = Integer.parseInt(Util.getProperty("WebImplicitWait").toString());
        if (BROWSER.toUpperCase().equals("CHROME")) {
            System.setProperty("webdriver.chrome.silentOutput", "true");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "chromedriver.exe");
            ChromeOptions opt = new ChromeOptions();
            capabilities = new DesiredCapabilities();
            capabilities.setCapability("chromeOptions", opt);
            capabilities.setBrowserName("chrome");
            opt.addArguments("--no-sandbox");
            webDriver = new ChromeDriver(opt);
            TestWeb.deleteCookie();
            webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            webDriver.navigate().to(baseUrl);
            webDriver.manage().window().maximize();
            System.out.println("==========================================Opening chrome====================================");
        } else if (BROWSER.toUpperCase().equals("FIREFOX")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
            webDriver = new FirefoxDriver();
            webDriver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            webDriver.navigate().to(baseUrl);
            webDriver.manage().window().maximize();
            TestWeb.deleteCookie();
            System.out.println("==========================================Opening firefox====================================");
        } else if (BROWSER.toUpperCase().equals("IE")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
            capabilities = DesiredCapabilities.internetExplorer();
            webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setJavascriptEnabled(true);
            webDriver = new InternetExplorerDriver(capabilities);
            webDriver.navigate().to(baseUrl);
            Util.pause(8);
            webDriver.switchTo().defaultContent();
            webDriver.manage().window().maximize();
            TestWeb.deleteCookie();
            System.out.println("==========================================Opening IE====================================");
        }
        return webDriver;
    }
}