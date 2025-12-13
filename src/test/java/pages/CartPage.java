package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for cart item containers
    private By cartItems = By.className("cart_item");

    // Within a cart_item: name and price
    private By itemName = By.className("inventory_item_name");
    private By itemPrice = By.className("inventory_item_price");

    // Remove button within a cart_item
    private By removeButton = By.tagName("button");

    // Checkout button on cart page
    private By checkoutButton = By.id("checkout");

    // Method: return list of product names in cart
    public List<String> getCartItemNames() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.stream()
                .map(e -> e.findElement(itemName).getText())
                .collect(Collectors.toList());
    }

    // Method: return list of product prices as double (e.g. 29.99)
    public List<Double> getCartItemPrices() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.stream()
                .map(e -> e.findElement(itemPrice).getText().replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    // Method: remove an item by name (click Remove button inside that item)
    public void removeItemByName(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(itemName).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement btn = item.findElement(removeButton);
                if (btn.getText().equalsIgnoreCase("Remove")) {
                    btn.click();
                }
                return;
            }
        }
        throw new RuntimeException("Cart item not found to remove: " + productName);
    }

    // Method: proceed to checkout (click checkout)
    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Convenience: total of prices in cart
    public double getCartTotal() {
        return getCartItemPrices().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Count of items
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
}
