package com.sample.ui.controls;

import com.sample.framework.Configuration;
import com.sample.framework.ui.Page;
import org.openqa.selenium.By;

public class Edit extends Control {
    public Edit(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }
    public void setText(String value) throws Exception {
        if (Configuration.platform().isAndroidNative()){
            this.getParent().hideKeyboard();
        }
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
        if (Configuration.platform().isAndroidNative()){
            this.getParent().hideKeyboard();
        }
    }
}
