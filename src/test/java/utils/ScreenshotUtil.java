package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {

        // Create screenshots folder if not exists
    	String projectPath = System.getProperty("user.dir");
    	File screenshotDir = new File(projectPath + File.separator + "screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        // Timestamp for unique file name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Screenshot file name
        String fileName = testName + "_" + timestamp + ".png";

        // Take screenshot
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Destination path
        File destinationFile = new File(screenshotDir, fileName);

        try {
            FileUtils.copyFile(sourceFile, destinationFile);
            System.out.println("📸 Screenshot saved: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Failed to save screenshot: " + e.getMessage());
        }
    }
}
