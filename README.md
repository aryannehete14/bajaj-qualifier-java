# Bajaj Finserv Health Limited - Java Backend Qualifier

## Project Overview
This project is a Java-based Spring Boot application developed for the Bajaj Finserv Health Limited Recruitment Drive (Internship + PPO). The application automates a technical workflow by interacting with a central API to retrieve an access token, process a technical challenge, and submit the results via a secure webhook.

## Technical Details
- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Build Tool:** Maven
- **Libraries Used:** - `RestTemplate` (for HTTP communication)
  - `JSON` (for data parsing and mapping)

## Features
- **Automated Flow:** The application implements `CommandLineRunner` to trigger the technical flow immediately upon startup.
- **Authentication:** Dynamically retrieves a JWT `accessToken` from the qualifier's authentication endpoint.
- **Header-based Security:** Utilizes the Bearer token scheme in the `Authorization` header for all secure POST requests.
- **Solution Logic:** - **Registration Number:** ADT23SOCB0202 (Even)
  - **Task:** Solved **Question 2**, which involves calculating the number of employees younger than 30 within specific departments using optimized query logic.

## How to Run
1. **Prerequisites:** Ensure you have Java 17+ and Maven installed.
2. **Build the Project:**
   ```bash
   ./mvnw clean package
