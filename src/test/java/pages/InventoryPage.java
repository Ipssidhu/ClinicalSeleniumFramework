package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for all product names
    private By productNames = By.className("inventory_item_name");

    // Locator for product containers (to find button within specific product)
    private By productItems = By.className("inventory_item");

    // Locator for product prices
    private By productPrices = By.className("inventory_item_price");

    // Locator for cart badge
    private By cartBadge = By.className("shopping_cart_badge");

    // Method 1: Return total product count
    public int getProductCount() {
        List<WebElement> products = driver.findElements(productNames);
        return products.size();
    }

    // Method 2: Return list of product names
    public List<String> getProductNames() {
        List<WebElement> elems = driver.findElements(productNames);
        return elems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Method 3: Print product names (keeps existing behavior)
    public void printProductNames() {
        getProductNames().forEach(name -> System.out.println("Product: " + name));
    }

    // Method 4: Return list of product prices as String (e.g. "$29.99")
    public List<String> getProductPrices() {
        List<WebElement> elems = driver.findElements(productPrices);
        return elems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Method 5: Add product to cart by product name
    public void addProductToCart(String productName) {
        List<WebElement> items = driver.findElements(productItems);
        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(productName)) {
                WebElement button = item.findElement(By.tagName("button"));
                if (button.getText().equalsIgnoreCase("add to cart") || button.getText().equalsIgnoreCase("Add to cart")) {
                    button.click();
                } else {
                    // if already in cart, do nothing
                }
               
                return;
            }
        }
        throw new RuntimeException("Product not found on inventory page: " + productName);
    }

    // Method 6: get cart item count (reads the badge). returns 0 if no badge shown
    public int getCartItemCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(badges.get(0).getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
