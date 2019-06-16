package com.sample.tests.pages;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.ui.controls.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage extends Page {
    @FindBy(locator = "com.booking:id/btn_start_search")
    public Control buttonStartSearch;

    public LandingPage(WebDriver driver) {
        super(driver);
    }
}
