package com.sample.tests.pages;

import com.sample.framework.Platform;
import com.sample.framework.ui.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.tests.controls.LocationLookupEdit;
import com.sample.ui.controls.Control;
import com.sample.ui.controls.DateLabel;
import org.openqa.selenium.WebDriver;

@Alias("Search")
public class SearchPage extends Page {
    @Alias("Destination")
    @FindBy(locator = "com.booking:id/search_searchInput", platform = Platform.ANDROID_NATIVE)
    @FindBy(locator = "ss")
    public LocationLookupEdit editDestination;

    @FindBy(locator = "com.booking:id/search_checkinDate", platform = Platform.ANDROID_NATIVE, format = "EEE, MMMM dd")
    public DateLabel dateCheckin;
    @FindBy(locator = "com.booking:id/search_checkoutDate", platform = Platform.ANDROID_NATIVE, format = "EEE, MMMM dd")
    public DateLabel dateCheckout;

    @Alias("Today's Date")
    @FindBy(locator = "xpath=(//android.widget.TextView[contains(@resource-id, 'calendar_tv') and @enabled='true'])[1]",
            platform = Platform.ANDROID_NATIVE, excludeFromSearch = true)
    @FindBy(locator = "//table[@class='bui-calendar__dates']//td[contains(@class,'bui-calendar__date--today')]")
    public Control buttonTodaysDate;
    @Alias("Business")
    @FindBy(locator = "com.booking:id/business_purpose_business", platform = Platform.ANDROID_NATIVE)
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])")
    public Control radioBusiness;
    @Alias("Leisure")
    @FindBy(locator = "com.booking:id/business_purpose_leisure", platform = Platform.ANDROID_NATIVE)
    @FindBy(locator = "xpath=(//input[@name='sb_travel_purpose'])[1]")
    public Control radioLeisure;
    @Alias("Search")
    @FindBy(locator = "com.booking:id/search_search", platform = Platform.ANDROID_NATIVE)
    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSearch;

    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
