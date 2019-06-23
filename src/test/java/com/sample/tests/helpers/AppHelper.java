package com.sample.tests.helpers;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.junit.TestCommon;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AppHelper {

    public AppHelper() {
    }

    public static SearchPage startApp(boolean reset) throws Exception {
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
        if (Configuration.platform().isAndroidNative() && reset){
            SystemUtils.setSystemTime();
            SystemUtils.resetAppData();
        }
        WebDriver driver = Driver.init(Configuration.get("driver_url"), Configuration.platform(),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
        if (Configuration.platform().isWeb()){
            Driver.current().get(Configuration.get("url"));
        }
        SearchPage searchPage;
        if (Configuration.platform().isWeb()){
            searchPage = PageFactory.init(Driver.current(), SearchPage.class);
        } else {
            LandingPage landingPage = PageFactory.init(Driver.current(), LandingPage.class);
            searchPage = landingPage.buttonStartSearch.click(SearchPage.class);
        }
        return searchPage;
    }
    public static void stopApp(){
        Driver.current().quit();
    }
    public static void uninstallApp() throws Exception {
        Configuration.load();
        if (Configuration.platform().isAndroidNative()){
            SystemUtils.uninstallApp(Configuration.get("appPackage"));
        }
    }
}
