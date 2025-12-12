package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.InventoryPage;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @Test
    public void addSingleProductToCart_shouldIncreaseCartCount() {
        // Login
        LoginPage login = new LoginPage(driver);
        login.loginAs("standard_user", "secret_sauce");

        // Wait for inventory page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        InventoryPage inventory = new InventoryPage(driver);

        // Ensure starting cart is zero
        Assert.assertEquals(inventory.getCartItemCount(), 0, "Cart should start empty");

        // Add a product
        String productToAdd = "Sauce Labs Backpack";
        inventory.addProductToCart(productToAdd);

        // Wait a short moment for UI update (explicit wait could be added for badge presence)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "1"));

        // Assert cart shows 1
        Assert.assertEquals(inventory.getCartItemCount(), 1, "Cart count should be 1 after adding one product");

        // Optionally add another and validate 2 (uncomment to use)
        // inventory.addProductToCart("Sauce Labs Bike Light");
        // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "2"));
        // Assert.assertEquals(inventory.getCartItemCount(), 2);
    }
}
