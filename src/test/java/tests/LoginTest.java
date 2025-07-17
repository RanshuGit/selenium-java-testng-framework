package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import framework.drivers.DriverManager;
import framework.pages.LoginPage;
import framework.pages.InventoryPage;

import io.qameta.allure.*;
import dataproviders.LoginDataProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Epic("Login Feature")
@Feature("Login Page Tests")
public class LoginTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test(description = "Valid login should navigate to Inventory page", dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify valid login for standard user")
    public void validLoginTest(String username, String password) {
        log.info("Starting validLoginTest");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());

        InventoryPage inventoryPage = loginPage.login(username, password);

        String currentUrl = inventoryPage.getCurrentUrl();
        log.info("Navigated to URL: " + currentUrl);
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "URL after login mismatch");

        String title = inventoryPage.getPageTitle();
        log.info("Page title is: " + title);
        Assert.assertEquals(title, "Products", "Page title is incorrect");
    }

    @Test(description = "Invalid login should show an error message", dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to verify login fails with invalid credentials")
    public void invalidLoginTest(String username, String password) {
        log.info("Starting invalidLoginTest");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login(username, password);

        String errorMessage = loginPage.getErrorMessage();
        log.warn("Error message shown: " + errorMessage);
        Assert.assertTrue(errorMessage.contains("Username and password do not match"), "Error message not displayed properly");
    }
}
