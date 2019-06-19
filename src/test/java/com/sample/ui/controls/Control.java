package com.sample.ui.controls;

import com.sample.framework.Configuration;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.ui.ScrollTo;
import com.sample.framework.ui.SubItem;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    private String scrollTo;
    private ScrollTo scrollDirection;
    private String format = "";
    private boolean excludeFromSearch;

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
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isExcludeFromSearch() {
        return excludeFromSearch;
    }

    public void setExcludeFromSearch(boolean excludeFromSearch) {
        this.excludeFromSearch = excludeFromSearch;
    }

    public String getScrollTo() {
        return scrollTo;
    }

    public void setScrollTo(String scrollTo) {
        this.scrollTo = scrollTo;
    }

    public ScrollTo getScrollDirection() {
        return scrollDirection;
    }

    public void setScrollDirection(ScrollTo scrollDirection) {
        this.scrollDirection = scrollDirection;
    }

    public WebElement element(){
        return getDriver().findElement(locator);
    }

    public WebElement element(int index){
        return getDriver().findElements(locator).get(index);
    }
    public boolean waitUntil(ExpectedCondition<?> condition, long timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean exists(long timeout){
        this.scrollTo();
        return waitUntil(ExpectedConditions.presenceOfElementLocated(locator), timeout);
    }
    public boolean exists(){
        return exists(TIMEOUT);
    }
    public boolean visible(long timeout){
        Assert.assertTrue(this.exists(timeout));
        return waitUntil(ExpectedConditions.visibilityOfElementLocated(locator), timeout);
    }
    public boolean visible(){
        return visible(TIMEOUT);
    }
    public boolean invisible(long timeout){
        Assert.assertTrue(this.exists(timeout));
        return waitUntil(ExpectedConditions.invisibilityOfElementLocated(locator), timeout);
    }
    public boolean invisible(){
        return invisible(TIMEOUT);
    }
    public boolean enabled(long timeout){
        this.scrollTo();
        return waitUntil(ExpectedConditions.elementToBeClickable(locator), timeout);
    }
    public boolean enabled(){
        return enabled(TIMEOUT);
    }
    public boolean disabled(long timeout){
        this.scrollTo();
        return waitUntil(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)), timeout);
    }
    public boolean disabled(){
        return disabled(TIMEOUT);
    }
    public void click(){
        Assert.assertTrue("Unable to find element with locator: " + this.getLocator(),
                this.exists());
        this.element().click();
    }
    public <T extends Page> T click(Class<T> pageClass) throws Exception {
        this.click();
        T page = PageFactory.init(this.getDriver(), pageClass);
        Assert.assertTrue(String.format("The page of %s class didn't appear during specified timeout",
                pageClass.getName()), page.isCurrent());
        return page;
    }
    public String getText(){
        Assert.assertTrue("Unable to find element with locator: " + this.getLocator(),
                this.exists());
        return this.element().getText();
    }
    public String getValue(){
        return this.getText();
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
    public void scrollTo(){
        if (this.getScrollTo() != null && !this.getScrollTo().trim().equals("")){
            this.getParent().scrollTo(getScrollTo(), getScrollDirection());
        }
    }
}
