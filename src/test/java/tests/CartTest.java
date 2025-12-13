package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.CartPage;

import java.time.Duration;
import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void addRemoveProducts_cartCountAndTotal_shouldUpdate() {
        // 1) Login
        LoginPage login = new LoginPage(driver);
        login.loginAs("standard_user", "secret_sauce");

        // 2) Wait for inventory page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("inventory.html"));

        // 3) Add two products via InventoryPage
        InventoryPage inventory = new InventoryPage(driver);
        String p1 = "Sauce Labs Backpack";
        String p2 = "Sauce Labs Bike Light";
        
     // ensure starting cart is empty
        Assert.assertEquals(inventory.getCartItemCount(), 0, "Cart should start empty");
        inventory.addProductToCart(p1);
      // adding p1
        
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "1"));
        
        inventory.addProductToCart(p2);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("shopping_cart_badge"), "2"));
        
        // 4) Click cart icon to open cart page
        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
        
        CartPage cart = new CartPage(driver);
        int count = cart.getCartItemCount();
        Assert.assertEquals(count, 2, "Cart should contain 2 items");
        
        List<String> names=cart.getCartItemNames();
        System.out.println("Cart item name: " + names);
        
        double total = cart.getCartTotal();
        System.out.println("Cart total (computed): " + total);
        
        List<Double> prices = cart.getCartItemPrices();
        double expectedTotal = prices.stream().mapToDouble(Double::doubleValue).sum();
        Assert.assertEquals(total, expectedTotal, 0.001, "Cart total must equal sum of prices");

        cart.removeItemByName(p1);
        // wait for removal to reflect - either badge update or cart item count change
        wait.until(driver1 -> cart.getCartItemCount() == 1);

        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart count should be 1 after removal");

        System.out.println("Cart add/remove validated successfully.");
        
    }
    
    
}    