# Info about the Simple API project

1. It is written in Java 17 using Spring Boot.

**Functionality**

1. Accept the transactions payload
2. Validate the input and then perform operation on the payload
3. Endpoints for reporting, fetches data based on customer id, product code or location.

**Prerequisite** 
To run the application :
 - JDK 17 (if not using docker)
 - Maven
 - Docker

**Steps to run app**
1. Go to the app folder and run command:: `mvn install`, this will build the jar for the project
2. Run command to build docker image :: `docker build -t spring-boot .`
3. If you want to run command from outside the app folder, please change last dor (.) with appropriate folder where you could see Dockerfile for this project
4. Once the image is built, run ::`docker run -d -p 8080:8080 -t spring-boot `

Postman collection is added in the project as SimpleAPI.postman_collection.json

Following credentials are needed to access the application
- Username : user
- Password : password

The postman collection has Authorisation header attached, with these credentials



Tasks Covered

Task 1 :
 - Accepting large amount of transactions per second - The response time for saving transaction is less than 100 ms
 - Data needs to be stored in the database for reporting - We are using in memory H2 database

Task 2 : 
 - Date must not be in the past 
 - Total cost of transaction must not exceed 5000
 - Product must be active

Task 3 : 
 - Total cost of transactions per customer 
 - Total cost of transactions per product
 - Number of transactions sold to customer from Australia

Task 4 : 
 - Add security to webservice


Next steps :
LDAP is also configured and can be used with the application by making small change to SecurityConfig class.
At the moment LDAP will pass sessionid as cookie. Need to pass it as JWT for better API experience