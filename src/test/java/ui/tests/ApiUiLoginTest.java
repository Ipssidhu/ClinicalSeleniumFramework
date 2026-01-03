package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import ui.utils.UiAuthUtil;

public class ApiUiLoginTest extends BaseTest {

    @Test
    public void loginViaApiAndOpenUiDirectly() {

        // Inject logged-in session
        UiAuthUtil.injectLoggedInSession(driver, "standard_user");

        // Navigate directly to inventory page
        driver.get("https://www.saucedemo.com/inventory.html");

        // Validate login success
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"),
                "User is not logged in via API session!");

        System.out.println("User successfully logged in via API + UI integration");
    }
}
