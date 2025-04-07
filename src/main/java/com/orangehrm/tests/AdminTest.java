package com.orangehrm.tests;

import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AdminTest extends BaseTest {
    @Test(description = "Test Admin page functionality")
    @Description("Test Description: Verify Admin page navigation and user management")
    public void testAdminPageFunctionality() {
        // Login
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        // Navigate to Admin page
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        // Verify Admin page
        AdminPage adminPage = new AdminPage();
        assertTrue(adminPage.isAdminPageDisplayed(), "Admin page is not displayed");

        // Test User Management navigation
        adminPage.navigateToUsers();
        assertTrue(adminPage.isAddUserButtonDisplayed(), "Add User button is not visible");
    }
}