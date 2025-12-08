package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton   = By.id("login-button");
    private By errorMessage  = By.cssSelector("h3[data-test='error']");

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Convenience method
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
