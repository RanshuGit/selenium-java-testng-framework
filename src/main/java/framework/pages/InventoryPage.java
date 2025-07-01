package framework.pages;

import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InventoryPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(InventoryPage.class);

    private By title = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By swagLabsLogo = By.className("app_logo");
    private By productPrices = By.className("inventory_item_price");
    private By addToCartButtons = By.xpath("//button[contains(text(),'Add to cart')]");
    private By removeButtons = By.xpath("//button[contains(text(),'Remove')]");
    private By menuButton = By.id("react-burger-menu-btn");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Get page title")
    public String getPageTitle() {
        String pageTitle = driver.findElement(title).getText();
        log.info("Page title: " + pageTitle);
        return pageTitle;
    }

    @Step("Get current URL of inventory page")
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        log.debug("Current URL: " + url);
        return url;
    }

    @Step("Check if logo is present")
    public boolean logoPresent() {
        return driver.findElement(swagLabsLogo).isDisplayed();
    }

    @Step("Get total product count")
    public int getProductCount() {
        int count = driver.findElements(inventoryItems).size();
        log.info("Total number of products: " + count);
        return count;
    }

    @Step("Get all product names")
    public List<String> getAllProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();
        for (WebElement e : elements) {
            names.add(e.getText());
            log.info("Product name: " + e.getText());
        }
        return names;
    }

    @Step("Get all product prices")
    public List<String> getAllProductPrices() {
        List<WebElement> elements = driver.findElements(productPrices);
        List<String> prices = new ArrayList<>();
        for (WebElement e : elements) {
            prices.add(e.getText());
            log.info("Product price: " + e.getText());
        }
        return prices;
    }

    @Step("Check if menu button is visible")
    public boolean isMenuButtonVisible() {
        boolean visible = driver.findElement(menuButton).isDisplayed();
        log.info("Menu button visible: " + visible);
        return visible;
    }

    @Step("Add first product to cart")
    public boolean addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
            log.info("Clicked Add to Cart on first product");
            return true;
        }
        return false;
    }

    @Step("Verify Remove button appears after adding to cart")
    public boolean verifyRemoveButtonAppears() {
        WebElement buttons = driver.findElement(removeButtons);
        if (buttons.getText().equals("Remove")) {
            log.info("Script Passed: 'Remove' button is visible for the product.");
            return true;
        } else {
            log.error("Script Failed: Expected 'Remove' but found");
            return false;
        }
    }
}
