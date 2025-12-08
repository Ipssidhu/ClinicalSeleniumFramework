package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.GoogleHomePage;
import pages.GoogleSearchResultsPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OpenGoogleTest extends BaseTest {   // <-- EXTENDS BaseTest

    @Test
    public void openGoogleHomePage() {

        String searchText = "Selenium WebDriver tutorial";

        // use driver from BaseTest (already initialized)
        GoogleHomePage googleHomePage = new GoogleHomePage(driver);
        googleHomePage.enterSearchText(searchText);
        googleHomePage.submitSearch();

        // Wait until title contains the text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains(searchText));

        System.out.println("Result Page Title: " + driver.getTitle());

        // (optional) use results page object
        GoogleSearchResultsPage resultsPage = new GoogleSearchResultsPage(driver);
        System.out.println("Result stats: " + resultsPage.getResultStats());
    }
}
