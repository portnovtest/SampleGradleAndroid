package com.sample.tests.junit;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.framework.utils.SystemUtils;
import com.sample.tests.pages.PlayStorePage;

import java.io.File;

public class UpgradeTest extends TestCommon {

    public UpgradeTest() {
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        SystemUtils.uninstallApp(Configuration.get("appPackage"));
        PlayStorePage playStorePage = PageFactory.init(Driver.current(), PlayStorePage.class);
        playStorePage.installApp();
    }

    public void testUpgradeStart() throws Exception {
        Driver.current().quit();
        SystemUtils.updateApp(new File(Configuration.get("app_path")).getAbsolutePath());
        super.setUp(false);
        searchPage.editDestination.setText("Leeds");
    }
}
