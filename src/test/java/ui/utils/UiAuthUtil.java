package ui.utils;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class UiAuthUtil {

    public static void injectLoggedInSession(WebDriver driver, String username) {

        // Open base domain first (important)
        driver.get("https://www.saucedemo.com/");

        // Create session cookie
        Cookie sessionCookie = new Cookie("session-username", username);

        // Add cookie to browser
        driver.manage().addCookie(sessionCookie);

        // Refresh browser to apply session
        driver.navigate().refresh();
    }
}
