# Selenium Java TestNG Automation Framework

This project is a Selenium-based automation testing framework built using Java, TestNG, Maven, and Log4j2. It supports parallel execution, environment-specific configuration, Allure reporting, and GitHub Actions CI integration.

## Features

- Maven-based project with TestNG for test execution  
- Page Object Model (POM) design pattern  
- Thread-safe WebDriver using ThreadLocal  
- Environment configuration support (e.g., `stage`, `dev`)  
- Cross-browser execution (Chrome, Firefox, Edge)  
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
│   │           ├── config/           
│   │           ├── drivers/          
│   │           └── pages/           
│   └── test/
│       ├── java/
│       │   └── tests/               
│       └── resources/
│           ├── config/             
│           └── log4j2.xml           
├── .github/
│   └── workflows/
│       └── selenium.yml             
├── allure-results/                 
├── logs/                           
├── pom.xml
└── README.md
```

## How to Run

### Locally

```bash
mvn clean test -Dbrowser=chrome -Denv=stage
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

