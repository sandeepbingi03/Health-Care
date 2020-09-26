# Health-Care
Using Spring Boot or Go, and your database of choice (PostgreSQL, MySQL, MongoDB -- any you'd like), develop a microservice for tracking the status of enrollees in a health care program.

Project:Health-Care 
Developer: Sandeep Bingi

# The challenge

Using Spring Boot or Go, and your database of choice (PostgreSQL, MySQL, MongoDB -- any you'd like), develop a microservice for tracking the status of enrollees in a health care program.
- Enrollees must have an id, name, and activation status (true or false), and a birth date
- Enrollees may have a phone number (although they do not have to supply this)
- Enrollees may have zero or more dependents
- Each of an enrollee's dependents must have an id, name, and birth date

The application we will be building will need to be able to do these things:
- Add a new enrollee
- Modify an existing enrollee
- Remove an enrollee entirely
- Add dependents to an enrollee
- Remove dependents from an enrollee
- Modify existing dependents
######

/*
 * Test Data
 * 
{
 "name" : "TEST",
	"activationStatus": true,
	"birthDate": "1991-01-01",
	"phoneNumber": "9876543210",
    "list" : [
    	{ "name" : "DEPENDENT",
		  "birthDate": "1995-01-01"
    	}
	]
}
 */
# Get all enrollees
GET: http://localhost:8080/api/v1/enroll
# Get enrollee by id
GET: http://localhost:8080/api/v1/enroll/{id}
# Add enrollee and dependent
POST: http://localhost:8080/api/v1/enroll
Sample request body:
{
 "name" : "TEST",
	"activationStatus": true,
	"birthDate": "1991-01-01",
	"phoneNumber": "9876543210",
    "list" : [
    	{ "name" : "DEPENDENT",
		  "birthDate": "1995-01-01"
    	}
	]
}
# Update an erolle or dependent
PUT: http://localhost:8080/api/v1/enroll/{id}
Sample request body
{
 "name" : "TEST",
	"activationStatus": true,
	"birthDate": "1991-01-01",
	"phoneNumber": "9876543210",
    "list" : [
    	{ "name" : "DEPENDENT",
		  "birthDate": "1995-01-01"
    	}
	]
}
#Delete an enrollee and dependent
DELETE: http://localhost:8080/api/v1/enroll/{id}
