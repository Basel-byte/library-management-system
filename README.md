# Library Management System

  

Table of contents
=================

* [Task Overview](#task-overview)
* [Getting Started](#getting-started)
  *  [Dependencies](#dependencies)
  *  [Building project](#project-build)
*	[Implementation](#implementation)
* [Testing](#testing)
* [API endpoints](#API-endpoints)
* [Summary](#summary)

## Task Overview

Build a Library Management System API using Spring Boot. The system should allow librarians to manage books, patrons, and borrowing records.

**Entities:**  
● Book: Includes attributes like ID, title, author, publication year, ISBN, etc.  
● Patron: Contains details like ID, name, contact information, etc.  
● Borrowing Record: Tracks the association between books and patrons,  
including borrowing and return dates.

![Schema Diagram](https://github.com/Basel-byte/library-management-system/blob/main/schema-diagram.png)

## Getting Started

I used maven as my build tool for this spring-boot application. I will take about spring-boot dependencies.

## Dependencies
	●	Spring Web
	●	Spring Security
	●	Spring Data JPA
	●	Spring Cache
	● 	Spring Bean Validation
	●	Mysql connector 
	● 	H2 InMemDB for testing
	●	Lombok
	●	MapStruct
	●	Assert4j
	
## Building project

> Run `` mnv clean install `` in your terminal to install the dependencies if not yet downloaded and compile the project.

> Run ```mvn spring-boot:run``` to run the backend server.

## Implementation

The project structure is divided between many layers or packages as follows:
![Project Structure](https://github.com/Basel-byte/library-management-system/blob/main/project-structure.jpg)
		1. Rest Controller
		2. DTO (Data Transfer Object)
		3. MapStruct.  ```Mapping from DTO to Model(Entity) and vice verca.```
		4. Model. ```Represent table in Database```
		5. Service.
		6. Repository. (DAO)
		7. utils package for handling custom exceptions and creating custom annotations for validation.

## Testing

I used **junit 5**, **Mockito** and **Assert4j** for unit testing, while i used **postman** for API testing.

## API endpoints

[Click on this link for API documentation on postman workspace](https://app.getpostman.com/join-team?invite_code=69d26298fc2155b0b0faae524898f2d8&target_code=305076f3012761a5e5a5a2f78e60eec3)

## Summary

What i have done special in this project is:

 - Input Validation 
 ```Custom annotations are implemented to enforce custom validation rules and this validation is done on the DTO classes.```
 - Exception Handling
 ```Handling all exceptions through custom exception handling annotation to avoid boilerplate code and responding to the client with meaningful HTTP status codes respecting the best practises```
 - Transaction Management and Isolation
 ```By using @Transactional to provide atomicity of critical operations and to ensure no race condition can happpen with the number_of_copies field in book table when multiple users are updating it at the same time, @Lock(LockModeType.PESSIMISTIC_WRITE) is used to provide isolation```
