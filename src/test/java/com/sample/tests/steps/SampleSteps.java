package com.sample.tests.steps;

import com.sample.framework.ui.Page;
import com.sample.ui.controls.Control;
import com.sample.ui.controls.Edit;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class SampleSteps {
    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_page(String pageName) throws Throwable {
        Page page = Page.screen(pageName);
        Assert.assertNotNull("Unable to find page for name: '" + pageName + "'", page);
        page.navigate();
        Page.setCurrent(page);
    }

    @When("^(?:I |)enter \"([^\"]*)\" text into the \"([^\"]*)\" field$")
    public void i_enter_text_into_the_field(String text, String fieldName) throws Throwable {
        Edit control = (Edit) Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find element for name: '" + fieldName + "'", fieldName);
        control.setText(text);
    }

    @When("^(?:I |)click on the \"([^\"]*)\" (?:element|button)$")
    public void click_on_the_element(String fieldName) throws Throwable {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find element for name: '" + fieldName + "'", fieldName);
        control.click();
    }

    @Then("^I should see the \"([^\"]*)\" (?:screen|page)$")
    public void i_should_see_the_screen(String pageName) throws Throwable {
        Page page = Page.screen(pageName);
        Assert.assertNotNull("Unable to find page for name: '" + pageName + "'", page);
        Assert.assertTrue("The '" + pageName + "' is not current", page.isCurrent());
        Page.setCurrent(page);
    }

    @Then("^(?:I should see | )the \"([^\"]*)\" field has \"([^\"]*)\" text$")
    public void the_field_has_text(String fieldName, String text) throws Throwable {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find page for name: '" + fieldName + "'", fieldName);
        Assert.assertEquals(text, control.getText());
    }
}
