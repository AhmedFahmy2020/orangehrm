package com.orangehrm.pages;

import com.orangehrm.utils.NumberExtractorUtility;
import com.orangehrm.utils.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AdminPage extends BasePage {
    // Locators
    private final By adminHeader = By.xpath("//h6[text()='Admin']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By userRoleDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
    private final By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
    private final By statusDropdown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
    private final By usernameField = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By passwordField = By.xpath("(//input[@type='password'])[1]");
    private final By confirmPasswordField = By.xpath("(//input[@type='password'])[2]");
    private final By saveButton = By.xpath("//button[text()=' Save ']");
    private final By successMessage = By.xpath("//p[contains(@class,'oxd-text--toast-message')]");
    private final By recordCount = By.xpath("//span[@class='oxd-text oxd-text--span']");
    private final By searchUsernameField = By.xpath("//label[text()='Username']/following::input[1]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By deleteButton = By.xpath("//button[contains(@class,'oxd-button--label-danger')]");
    private final By confirmDeleteButton = By.xpath("//button[text()=' Yes, Delete ']");
    private final By userTableRows = By.xpath("//div[@class='oxd-table-card']");

    private final By userManagementMenu = By.xpath("//span[contains(text(),'User Management')]");
    private final By usersOption = By.xpath("//a[text()='Users']");
    private final By usersHeader = By.xpath("//h5[text()='System Users']");


    public boolean isAdminPageDisplayed() {
        return isDisplayed(adminHeader);
    }

    public void clickAddButton() {
        click(addButton);
    }

    public void addNewUser(String userRole, String employeeName, String status,
                           String username, String password) {
        // Select User Role
        click(userRoleDropdown);
        click(By.xpath("//span[text()='" + userRole + "']"));

        // Type Employee Name
        type(employeeNameField, employeeName);
        Waits.waitForVisibility(driver, By.xpath("//div[@role='option']")).click();

        // Select Status
        click(statusDropdown);
        click(By.xpath("//span[text()='" + status + "']"));

        // Enter Username and Password
        type(usernameField, username);
        type(passwordField, password);
        type(confirmPasswordField, password);

        // Click Save
        click(saveButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    public int getRecordCount() {
        String countText = getText(recordCount);
        return NumberExtractorUtility.extractNumberFromParentheses(countText);
    }

    public void searchUserByUsername(String username) {
        type(searchUsernameField, username);
        click(searchButton);
    }

    public void deleteUser(String username) {
        searchUserByUsername(username);
        click(deleteButton);
        click(confirmDeleteButton);
    }

    public boolean isUserInTable(String username) {
        searchUserByUsername(username);
        List<WebElement> rows = driver.findElements(userTableRows);
        return !rows.isEmpty();
    }

    public void navigateToUsers() {
        // Click on User Management dropdown if not already expanded
        try {
            WebElement userManagementElement = driver.findElement(userManagementMenu);
            if (!userManagementElement.getAttribute("class").contains("open")) {
                click(userManagementMenu);
            }
        } catch (Exception e) {
            click(userManagementMenu);
        }

        // Click on Users option
        click(usersOption);

        // Wait for Users page to load
        Waits.waitForVisibility(driver, usersHeader);
    }

    public boolean isUsersPageDisplayed() {
        return isDisplayed(usersHeader);
    }

    public boolean isAddUserButtonDisplayed() {
        try {
            // Use explicit wait to ensure the button is interactable
            Waits.waitForVisibility(driver, addButton);
            return driver.findElement(addButton).isDisplayed();
        } catch (Exception e) {
            // Log the exception (you can integrate with a logging framework)
            System.out.println("Add User button not found: " + e.getMessage());
            return false;
        }
    }
}