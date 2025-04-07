package com.orangehrm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static byte[] takeScreenshot(WebDriver driver) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is null, cannot take screenshot");
            return null;
        }
        logger.debug("Taking screenshot as bytes");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void saveScreenshot(WebDriver driver, String testName) throws IOException {
        if (driver == null) {
            logger.error("WebDriver is null, cannot save screenshot");
            return;
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotName = testName + "_" + timestamp + ".png";

        // Get the screenshot directory from config
        ConfigReader configReader = new ConfigReader();
        String screenshotDir = configReader.getProperty("screenshot.path");

        Path directory = Paths.get(screenshotDir);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
            logger.info("Created directory for screenshots: " + directory);
        }

        Path destination = directory.resolve(screenshotName);
        Files.copy(screenshotFile.toPath(), destination);
        logger.info("Screenshot saved to: " + destination);
    }
}