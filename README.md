# Customer Lead CRM API

A production-quality REST API for managing customer leads, follow-ups, notes, and lead types. Built with Spring Boot 3.2.x, Java 21, and MySQL.

## Features

- **Complete CRUD Operations** for Lead Types, Customer Leads, Follow-ups, and Notes
- **Dashboard APIs** for statistics and analytics
- **Advanced Search** with multiple filter criteria
- **Reminder APIs** for today's, overdue, and tomorrow's follow-ups
- **Simple Authentication** using username/password stored in MySQL
- **Global Exception Handling** with meaningful error messages
- **Swagger/OpenAPI Documentation** for easy API exploration
- **CORS Configuration** for Angular frontend integration
- **DTO Pattern** for clean data transfer
- **Layered Architecture** following best practices

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.16**
- **Spring Data JPA**
- **MySQL**
- **Maven**
- **Lombok**
- **Validation**
- **Swagger/OpenAPI (SpringDoc)**

## Project Structure

```
com.crm.customerlead
├── config          # Configuration classes (CORS, OpenAPI, DataLoader)
├── controller      # REST controllers
├── service         # Service interfaces
├── service.impl    # Service implementations
├── repository      # JPA repositories
├── entity          # JPA entities
├── dto             # Data Transfer Objects
├── mapper          # Entity <-> DTO mappers
├── exception       # Custom exceptions and global handler
└── util            # Utility classes
```

## Database Schema

### Tables

- **users** - User authentication and roles
- **lead_type** - Lead type classifications
- **customer_lead** - Customer lead information
- **follow_up** - Follow-up records for leads
- **notes** - Notes associated with leads

### Enums

**Status**: NEW, CONTACTED, INTERESTED, FOLLOW_UP, VISIT_SCHEDULED, NEGOTIATION, CLOSED_WON, CLOSED_LOST, NOT_INTERESTED

**Priority**: HOT, WARM, COLD, NOT_CUSTOMER

## Prerequisites

- Java 21 or Java 17
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd customer-lead-crm-api
   ```

2. **Configure Database**
   
   Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/customer_lead_crm
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run MySQL Script**
   
   Execute the provided SQL script to set up the database:
   ```bash
   mysql -u root -p < database/mysql-script.sql
   ```

4. **Build the Project**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the main class `CustomerLeadCrmApiApplication` from your IDE.

The application will start on `http://localhost:8080`

## API Documentation

Once the application is running, access the Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

The OpenAPI JSON is available at:
```
http://localhost:8080/api-docs
```

## API Endpoints

### Authentication

- `POST /api/auth/login` - Login with username and password

### Users

- `POST /api/users` - Create user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user

### Lead Types

- `POST /api/lead-types` - Create lead type
- `GET /api/lead-types` - Get all lead types
- `GET /api/lead-types/{id}` - Get lead type by ID
- `PUT /api/lead-types/{id}` - Update lead type
- `DELETE /api/lead-types/{id}` - Delete lead type

### Customer Leads

- `POST /api/leads` - Create lead
- `GET /api/leads` - Get all leads
- `GET /api/leads/{id}` - Get lead by ID
- `PUT /api/leads/{id}` - Update lead
- `DELETE /api/leads/{id}` - Delete lead
- `POST /api/leads/search` - Search leads with filters
- `GET /api/leads/dashboard` - Get dashboard statistics

### Follow-ups

- `POST /api/followups` - Create follow-up
- `GET /api/followups` - Get all follow-ups
- `GET /api/followups/{id}` - Get follow-up by ID
- `GET /api/followups/lead/{leadId}` - Get follow-ups by lead ID
- `PUT /api/followups/{id}` - Update follow-up
- `DELETE /api/followups/{id}` - Delete follow-up
- `GET /api/followups/today` - Get today's follow-ups
- `GET /api/followups/overdue` - Get overdue follow-ups
- `GET /api/followups/tomorrow` - Get tomorrow's follow-ups

### Notes

- `POST /api/notes` - Create note
- `GET /api/notes` - Get all notes
- `GET /api/notes/{id}` - Get note by ID
- `GET /api/notes/lead/{leadId}` - Get notes by lead ID
- `PUT /api/notes/{id}` - Update note
- `DELETE /api/notes/{id}` - Delete note

## Sample Data

The application includes a `DataLoader` that automatically loads sample data on first startup:

**Users:**
- admin / admin123 (ADMIN)
- executive / executive123 (EXECUTIVE)

