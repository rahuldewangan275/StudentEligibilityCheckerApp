# Student Scholarship Eligibility API
## Overview
The Student Scholarship Eligibility API is designed to process and determine the scholarship eligibility of students based on their marks. This microservice-based API handles CSV file uploads, processes the student data, and outputs an updated CSV file with eligibility status. The eligibility criteria are dynamic and can be modified before processing any file. Additionally, the API supports querying the eligibility status of a student by roll number.

## Features
* Features
* Upload CSV files (up to 50,000 records) and trigger processing.
* Dynamic eligibility criteria for scholarship determination.
* In-memory database/cache for fast querying by roll number.
* LOGGING : for monitoring and debugging.
* Comprehensive unit tests for reliability.
* Swagger documentation for easy API interaction.

  ## Tech Stacks
  * Language and Framework : Java & SpringBoot,Hibernate,Jpa
  * Unit Testing : JUNIT 5
  * API Documentation : Swagger API
  * DataBase: H2 (migrated from MySql to H2)
  * other tools: Intellij, Dbeaver

## Installation and Setup

### Clone the repository  
```
(https://github.com/rahuldewangan275/StudentEligibilityCheckerApp.git)
```

### should be installed on your system
* H2 data base 
* Intellij

### add required dependencies
* spring-boot-starter-web
* opencsv
* spring-boot-starter-data-jpa
* commons-csv
* h2 database dependency
* for unit test : spring-boot-starter-test dependency
* developers tool: lombok
* swagger
  ```
  	<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.2.0</version>
		</dependency> `
  ```
  ### Access Swagger documentation at http://localhost:8080/swagger-ui.html.

  ### API Endpoints
  ![Screenshot 2024-05-17 185215](https://github.com/rahuldewangan275/StudentEligibilityCheckerApp/assets/115481639/8f4f2766-f876-4099-9b86-818c1b39a3eb)

  
```
- Upload and Process CSV
- Endpoint: /api/students/upload
- Method: POST
- Description: Upload a CSV file and start the processing to determine eligibility.
- Response: Returns the updated CSV file with the eligible column set to YES or NO.(Download the new File)
```

SNAP:
![Screenshot 2024-05-17 185321](https://github.com/rahuldewangan275/StudentEligibilityCheckerApp/assets/115481639/d2c9f610-d15d-43ad-ba34-c59bfed942b9)



### Check Eligibility by Roll Number
``` 
Endpoint: /api/students/searchStudent?roll_number= roll number
Method: GET
Description: Check the eligibility status of a student by roll number.
Response: Returns YES, NO, or NA (if data is not present).
```
SNAP:
![Screenshot 2024-05-17 185357](https://github.com/rahuldewangan275/StudentEligibilityCheckerApp/assets/115481639/06bc8b1f-8a41-41df-8fda-53f3e26efb26)

  

### CSV File Structure
 The CSV file should have the following columns:
- roll_number: Unique identifier for each student.
- student_name: Name of the student.
- science: Marks obtained in Science.
- maths: Marks obtained in Maths.
- english: Marks obtained in English.
- computer: Marks obtained in Computer Science.
- eligible: Placeholder column for eligibility status (ToBeChecked).

Example:
```
roll_number,student_name,science,maths,english,computer,eligible
100101,Vivek Sharma,86,89,78,92,ToBeChecked
```

## Logging
The application includes appropriate logging for various events and errors. Logs are essential for monitoring the application's behavior and debugging issues.

## Unit Testing
Unit tests are included to ensure the reliability of the API
  
 





  
  
  
  
  
