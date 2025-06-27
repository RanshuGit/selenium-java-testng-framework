package tests;

import framework.drivers.DriverManager;
import framework.pages.LoginPage;
import framework.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InventoryTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(InventoryTest.class);

    @Test
    public void verifyProductsPageFunctionalities() throws InterruptedException {
    	log.info("Inventory Page begins");
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(inventoryPage.getPageTitle(), "Products", "Page title mismatch");

        int count =inventoryPage.getProductCount();
        Assert.assertEquals(count, 6, "Expected 6 products");

        List<String> names = inventoryPage.getAllProductNames();
        Assert.assertFalse(names.isEmpty(), "No product names found");

        List<String> prices = inventoryPage.getAllProductPrices();
        Assert.assertEquals(prices.size(), count, "Mismatch in number of prices and products");

        Assert.assertTrue(inventoryPage.addFirstProductToCart(), "Add to Cart button not working");

        Assert.assertTrue(inventoryPage.isMenuButtonVisible(), "Menu button is not visible");
        log.info("Inventory Page ends");
    }
}
