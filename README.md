# Cucumber Java Automation Framework For GUI with Selenium
This repo is to sample BDD automation framework to manage GUI testing using Cucumber Selenium and Java

## Tool and Technologies:

    1. Java
    2. Cucumber
    3. Selenium
    4. Junit
    5. Maven


## Dependencies:
    To run the automation testing we need Java 11 >
    Maven in installed and Configured
    The automation can be run in Chrome and Firefox

## setup

### Below Steps to Setup the Repo

1. mvn clean install - To install the dependencies from the pom.xml

### Usage
1. mvn -Dtest=Runner test - to Run the Tests
2. mvn -Dtest=Runner test -Dcucumber.filter.tags="@tagName" - Run the test with specific tags

### Reports:
1. html report - generated Using Cucumber HTML Under the target folder
2. html report - generated from the extent report under test-report folder with Date and Month
3. pdf report - generated from the extent report and available in test report pdf report folder

    





    