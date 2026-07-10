# Backend Testing Instructions

## Prerequisites
- Backend running on port 8080
- MySQL database running with sample data loaded
- Frontend running on port 4200 (if using Angular)

## 1. Start the Backend

### Option A: Using Maven (Command Line)
```bash
cd customer-lead-crm-api
./mvnw clean install
./mvnw spring-boot:run
```

### Option B: Using STS/Eclipse
1. Right-click on `CustomerLeadCrmApiApplication.java`
2. Select "Run As" → "Spring Boot App"
3. Wait for "Started CustomerLeadCrmApiApplication" in console

## 2. Access Swagger UI

Once the backend is running, open in browser:
```
http://localhost:8080/swagger-ui.html
```

Or directly access the API docs:
```
http://localhost:8080/api-docs
```

## 3. Test Login API via Swagger

1. Go to `http://localhost:8080/swagger-ui.html`
2. Find the **Authentication** section
3. Click on `POST /api/auth/login`
4. Click "Try it out"
5. Enter the request body:
```json
{
  "username": "admin",
  "password": "admin123"
}
```
6. Click "Execute"
7. You should get a 200 OK response with user details

## 4. Test Login via cURL

```bash
# Successful login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Failed login (wrong password)
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"wrongpassword"}'
```

## 5. Test Login via Postman

Import the provided `postman-collection.json` file and test the login endpoint.

## 6. Frontend Testing

If your Angular frontend is running on port 4200:

### Check Frontend API Configuration
Ensure your Angular app is calling the correct endpoint:
```
http://localhost:8080/api/auth/login
```

### Common Frontend Issues:

1. **Wrong endpoint path**: Should be `/api/auth/login` not just `/login`
2. **Missing Content-Type header**: Must be `application/json`
3. **Incorrect JSON structure**: Must match LoginRequest format:
   ```json
   {
     "username": "admin",
     "password": "admin123"
   }
   ```
4. **CORS issues**: Backend is configured for `http://localhost:4200`

### Debug Frontend Request:

Open browser DevTools (F12) → Network tab → Try to login → Check:
- Request URL: Should be `http://localhost:8080/api/auth/login`
- Request Method: Should be `POST`
- Request Headers: Should include `Content-Type: application/json`
- Request Payload: Should show the JSON with username and password
- Response: Should show the error details

## 7. Verify Backend is Running

Check if backend is accessible:
```bash
# Check if server is running
curl http://localhost:8080/actuator/health

# Or check API docs
curl http://localhost:8080/api-docs
```

## Default Credentials

- **Username**: `admin`
- **Password**: `admin123`
- **Role**: ADMIN

Alternative:
- **Username**: `executive`
- **Password**: `executive123`
- **Role**: EXECUTIVE

## Troubleshooting 400 Bad Request

The 400 error means validation failed. Check:

1. **Request body is empty or null**
2. **Username field is missing or blank**
3. **Password field is missing or blank**
4. **JSON format is incorrect**
5. **Content-Type header is missing**

### Correct Request Example:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

## Database Setup (if needed)

If database is not set up:
```bash
mysql -u root -p < database/mysql-script.sql
```

Make sure MySQL is running and credentials in `application.properties` are correct:
- Username: `root`
- Password: `Python@2003`