package com.sample.tests.junit;

import com.sample.framework.utils.SystemUtils;
import com.sample.tests.helpers.AppHelper;
import com.sample.tests.pages.SearchPage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;

public class TestCommon {
    public SearchPage searchPage;
    public static Process process;

    public TestCommon() {
    }

    @BeforeClass
    public static void beforeSuite() throws Exception {
        AppHelper.uninstallApp();
        process = SystemUtils.startProcessMetricsCommand(new File("output.txt"));
    }
    @AfterClass
    public static void afterSuite() throws Exception {
        process.destroy();
    }
    public void setUp(boolean reset) throws Exception {
        AppHelper.startApp(reset);
    }
    @Before
    public void setUp() throws Exception {
        setUp(true);
    }
    @After
    public void tearDown(){
        AppHelper.stopApp();
    }
}
