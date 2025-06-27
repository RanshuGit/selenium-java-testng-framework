package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import framework.config.ConfigReader;
import framework.drivers.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
	private static final Logger log = LogManager.getLogger(BaseTest.class);

	@BeforeMethod
	public void setUp() {
		log.info("Setting configuration...");
		ConfigReader.loadConfig();

		DriverManager.initDriver();
		DriverManager.getDriver().get(ConfigReader.get("baseUrl"));
	}

	@AfterMethod
	public void tearDown() {
		log.info("Quitting browser...");
		DriverManager.quitDriver();
	}
}
