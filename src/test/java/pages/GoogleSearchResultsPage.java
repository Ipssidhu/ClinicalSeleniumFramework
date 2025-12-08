package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {

    private WebDriver driver;

    // Constructor
    public GoogleSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator -> result stats text (e.g., "About 2,34,000 results")
    private By resultStats = By.id("result-stats");

    // Method to get result stats
    public String getResultStats() {
        return driver.findElement(resultStats).getText();
    }
}
