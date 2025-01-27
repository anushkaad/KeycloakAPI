  

# KeycloakAPI 

  

This project automates the testing of Keycloak User Management APIs using modern testing frameworks and tools. It validates user-related operations such as user creation, retrieval, updating, enabling, and disabling through REST API endpoints.
  

## Features

  
  

-User Management Operations: Automates CRUD operations for users, including enabling and disabling user accounts.

  

-End-to-End API Testing: Ensures APIs function as expected with comprehensive test coverage.

  

-Reusable Test Components: Implements modular and reusable functions for streamlined test development.

  

## Prerequisites

  

- **Languages**: Java

  

- **Testing Frameworks**: Cucumber, Serenity BDD, JUnit, RestAssured

  

- **Testing Tools**: Postman, IntelliJ IDEA, Maven

  

- **Version Control**: Git

  


 ## Key Highlights

  

- Implemented behavior-driven development (BDD) with Cucumber and Serenity BDD for detailed and visual test reporting.

  

- Designed data-driven test scenarios for API validation.

  

- Automated response validation for HTTP status codes, headers, and JSON payloads.

## Setup Instructions

  

1. **Clone the Repository**

```bash
git clone https://github.com/yourusername/KeycloakAPI.git
```
  
  
2.  **Navigate to the Project Directory**

```bash
cd KeycloakAPI
```
  

3. **Install Dependencies Ensure Maven is installed and run**

```bash
mvn clean install
```
    
4.   **Run Tests**  
    Execute the tests using Maven:

```bash
mvn clean test
```


## Project Structure

-   `src/main/java`: Contains POJO classes .
-   `src/test/java`: Contains test cases for the APIs .
-   `src/test/java/helper`: Contains helper classes and endpoints for the APIs .
-   `src/test/java/resources`: Configuration files and BDD file for the APIs .
-   `src/test/java/runner`: Contains TestSuite runner class for the APIs .
-   `src/test/java/stepDefination`: Contains Hooks and stepDefination for the APIs .
-   `pom.xml`: Maven configuration file with project dependencies .

## Reports

Serenity generates detailed test execution reports under the `target/site/serenity/index.html` directory.

