package tests;

import framework.drivers.DriverManager;
import framework.pages.LoginPage;
import framework.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Epic("Inventory Feature")
@Feature("Inventory Page Tests")
public class InventoryTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(InventoryTest.class);

    @Test(description = "Verify inventory page product elements and actions")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify inventory page loads with all product elements and allows add to cart")
    public void verifyProductsPageFunctionalities() throws InterruptedException {
        log.info("Inventory Page test begins");

        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        log.info("Verifying page title...");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Page title mismatch");

        int count = inventoryPage.getProductCount();
        log.info("Verifying product count...");
        Assert.assertEquals(count, 6, "Expected 6 products");

        List<String> names = inventoryPage.getAllProductNames();
        log.info("Verifying product names are listed...");
        Assert.assertFalse(names.isEmpty(), "No product names found");

        List<String> prices = inventoryPage.getAllProductPrices();
        log.info("Verifying product prices count...");
        Assert.assertEquals(prices.size(), count, "Mismatch in number of prices and products");

        log.info("Testing Add to Cart functionality...");
        Assert.assertTrue(inventoryPage.addFirstProductToCart(), "Add to Cart button not working");

        log.info("Verifying menu button visibility...");
        Assert.assertTrue(inventoryPage.isMenuButtonVisible(), "Menu button is not visible");

        log.info("Inventory Page test ends");
    }
}
