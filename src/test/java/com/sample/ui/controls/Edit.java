package com.sample.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Edit extends Control {
    public Edit(WebDriver driver, By locator) {
        super(driver, locator);
    }
    public void setText(String value){
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
    }
}
