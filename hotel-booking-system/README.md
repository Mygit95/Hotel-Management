**Hotel Management System**
-This project is a Hotel Management System that allows users to book and 
cancel room bookings.

**APIs Collection**
-Extract the Postman APIs Collection from **Hotel-Management.postman_collection.json**
located in the root folder. You can import this collection into Postman to access 
and test all the APIs. I tried Adding Swagger, but due to dependency conflicts it's not
working properly.

**MySQL Database Setup**
-Make sure you have MySQL installed and running. Then, create the necessary database table
using the following SQL commands:

CREATE TABLE hotelbooking.rooms (
id SERIAL PRIMARY KEY,
description VARCHAR(255),
available BOOLEAN NOT NULL
);

INSERT INTO hotelbooking.rooms (description, available) VALUES
('Room 1', true),
('Room 2', true),
('Room 3', true),
('Room 4', true),
('Room 5', true),
('Room 6', true),
('Room 7', true),
('Room 8', true),
('Room 9', true),
('Room 10', true);

This will create a rooms table with 10 rooms, each initially set as available.

**Running the Application**
-Clone this repository to your local machine.
Import the project into your favorite IDE (Integrated Development Environment).
Run the application.

**Testing**
-Test cases have been created for all APIs to ensure proper functionality.
You can run these test cases using your preferred testing framework.

**Scalability and Load Balancing**
-To handle millions of requests, proper load balancing and auto-scaling techniques need to
be employed. This can be achieved using cloud-based solutions such as AWS (Amazon Web Services),
Google Cloud Platform, or Azure.
