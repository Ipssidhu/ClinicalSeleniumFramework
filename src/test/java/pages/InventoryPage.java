package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;
import org.openqa.selenium.WebElement;

public class InventoryPage { 

    private WebDriver driver; 
    private By productNames = By.className("inventory_item_name");
   
    public InventoryPage(WebDriver driver) {
    	this.driver= driver;
    	
    }
    
    public int getProductCount() {
    	List<WebElement>products = driver.findElements(productNames);
    	return products.size();
    } 
    
    public void printProductNames() {
    	List<WebElement>products = driver.findElements(productNames);
    	for(WebElement p : products) {
    		System.out.println("List of the porducts "+ p.getText());
    	}
    	
    }
    
} 