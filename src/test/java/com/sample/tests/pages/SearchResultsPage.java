package com.sample.tests.pages;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.ScrollTo;
import com.sample.ui.controls.Control;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends Page {
    @FindBy(locator = "com.booking:id/subtitle_layout_text")
    public Control textSubTitle;

    @FindBy(locator = "//*[contains(@text, 'Park Plaza')]",
            scrollTo = "Park Plaza", scrollDirection = ScrollTo.TOP_BOTTOM)
    public Control textParkPlaza;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
}
