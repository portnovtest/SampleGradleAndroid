package com.sample.tests.junit;

import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.SearchResultsPage;
import com.sample.ui.controls.Control;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SampleTest extends TestCommon {

    private String destination;
    private boolean isBusiness;
    private String scrollToText;

    public SampleTest(String destination, boolean isBusiness, String scrollToText) {
        super();
        this.destination = destination;
        this.isBusiness = isBusiness;
        this.scrollToText = scrollToText;
    }



    @Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(
                new Object[][]{
                        {"Leeds", true, "Hilton Leeds"},
                        //{"Manchester", false}
                }
        );
    }
    @Test
    public void testSample() throws Exception {
        searchPage.editDestination.setText(destination);

        searchPage.buttonTodaysDate.click();
        Assert.assertTrue(
                searchPage.allElementsExist(
                        new Control[] {
                                searchPage.editDestination,
                                searchPage.dateCheckin,
                                searchPage.buttonSearch
                        }
                )
        );
        Assert.assertTrue(
                searchPage.anyOfElementsExist(
                        new Control[] {
                                searchPage.editDestination,
                                searchPage.dateCheckin,
                                searchPage.buttonSearch
                        }
                )
        );
        searchPage.editDestination.setText(destination);

        searchPage.buttonTodaysDate.click();
        SystemUtils.switchApp();
        Thread.sleep(1000);
        SystemUtils.killApp();
        SystemUtils.switchApp();

        long checkin = Long.parseLong(searchPage.dateCheckin.getValue());
        long checkout = Long.parseLong(searchPage.dateCheckout.getValue());
        Assert.assertEquals(checkout - checkin, 24 * 60 * 1000);
        if (this.isBusiness){
            searchPage.radioBusiness.click();
        } else {
            searchPage.radioLeisure.click();
        }
        SearchResultsPage searchResultsPage = searchPage.buttonSearch.click(SearchResultsPage.class);
//        String actualTitle = searchResultsPage.textSubTitle.getText();
//        Assert.assertEquals(actualTitle, destination);
        searchResultsPage.captureScreenShot("./build/sample-" + destination + ".png");
        searchResultsPage.scrollTo(scrollToText);
        searchResultsPage.textParkPlaza.click();
    }
}
