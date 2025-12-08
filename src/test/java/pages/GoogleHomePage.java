package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

    private WebDriver driver;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBox = By.name("q");

    public void enterSearchText(String text) {
        driver.findElement(searchBox).sendKeys(text);
    }

    public void submitSearch() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }
}
