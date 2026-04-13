# Student Attendance System

A full-stack attendance marking app — Spring Boot REST API + HTML/CSS/JS frontend.

## Project Structure

```
attendance/
├── pom.xml
├── frontend/
│   └── index.html          ← Open this in your browser
└── src/main/java/com/attendance/
    ├── AttendanceApplication.java
    ├── model/
    │   ├── Student.java
    │   └── AttendanceRecord.java
    ├── service/
    │   └── AttendanceService.java
    └── controller/
        ├── AttendanceController.java
        └── GlobalExceptionHandler.java
```

## Prerequisites
- Java 17+
- Maven 3.8+

## Run the Backend

```bash
cd attendance
mvn spring-boot:run
```

Server starts at: http://localhost:8080

## Open the Frontend

Just open `frontend/index.html` in your browser. No build step needed.

## REST API Endpoints

| Method | Endpoint                  | Description                     |
|--------|---------------------------|---------------------------------|
| GET    | /api/students             | List all students               |
| POST   | /api/students             | Add a student `{"name":"..."}`  |
| DELETE | /api/students/{id}        | Remove a student                |
| GET    | /api/attendance?date=     | Get attendance for a date       |
| POST   | /api/attendance           | Save attendance for a date      |
| GET    | /api/attendance/all       | All attendance records          |
| GET    | /api/summary?date=        | Summary stats for a date        |

### POST /api/attendance — example body
```json
{
  "date": "2026-04-09",
  "attendance": {
    "1": true,
    "2": false,
    "3": true
  }
}
```

## Notes
- Data is stored **in-memory** — restarting the server resets it.
- To persist data, swap `AttendanceService` maps for a JPA repository + H2/PostgreSQL.
- `@CrossOrigin(origins = "*")` is set for local development — restrict this in production.
