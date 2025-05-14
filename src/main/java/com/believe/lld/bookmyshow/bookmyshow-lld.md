# Low-Level Design for a "Book My Show"-like App

This document outlines the low-level design for a movie ticket booking application, similar to "Book My Show." The focus is on modularity, scalability, and maintainability.

---

## 1. Requirements

### Functional Requirements:
1. Users should be able to:
   - Search for movies by location, language, date, and genre.
   - View movie details (cast, duration, synopsis, ratings).
   - View available theaters and show timings for a selected movie.
   - Select seats and book tickets.
   - Make payments.
   - View booking history.

2. Admins should be able to:
   - Add/edit movies, theaters, and show timings.
   - Manage seat layouts.

### Non-Functional Requirements:
1. **Scalability:** Handle a large number of concurrent users.
2. **Availability:** System should be highly available with minimal downtime.
3. **Performance:** Quick responses for search, seat selection, and booking operations.
4. **Consistency:** Ensure consistent seat availability during booking.

---

## 2. Design Components

### a. Actors in the System:
- **User:** Browses, selects, and books tickets.
- **Admin:** Manages movies, theaters, and shows.

---

### b. High-Level Modules:
1. **User Management:** Handles user profiles, login, and registration.
2. **Movie Management:** Stores movie details (title, cast, language, etc.).
3. **Theater Management:** Manages theater details and seat layouts.
4. **Show Management:** Tracks show timings, screen assignments.
5. **Search Service:** Allows users to search movies by location, genre, etc.
6. **Booking Service:** Handles seat selection and booking flow.
7. **Payment Service:** Processes payments through external gateways.
8. **Notification Service:** Sends booking confirmations (email/SMS).
9. **Admin Panel:** Tools for admins to manage movies, theaters, and shows.

---

## 3. Class Diagram

```plaintext
+-----------------+      +-----------------+      +-----------------+
|     User        |      |     Movie       |      |    Theater      |
+-----------------+      +-----------------+      +-----------------+
| - userId        |      | - movieId       |      | - theaterId     |
| - name          |      | - title         |      | - name          |
| - email         |      | - genre         |      | - location      |
| - phoneNumber   |      | - language      |      | - screens       |
+-----------------+      | - duration      |      +-----------------+
                         +-----------------+

+-----------------+      +-----------------+      +-----------------+
|      Show       |      |      Seat       |      |   Booking       |
+-----------------+      +-----------------+      +-----------------+
| - showId        |      | - seatId        |      | - bookingId     |
| - movieId       |      | - screenId      |      | - userId        |
| - theaterId     |      | - row/column    |      | - showId        |
| - startTime     |      | - isAvailable   |      | - selectedSeats |
| - endTime       |      +-----------------+      | - amountPaid    |
+-----------------+                                +-----------------+
```

---

## 4. Key Workflows

### a. Search Movies
1. User enters location, date, language, or genre.
2. Search Service queries the database for matching movies.
3. Results are displayed to the user.

### b. Show Selection
1. User selects a movie.
2. System fetches available theaters and show timings.
3. User selects a theater and show time.

### c. Seat Booking
1. User selects available seats.
2. System locks the selected seats (using distributed locking).
3. Booking Service confirms the booking after payment.

### d. Payment
1. User chooses a payment method (credit card, UPI, etc.).
2. Payment Service interacts with external gateways.
3. On success, booking is finalized, and a notification is sent.

---

## 5. Database Design

### a. Tables and Schema

1. **User Table**
   ```sql
   CREATE TABLE Users (
       userId INT PRIMARY KEY,
       name VARCHAR(100),
       email VARCHAR(100),
       phoneNumber VARCHAR(15)
   );
   ```

2. **Movie Table**
   ```sql
   CREATE TABLE Movies (
       movieId INT PRIMARY KEY,
       title VARCHAR(100),
       genre VARCHAR(50),
       language VARCHAR(50),
       duration INT
   );
   ```

3. **Theater Table**
   ```sql
   CREATE TABLE Theaters (
       theaterId INT PRIMARY KEY,
       name VARCHAR(100),
       location VARCHAR(100)
   );
   ```

4. **Show Table**
   ```sql
   CREATE TABLE Shows (
       showId INT PRIMARY KEY,
       movieId INT,
       theaterId INT,
       startTime DATETIME,
       endTime DATETIME,
       FOREIGN KEY (movieId) REFERENCES Movies(movieId),
       FOREIGN KEY (theaterId) REFERENCES Theaters(theaterId)
   );
   ```

5. **Seat Table**
   ```sql
   CREATE TABLE Seats (
       seatId INT PRIMARY KEY,
       screenId INT,
       row INT,
       column INT,
       isAvailable BOOLEAN
   );
   ```

6. **Booking Table**
   ```sql
   CREATE TABLE Bookings (
       bookingId INT PRIMARY KEY,
       userId INT,
       showId INT,
       selectedSeats VARCHAR(255),
       amountPaid DECIMAL(10, 2),
       FOREIGN KEY (userId) REFERENCES Users(userId),
       FOREIGN KEY (showId) REFERENCES Shows(showId)
   );
   ```

---

## 6. Caching

- **Why:** Reduce database load and improve response times.
- **What to Cache:**
  - Frequently searched movies.
  - Seat availability for shows.
- **Tools:** Redis or Memcached.

---

## 7. API Design

### a. User APIs
1. **Login/Register:** `POST /users`
2. **Get User Details:** `GET /users/{userId}`

### b. Movie APIs
1. **Search Movies:** `GET /movies?location={}&date={}&genre={}`
2. **Get Movie Details:** `GET /movies/{movieId}`

### c. Booking APIs
1. **Get Show Details:** `GET /shows?movieId={}&location={}`
2. **Lock Seats:** `POST /bookings/lockSeats`
3. **Confirm Booking:** `POST /bookings/confirm`

---

## 8. Concurrency and Consistency

### a. Concurrency:
- Use distributed locks or transactions to prevent double booking of seats.

### b. Consistency:
- Ensure ACID compliance during seat booking.
- Eventual consistency for non-critical updates (notifications).

---

## 9. Scalability

### a. Vertical Scaling:
- Add more powerful machines for database and application servers.

### b. Horizontal Scaling:
- Use load balancers to distribute traffic across multiple app servers.
- Partition databases by location or theaters.

---

## 10. Security

- Use **OAuth2** for user authentication and token-based access.
- Encrypt sensitive data (e.g., payment details) using **SSL/TLS**.
- Validate user inputs to prevent **SQL injection**.

---

## 11. Monitoring and Logging

- **Tools:** ELK Stack (ElasticSearch, Logstash, Kibana), Prometheus, Grafana.
- Monitor:
  - API response times.
  - Database query performance.
  - Error rates in seat booking.

---

## Summary

This design ensures modularity, scalability, and robustness, making the system well-suited for handling millions of users and transactions efficiently. Further optimizations can be applied based on specific requirements like regional preferences or seasonal traffic spikes.