package com.orangehrm.pages;

import org.openqa.selenium.By;

public class DashboardPage extends BasePage {
    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By profileDropdown = By.className("oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By searchButton = By.xpath("//button[@type='submit']");

    public boolean isDashboardDisplayed() {
        return isDisplayed(dashboardHeader);
    }

    public void logout() {
        click(profileDropdown);
        click(logoutLink);
    }

    public void clickAdminMenu() {
        click(adminMenu);
    }

    public boolean isSearchButtonDisplayed() {
        return isDisplayed(searchButton);
    }
}