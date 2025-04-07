package com.orangehrm.pages;

import com.orangehrm.utils.DriverManager;
import com.orangehrm.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected WebDriver driver = DriverManager.getDriver();

    protected WebElement find(By locator) {
        return Waits.waitForVisibility(driver, locator);
    }

    protected void click(By locator) {
        Waits.waitForClickability(driver, locator).click();
    }

    protected void type(By locator, String text) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        try {
            return find(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}