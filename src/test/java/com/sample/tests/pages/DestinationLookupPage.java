package com.sample.tests.pages;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.SubItem;
import com.sample.ui.controls.Control;
import com.sample.ui.controls.Edit;
import com.sample.ui.controls.TableView;
import org.openqa.selenium.WebDriver;

public class DestinationLookupPage extends Page {
    @FindBy(locator = "com.booking:id/disam_search")
    public Edit editDestinationInput;
    @FindBy(locator = "com.booking:id/disam_list_root")
    public Control itemDestinationResult;
    @FindBy(locator = "//*[contains(@resource-id, 'locations_container')]",
        itemLocator = "//android.widget.LinearLayout[contains(@resource-id, 'disam_list_root')]")
    @SubItem(name = "Title",
        locator = "//android.widget.LinearLayout[contains(@resource-id, 'disam_list_name')]")
    public TableView tableDestinationResults;

    public DestinationLookupPage(WebDriver driverValue) {
        super(driverValue);
    }
}
