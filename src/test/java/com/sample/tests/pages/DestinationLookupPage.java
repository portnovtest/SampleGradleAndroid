package com.sample.tests.pages;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.ui.controls.Control;
import com.sample.ui.controls.Edit;
import org.openqa.selenium.WebDriver;

public class DestinationLookupPage extends Page {
    @FindBy(locator = "com.booking:id/disam_search")
    public Edit editDestinationInput;
    @FindBy(locator = "com.booking:id/disam_list_root")
    public Control itemDestinationResult;

    public DestinationLookupPage(WebDriver driverValue) {
        super(driverValue);
    }
}
