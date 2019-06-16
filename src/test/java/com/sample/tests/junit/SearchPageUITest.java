package com.sample.tests.junit;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.LandingPage;
import com.sample.tests.pages.SearchPage;
import org.junit.Assert;
import org.junit.Test;

public class SearchPageUITest extends TestCommon {

    public SearchPageUITest() {
    }

    @Test
    public void testVerifyUIOnSearchPage() throws Exception {
        Assert.assertTrue(searchPage.editDestination.exists());
        Assert.assertTrue(searchPage.radioBusiness.exists());
        Assert.assertTrue(searchPage.radioLeisure.exists());
        Assert.assertTrue(searchPage.buttonSearch.exists());
    }
}
