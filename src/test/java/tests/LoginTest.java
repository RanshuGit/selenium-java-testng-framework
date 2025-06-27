package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.drivers.DriverManager;
import framework.pages.LoginPage;
import framework.pages.InventoryPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginTest extends BaseTest {
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void validLoginTest() {
        log.info("Starting validLoginTest");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        String currentUrl = inventoryPage.getCurrentUrl();
        log.info("Navigated to URL: " + currentUrl);
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");

        String title = inventoryPage.getPageTitle();
        log.info("Page title is: " + title);
        
        Assert.assertEquals(title, "Products");
    }

    @Test
    public void invalidLoginTest() {
        log.info("Starting invalidLoginTest");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.login("locked_out_user", "wrong_password");

        String errorMessage = loginPage.getErrorMessage();
        log.warn("Error message shown: " + errorMessage);
        Assert.assertTrue(errorMessage.contains("Username and password do not match"));
    }
}
