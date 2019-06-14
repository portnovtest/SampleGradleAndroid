package com.sample.tests.junit;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.ui.controls.Control;
import com.sample.ui.controls.Edit;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class SampleTest {

    private String destination;
    private boolean isBusiness;

    public SampleTest(String destination, boolean isBusiness) {
        super();
        this.destination = destination;
        this.isBusiness = isBusiness;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.BROWSER_NAME,"");
        cap.setCapability("platformVersion", Configuration.get("platformVersion"));
        cap.setCapability("platformName","Android");
        cap.setCapability("app", new File(Configuration.get("app_path")).getAbsolutePath());
        cap.setCapability("deviceName",Configuration.get("deviceName"));
        cap.setCapability("commandTimeout",Configuration.get("commandTimeout"));
        cap.setCapability("appActivity",Configuration.get("appActivity"));
        cap.setCapability("appPackage",Configuration.get("appPackage"));
        cap.setCapability("appWaitActivity",Configuration.get("appActivity"));
        cap.setCapability("appWaitPackage",Configuration.get("appPackage"));
        cap.setCapability("fullReset",true);
        WebDriver driver = new AndroidDriver<>(new URL(Configuration.get("driver_url")),cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.add(driver);
    }

    @After
    public void tearDown(){
        Driver.current().quit();
    }

    @Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(
                new Object[][]{
                        {"London", true},
                        {"Manchester", false}
                }
        );
    }
    @Test
    public void testSample() throws InterruptedException {
       Control buttonStartSearch = new Control(Driver.current(), By.id("com.booking:id/btn_start_search"));
       Control buttonDestination = new Control(Driver.current(), By.id("com.booking:id/search_searchInput"));
        Edit editDestinationInput = new Edit(Driver.current(), By.id("com.booking:id/disam_search"));
        Control itemDestinationResult = new Control(Driver.current(), By.id("com.booking:id/disam_list_root"));
        Control buttonTodaysDate = new Control(Driver.current(),
                By.xpath("(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]"));
        Control radioBusiness = new Control(Driver.current(), By.id("com.booking:id/business_purpose_business"));
        Control radioLeasure = new Control(Driver.current(), By.id("com.booking:id/business_purpose_leisure"));
        Control buttonSearch = new Control(Driver.current(), By.id("com.booking:id/search_search"));
        Control textSubTitle = new Control(Driver.current(), By.id("com.booking:id/subtitle_layout_text"));

        buttonStartSearch.click();
        buttonDestination.click();
        editDestinationInput.setText(this.destination);
        Thread.sleep(3000);
        itemDestinationResult.element(0).click();
        buttonTodaysDate.click();
        if (this.isBusiness){
            radioBusiness.click();
        } else {
            radioLeasure.click();
        }
        buttonSearch.click();
        String actualTitle = textSubTitle.getText();
        Assert.assertEquals(actualTitle, this.destination);
    }
}
