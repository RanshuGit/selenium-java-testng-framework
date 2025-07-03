# Selenium Java TestNG Automation Framework

This project is a Selenium-based automation testing framework built using Java, TestNG, Maven. It supports parallel execution, environment-specific configuration, Allure reporting, and GitHub Actions CI integration.

## Features

- Maven-based project with TestNG for test execution  
- Page Object Model (POM) design pattern  
- Thread-safe WebDriver using ThreadLocal  
- Environment configuration support (e.g., `stage`, `dev`)  
- Cross-browser execution (Chrome, Firefox, Edge)  
- TestNG Parallel Execution
- Logging using Log4j2  
- Allure reporting integration  
- GitHub Actions CI setup  

## Folder Structure

```
selenium-java-testng-framework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── framework/
│   │           ├── config/                 # ConfigReader, env properties loader
│   │           ├── drivers/                # DriverManager with ThreadLocal WebDriver
│   │           ├── pages/                  # Page Object classes (LoginPage, InventoryPage, etc.)
│   │           └── utils/                  # Utility classes (WaitHelper, ElementActions - upcoming)
│   └── test/
│       ├── java/
│       │   ├── tests/                      # TestNG test classes (LoginTest, InventoryTest)
│       │   └── tests/dataproviders/       # JSON-based DataProvider classes
│       └── resources/
│           ├── config/
│           │   ├── config_dev.properties
│           │   ├── config_stage.properties
│           │   └── config_qa.properties
│           ├── testdata/
│           │   └── loginData.json         # Test data used by JsonDataProvider
│           └── log4j2.xml                 # Logging configuration
├── .github/
│   └── workflows/
│       └── selenium.yml                   # GitHub Actions CI config (runs tests, generates Allure report)
├── allure-results/                        # Allure test result output (generated)
├── logs/                                  # Application/test logs
├── testng-parallel.xml                    # TestNG suite file for parallel execution
├── jenkins/
│   └── Jenkinsfile                        # Jenkins pipeline for test execution (to be added)
├── pom.xml                                # Maven build file (includes Surefire, Allure, TestNG config)
└── README.md                              # Project documentation

```

## How to Run

### Locally + Parallel

```bash
mvn clean test -Dheadless=false -Dbrowser=chrome -Denv=stage
```

### On GitHub Actions

Code is pushed to the `main` branch and GitHub Actions workflow (`.github/workflows/selenium.yml`) automatically triggers:

- Checkout code  
- Setup Java  
- Run tests  
- Upload Allure results as artifact  

## Allure Report

To generate the report locally:

```bash
allure serve allure-results
```

Or download the Allure CLI from:  
https://docs.qameta.io/allure/

## Logging

Log4j2 is used to capture runtime logs. Logs are printed to the console and saved in:

```
logs/test-log.log
```

## GitHub Setup

- Git initialized inside the framework folder  
- Repository created and code pushed  
- GitHub Actions configured to run tests and store test reports  

