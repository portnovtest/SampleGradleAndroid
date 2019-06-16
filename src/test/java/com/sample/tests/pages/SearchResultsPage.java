package com.sample.tests.pages;

import com.sample.framework.Driver;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.ui.controls.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends Page {
    @FindBy(locator = "com.booking:id/subtitle_layout_text")
    public Control textSubTitle;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}
