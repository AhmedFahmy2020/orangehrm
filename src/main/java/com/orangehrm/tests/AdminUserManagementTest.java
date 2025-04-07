package com.orangehrm.tests;

import com.orangehrm.pages.AdminPage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
public class AdminUserManagementTest extends BaseTest {
    private final String USER_ROLE = "ESS";
    private final String EMPLOYEE_NAME = "Ranga  Akunuri";
    private final String STATUS = "Enabled";
    private final String PASSWORD = "Password123!";
    private String username;

    @Test(priority = 1, description = "Test record count before adding user")
    @Description("Verify initial record count in Admin section")
    public void testInitialRecordCount() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        assertTrue(adminPage.isAdminPageDisplayed());

        int initialCount = adminPage.getRecordCount();
        assertTrue(initialCount >= 0, "Record count should be positive");
    }

    @Test(priority = 2, description = "Add new user")
    @Description("Test adding a new user through Admin section")
    public void testAddNewUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        int initialCount = adminPage.getRecordCount();

        // Generate unique username
        username = "testuser" + System.currentTimeMillis();

        adminPage.clickAddButton();
        adminPage.addNewUser(USER_ROLE, EMPLOYEE_NAME, STATUS, username, PASSWORD);

        assertTrue(adminPage.isSuccessMessageDisplayed(), "Success message not displayed");

        int newCount = adminPage.getRecordCount();
        assertEquals(newCount, initialCount + 1, "Record count should increase by 1");
    }

    @Test(priority = 3, description = "Search for new user")
    @Description("Verify the newly added user can be found")
    public void testSearchNewUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        adminPage.searchUserByUsername(username);

        assertTrue(adminPage.isUserInTable(username), "New user not found in table");
    }

    @Test(priority = 4, description = "Delete new user")
    @Description("Test deleting the newly added user")
    public void testDeleteUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        int initialCount = adminPage.getRecordCount();

        adminPage.deleteUser(username);
        assertTrue(adminPage.isSuccessMessageDisplayed(), "Delete success message not displayed");

        int newCount = adminPage.getRecordCount();
        assertEquals(newCount, initialCount - 1, "Record count should decrease by 1");

        // Verify user is really deleted
        adminPage.searchUserByUsername(username);
        assertFalse(adminPage.isUserInTable(username), "User should be deleted but still exists");
    }

    @Test(priority = 0, description = "Test navigation to Users page")
    @Description("Verify navigation to Users section under User Management")
    public void testNavigateToUsersPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickAdminMenu();

        AdminPage adminPage = new AdminPage();
        adminPage.navigateToUsers();

        assertTrue(adminPage.isUsersPageDisplayed(), "Users page is not displayed");
    }
}