package framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);
    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter username: {0}")
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    @Step("Enter password: {0}")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click on Login button")
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Get error message after failed login")
    public String getErrorMessage() {
        String error = driver.findElement(errorMessage).getText();
        log.error("Login failed with message: " + error);
        return error;
    }

    @Step("Login using username: {0} and password: {1}")
    public InventoryPage login(String username, String password) {
        log.info("Logging in with user: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new InventoryPage(driver);
    }
}
