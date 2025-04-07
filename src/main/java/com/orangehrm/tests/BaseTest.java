package com.orangehrm.tests;

import com.orangehrm.utils.ConfigReader;
import com.orangehrm.utils.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class BaseTest {
    @BeforeMethod
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", new ByteArrayInputStream(screenshot));
        }
        DriverManager.quitDriver();
    }
}