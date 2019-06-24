package com.sample.tests.steps;

import com.sample.tests.helpers.AppHelper;
import org.junit.After;
import org.junit.Before;

public class Hooks {

    @Before
    public void setUp() throws Exception {
        AppHelper.startApp(true);
    }
    @After
    public void tearDown(){
        AppHelper.stopApp();
    }
}