**Lead Types:** Website, Referral, Cold Call, Social Media

**Sample Leads:** 4 sample customer leads with various statuses and priorities

## Search API

The search API allows filtering leads by multiple criteria:

```json
{
  "customerName": "John",
  "mobile": "9876543210",
  "city": "New York",
  "leadTypeId": 1,
  "status": "INTERESTED",
  "priority": "HOT",
  "executive": "executive",
  "startDate": "2024-01-01T00:00:00",
  "endDate": "2024-12-31T23:59:59"
}
```

All fields are optional - only provided filters will be applied.

## Dashboard API

Returns statistics including:
- Total Leads
- Today's Follow-ups
- Pending Follow-ups
- Hot Customers
- Closed Won
- Closed Lost
- Today's Leads
- Monthly Leads

## Validation

The API enforces the following validations:

**Customer Lead:**
- Customer Name (required)
- Mobile (required)
- Email (valid format)
- Lead Type (required)
- Status (required)
- Priority (required)

**Follow-up:**
- Lead ID (required)
- Discussion (required)
- Next Follow-up Date (required)
- Status (required)

**Note:**
- Lead ID (required)
- Note (required)

## CORS Configuration

The API is configured to accept requests from `http://localhost:4200` for development with Angular. This can be changed in `application.properties`:

```properties
cors.allowed.origins=http://localhost:4200
```

## Environment Variables

For production deployment, use environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://your-host:3306/customer_lead_crm
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

## Deployment

The application can be deployed to any cloud platform (Render, Railway, AWS, etc.) that supports Java applications.

### Deploy to Render (Web Service) + Railway (MySQL)

This project is configured to run the Spring Boot API on **Render** while the **MySQL database is hosted on Railway** (Render's free tier only provides PostgreSQL).

**1. Set up MySQL on Railway**
- Create a project on [Railway](https://railway.app) and add a **MySQL** plugin.
- Open the MySQL service and copy the connection details. The provided URL looks like:
  ```
  mysql://user:password@host.railway.app:3306/customer_lead_crm
  ```
- You can run `database/mysql-script.sql` against this database, or let `spring.jpa.hibernate.ddl-auto=update` create the schema on first start.

**2. Push this repo to GitHub**
- This repo already contains `system.properties` (Java 21) and `render.yaml`.

**3. Create the Render Web Service**
- In Render, click **New → Web Service** and connect this GitHub repo.
- Render will detect `system.properties` and use Java 21. The `render.yaml` sets:
  - Build command: `./mvnw clean install`
  - Start command: `java -jar target/customer-lead-crm-api-0.0.1-SNAPSHOT.jar`
- In the **Environment** section, add these variables (copy values from your Railway MySQL connection string):
  | Key | Value (from Railway) |
  |-----|----------------------|
  | `SPRING_DATASOURCE_URL` | `jdbc:mysql://user:password@host.railway.app:3306/customer_lead_crm?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true` |
  | `SPRING_DATASOURCE_USERNAME` | Railway MySQL username |
  | `SPRING_DATASOURCE_PASSWORD` | Railway MySQL password |
  | `CORS_ALLOWED_ORIGINS` | Your frontend URL (e.g. `https://your-frontend.onrender.com`) |
- Render automatically provides the `PORT` variable, so no manual port setting is needed.

**4. Deploy**
- Click **Deploy**. Once live, the API is available at the Render URL and Swagger UI at `/swagger-ui.html`.

> Note: The `application.properties` file reads all database/port/CORS settings from environment variables with safe local defaults, so local development (`mvn spring-boot:run`) still works unchanged.

## Postman Collection

A complete Postman collection is provided at `postman-collection.json`. Import this into Postman to test all API endpoints.

## Default Credentials

For testing:
- Username: `admin`
- Password: `admin123`

## Error Handling

All errors return a consistent JSON format:

```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "timestamp": "2024-01-01T12:00:00"
}
```

HTTP Status Codes:
- 200 OK
- 201 Created
- 400 Bad Request (validation errors)
- 404 Not Found
- 500 Internal Server Error

## Development

### Adding New Features

1. Create/Update Entity in `entity` package
2. Create/Update Repository in `repository` package
3. Create/Update DTO in `dto` package
4. Create/Update Mapper in `mapper` package
5. Create/Update Service interface and implementation
6. Create/Update Controller
7. Add validation annotations as needed
8. Update Swagger documentation

### Running Tests

```bash
mvn test
```

## License

MIT License

## Support

For issues and questions, please contact the development team.
