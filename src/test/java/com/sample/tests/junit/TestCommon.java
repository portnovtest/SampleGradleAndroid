package com.sample.tests.junit;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestCommon {

    public SearchPage searchPage;
    public TestCommon() {
    }

    @BeforeClass
    public static void beforeSuite() throws Exception {
        Configuration.load();
        if (Configuration.platform().isAndroidNative()){
            SystemUtils.uninstallApp(Configuration.get("appPackage"));
        }
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","geckodriver");
        System.setProperty("webdriver.chrome.driver","chromedriver");
        Configuration.load();
        DesiredCapabilities cap = new DesiredCapabilities();
        //cap.setCapability(CapabilityType.BROWSER_NAME,"");
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName","Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        //cap.setCapability("udid", Configuration.get("udid"));
        cap.setCapability("deviceName",Configuration.get("deviceName"));
        cap.setCapability("commandTimeout",Configuration.get("commandTimeout"));
        cap.setCapability("appActivity",Configuration.get("appActivity"));
        cap.setCapability("appPackage",Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity",Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage",Configuration.get("appPackage"));
        cap.setCapability("fullReset",false);
        cap.setCapability("noReset",true);
        cap.setCapability("dontStopAppOnReset",true);
        if (Configuration.platform().isAndroidNative()){
            SystemUtils.setSystemTime();
            SystemUtils.resetAppData();
        }
        WebDriver driver = Driver.init(Configuration.get("driver_url"), Configuration.platform(),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
        if (Configuration.platform().isWeb()){
            Driver.current().get(Configuration.get("url"));
        }
        if (Configuration.platform().isWeb()){
            searchPage = PageFactory.init(Driver.current(), SearchPage.class);
        } else {
            LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
            searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
        }
    }

    @After
    public void tearDown(){
        Driver.current().quit();
    }
}
