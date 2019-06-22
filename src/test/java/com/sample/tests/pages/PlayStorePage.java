package com.sample.tests.pages;

import com.sample.framework.Configuration;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.utils.SystemUtils;
import com.sample.ui.controls.Control;
import org.openqa.selenium.WebDriver;

public class PlayStorePage extends Page {

    public PlayStorePage(WebDriver driverValue) {
        super(driverValue);
    }

    @FindBy(locator = "//android.widget.ImageView[contains(@resource-id, 'id/title_thumbnail')]")
    public Control iconTitle;

    @FindBy(locator = "//*[contains(@text, 'INSTALL')]", excludeFromSearch = true)
    public Control buttonInstall;

    @FindBy(locator = "//*[contains(@text, 'UNINSTALL')]", excludeFromSearch = true)
    public Control buttonUninstall;

    @FindBy(locator = "//*[@text='ACCEPT']", excludeFromSearch = true)
    public Control buttonAccept;

    @FindBy(locator = "//*[@text='OPEN']", excludeFromSearch = true)
    public Control buttonOpen;

    @Override
    public Page navigate() {
        SystemUtils.openDeepLink("market://details?id=" + Configuration.get("appPackage"));
        return this;
    }
}
