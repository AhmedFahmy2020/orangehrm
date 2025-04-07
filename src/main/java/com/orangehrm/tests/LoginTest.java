package com.orangehrm.tests;

import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.qameta.allure.Description;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {
    @Test(description = "Successful login with valid credentials")
    @Description("Test Description: Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard page is not displayed");
    }

    @Test(description = "Unsuccessful login with invalid credentials")
    @Description("Test Description: Verify error message appears with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("invalid", "invalid");

        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed");
        assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),
                "Error message does not contain expected text");
    }

    @Test(description = "Navigate to Admin page after login")
    @Description("Test Description: Verify user can navigate to Admin page")
    public void testNavigateToAdminPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        assertTrue(adminPage.isAdminPageDisplayed(), "Admin page is not displayed");
    }
}