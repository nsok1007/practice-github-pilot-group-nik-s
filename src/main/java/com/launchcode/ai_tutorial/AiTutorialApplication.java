package com.launchcode.ai_tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiTutorialApplication.class, args);
	}

}

/* GITHUB CO-PILOT EXERCISE PROMPT AND RESULTS:

Create a simpel class manager application that should  provide a simple REST API for managing teachers and
students stored in a PostgreSQL database. The application should have the following endpoints:
● GET /teachers - Get a list of all teachers
● GET /teachers/{id} - Get a specific teacher by ID
● POST /teachers - Create a new teacher
● PUT /teachers/{id} - Update an existing teacher
● DELETE /teachers/{id} - Delete a teacher by ID
● GET /students - Get a list of all students
● GET /students/{id} - Get a specific student by ID
● POST /students - Create a new student
● PUT /students/{id} - Update an existing student
● DELETE /students/{id} - Delete a student by ID
Ran 1 command, read 5 files

I’ll add the two PostgreSQL-backed JPA resources with matching CRUD endpoints, standard HTTP status behavior, and
an isolated in-memory database for the existing test suite.
Ran 4 commands, edited 3 files

Implemented CRUD REST APIs for /teachers and /students, backed by PostgreSQL. Each resource supports list, get by ID,
create, update, and delete; missing IDs return 404, creates return 201 with a Location header, and deletes return 204.
Database connection settings are configurable with DATABASE_URL, DATABASE_USERNAME, and DATABASE_PASSWORD. Added H2-backed
API tests; ./mvnw test -q passes.
*/
