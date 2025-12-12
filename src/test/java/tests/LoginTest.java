package tests;

import base.BaseTest;
import pages.InventoryPage;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginShouldNavigateToProductsPage() {

        // Create LoginPage object using driver from BaseTest
        LoginPage loginPage = new LoginPage(driver);

        // Login with valid credentials
        loginPage.loginAs("standard_user", "secret_sauce");

        // Wait until URL contains "inventory.html" (products page)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        // Print current URL and title
        System.out.println("Logged in! Current URL: " + driver.getCurrentUrl());
        System.out.println("Page Title: " + driver.getTitle());

        // 🔹 Assertions must be inside the method
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "URL mismatch - Login may have failed!");

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");
        
        // now use InventoryPage
        InventoryPage inventory = new pages.InventoryPage(driver);

        // get count and print names
        int productCount = inventory.getProductCount();
        inventory.printProductNames();

        // assertion: expect 6 products on SauceDemo inventory page
        org.testng.Assert.assertEquals(productCount, 6, "Product count mismatch on Inventory page");

        // print for visibility
        System.out.println("Inventory product count: " + productCount);
        System.out.println("Test complete - inventory validated.");
    }
    
    
}