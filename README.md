# Smart Habit Tracker (Backend)

A Spring Boot-based backend application to track daily habits, monitor consistency, and build streaks.

---

##  Features

*  User management (create, update, delete users)
*  Habit management per user
*  Mark habits as completed (once per day)
*  Access control (users can only access their own data)
*  Daily habit tracking with duplicate prevention
*  Current streak calculation based on consecutive days
*  Custom exception handling for clean API responses

---

##  Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL / PostgreSQL (depending on your setup)
* REST APIs

---

## 📂 Project Structure

```text
controller → Handles API requests
service    → Business logic & validations
repository → Database access (JPA)
entity     → Data models
exception  → Custom exception handling
```

---

##  API Overview

###  User APIs

* `POST /users` → Create user
* `GET /users/{id}` → Get user
* `PUT /users/{id}` → Update user
* `DELETE /users/{id}` → Delete user

---

###  Habit APIs

* `POST /users/{userId}/habits` → Create habit
* `GET /users/{userId}/habits` → Get all habits for a user
* `DELETE /habits/{habitId}` → Delete habit

---

###  Habit Completion APIs

* `POST /habits/{habitId}/complete` → Mark habit as completed (once per day)
* `GET /habits/{habitId}/completions` → Get completion history
* `GET /habits/{habitId}/streak` → Get current streak

---

##  Access Control

Each request includes a header:

```text
userId: <id>
```

The backend validates that users can only access their own habits and data.

---

##  How Streak Works

* Streak is calculated from **today backwards**
* Counts consecutive days of completion
* If today is not completed → streak = 0

---

##  Key Validations

* Prevent duplicate habit creation per user
* Prevent multiple completions for the same day
* Restrict access to only the owner of the habit
* Handle invalid user/habit requests gracefully

---

##  How to Run

1. Clone the repository

```bash
git clone https://github.com/Shamilin05/smart-habit-tracker
```

2. Navigate to the project

```bash
cd smart-habit-tracker
```

3. Run the application

```bash
./mvnw spring-boot:run
```

---

##  Future Improvements

*  JWT-based authentication (real-world security)
*  Longest streak & analytics
*  DTO-based API responses
*  Frontend integration

---

## 🙌 Author

Built as a learning project to understand backend system design, API development, and clean architecture.

---
