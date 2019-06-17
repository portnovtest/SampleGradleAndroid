package com.sample.ui.controls;

import com.sample.framework.Configuration;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.SubItem;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private Page parent;
    private By locator;
    private String locatorText = "";
    private String itemLocatorText = "";
    private HashMap<String, SubItem> subItemsMap;

    public Control(Page parentValue, By locatorValue) {
        super();
        this.parent = parentValue;
        this.locator = locatorValue;
        this.locatorText = this.locator.toString().replaceFirst("^By\\.(\\S+): ","");
        subItemsMap = new HashMap<>();
    }

    public WebDriver getDriver() {
        return this.parent.getDriver();
    }

    public Page getParent() {
        return parent;
    }

    public By getLocator() {
        return locator;
    }

    public String getItemLocatorText() {
        return itemLocatorText;
    }

    public void setItemLocatorText(String itemLocatorText) {
        this.itemLocatorText = itemLocatorText;
    }

    public String getLocatorText() {
        return locatorText;
    }

    public HashMap<String, SubItem> getSubItemsMap() {
        return subItemsMap;
    }
    public void addSubItems(SubItem[] items){
        for (SubItem item : items) {
            this.subItemsMap.put(item.name(),item);
        }
    }

    public WebElement element(){
        return getDriver().findElement(locator);
    }

    public WebElement element(int index){
        return getDriver().findElements(locator).get(index);
    }
    public boolean exists(long timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(),timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean exists(){
        return exists(TIMEOUT);
    }
    public void click(){
        Assert.assertTrue("Unable to find element with locator: " + this.getLocator(),
                this.exists());
        this.element().click();
    }
    public <T extends Page> T click(Class<T> pageClass) throws Exception {
        this.click();
        return PageFactory.init(this.getDriver(), pageClass);
    }
    public String getText(){
        Assert.assertTrue("Unable to find element with locator: " + this.getLocator(),
                this.exists());
        return this.element().getText();
    }

    public Rectangle getRect(){
        this.exists();
        Dimension size = this.element().getSize();
        Point location = ((MobileElement) this.element()).getCoordinates().onPage();
        Rectangle rect = new Rectangle(location.x,location.y,size.getWidth(),size.getHeight());
        rect.x = location.x;
        rect.y = location.y;
        rect.width = size.width;
        rect.height = size.height;
        return rect;
    }
}
