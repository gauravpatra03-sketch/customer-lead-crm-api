# Swagger API Testing Guide

This document provides sample inputs for testing all API endpoints in the Customer Lead CRM system.

## Base URL
```
http://localhost:8080/api
```

---

## 1. Authentication APIs (`/api/auth`)

### 1.1 Login User
**Endpoint:** `POST /api/auth/login`

**Sample Request:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Notes:**
- `username` (required): The username of the user
- `password` (required): The password of the user

---

## 2. User Management APIs (`/api/users`)

### 2.1 Create User
**Endpoint:** `POST /api/users`

**Sample Request:**
```json
{
  "username": "johndoe",
  "password": "password123",
  "role": "ADMIN"
}
```

**Notes:**
- `username` (required): Unique username for the user
- `password` (required): User's password
- `role` (optional): User role (e.g., ADMIN, USER, MANAGER)

### 2.2 Get User by ID
**Endpoint:** `GET /api/users/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 2.3 Get User by Username
**Endpoint:** `GET /api/users/username/{username}`

**Sample Path Parameter:**
```
username: admin
```

### 2.4 Get All Users
**Endpoint:** `GET /api/users`

**No request body required**

### 2.5 Update User
**Endpoint:** `PUT /api/users/{id}`

**Sample Path Parameter:**
```
id: 1
```

**Sample Request Body:**
```json
{
  "username": "johndoe_updated",
  "password": "newpassword123",
  "role": "MANAGER"
}
```

### 2.6 Delete User
**Endpoint:** `DELETE /api/users/{id}`

**Sample Path Parameter:**
```
id: 1
```

---

## 3. Lead Type Management APIs (`/api/lead-types`)

### 3.1 Create Lead Type
**Endpoint:** `POST /api/lead-types`

**Sample Request:**
```json
{
  "name": "Residential",
  "description": "Leads for residential properties"
}
```

**Notes:**
- `name` (required): Name of the lead type
- `description` (optional): Description of the lead type

### 3.2 Get Lead Type by ID
**Endpoint:** `GET /api/lead-types/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 3.3 Get All Lead Types
**Endpoint:** `GET /api/lead-types`

**No request body required**

### 3.4 Update Lead Type
**Endpoint:** `PUT /api/lead-types/{id}`

**Sample Path Parameter:**
```
id: 1
```

**Sample Request Body:**
```json
{
  "name": "Commercial",
  "description": "Leads for commercial properties"
}
```

### 3.5 Delete Lead Type
**Endpoint:** `DELETE /api/lead-types/{id}`

**Sample Path Parameter:**
```
id: 1
```

---

## 4. Customer Lead Management APIs (`/api/leads`)

### 4.1 Create Lead
**Endpoint:** `POST /api/leads`

**Sample Request:**
```json
{
  "customerName": "John Smith",
  "mobile": "9876543210",
  "alternateMobile": "9876543211",
  "email": "john.smith@example.com",
  "leadTypeId": 1,
  "city": "Mumbai",
  "address": "123, Marine Drive, Mumbai - 400001",
  "requirement": "3BHK apartment with sea view",
  "leadSource": "Website",
  "assignedExecutive": "Jane Doe",
  "discussionDetails": "Customer interested in 3BHK apartments, budget around 2 crores",
  "visitDate": "2024-12-20",
  "nextFollowUpDate": "2024-12-25",
  "status": "INTERESTED",
  "priority": "HOT"
}
```

**Notes:**
- `customerName` (required): Full name of the customer
- `mobile` (required): Primary mobile number
- `alternateMobile` (optional): Alternate mobile number
- `email` (optional): Email address (must be valid format)
- `leadTypeId` (required): ID of the lead type
- `city` (optional): City name
- `address` (optional): Full address
- `requirement` (optional): Customer's requirement
- `leadSource` (optional): Source of the lead (e.g., Website, Referral, Walk-in)
- `assignedExecutive` (optional): Name of assigned executive
- `discussionDetails` (optional): Details of discussion with customer
- `visitDate` (optional): Scheduled visit date (format: YYYY-MM-DD)
- `nextFollowUpDate` (optional): Next follow-up date (format: YYYY-MM-DD)
- `status` (required): Lead status (NEW, CONTACTED, INTERESTED, FOLLOW_UP, VISIT_SCHEDULED, NEGOTIATION, CLOSED_WON, CLOSED_LOST, NOT_INTERESTED)
- `priority` (required): Lead priority (HOT, WARM, COLD, NOT_CUSTOMER)

### 4.2 Get Lead by ID
**Endpoint:** `GET /api/leads/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 4.3 Get All Leads
**Endpoint:** `GET /api/leads`

**No request body required**

### 4.4 Update Lead
**Endpoint:** `PUT /api/leads/{id}`

**Sample Path Parameter:**
```
id: 1
```

**Sample Request Body:**
```json
{
  "customerName": "John Smith Updated",
  "mobile": "9876543210",
  "alternateMobile": "9876543211",
  "email": "john.smith.updated@example.com",
  "leadTypeId": 2,
  "city": "Delhi",
  "address": "456, Connaught Place, Delhi - 110001",
  "requirement": "4BHK apartment with garden",
  "leadSource": "Referral",
  "assignedExecutive": "Jane Doe",
  "discussionDetails": "Customer interested in 4BHK, budget increased to 3 crores",
  "visitDate": "2024-12-22",
  "nextFollowUpDate": "2024-12-28",
  "status": "NEGOTIATION",
  "priority": "HOT"
}
```

### 4.5 Delete Lead
**Endpoint:** `DELETE /api/leads/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 4.6 Search Leads
**Endpoint:** `POST /api/leads/search`

**Sample Request:**
```json
{
  "customerName": "John",
  "mobile": "9876543210",
  "email": "john@example.com",
  "city": "Mumbai",
  "status": "INTERESTED",
  "priority": "HOT",
  "leadSource": "Website",
  "assignedExecutive": "Jane Doe",
  "fromDate": "2024-12-01",
  "toDate": "2024-12-31"
}
```

**Notes:**
- All fields are optional
- `customerName`: Search by customer name (partial match)
- `mobile`: Search by mobile number
- `email`: Search by email
- `city`: Search by city
- `status`: Filter by status
- `priority`: Filter by priority
- `leadSource`: Filter by lead source
- `assignedExecutive`: Search by assigned executive
- `fromDate`: Filter from date (format: YYYY-MM-DD)
- `toDate`: Filter to date (format: YYYY-MM-DD)

### 4.7 Get Dashboard Stats
**Endpoint:** `GET /api/leads/dashboard`

**No request body required**

### 4.8 Get Recent Leads
**Endpoint:** `GET /api/leads/recent`

**No request body required**

### 4.9 Get Follow-ups for a Lead
**Endpoint:** `GET /api/leads/{leadId}/followups`

**Sample Path Parameter:**
```
leadId: 1
```

### 4.10 Get Notes for a Lead
**Endpoint:** `GET /api/leads/{leadId}/notes`

**Sample Path Parameter:**
```
leadId: 1
```

---

## 5. Follow-up Management APIs (`/api/followups`)

### 5.1 Create Follow-up
**Endpoint:** `POST /api/followups`

**Sample Request:**
```json
{
  "leadId": 1,
  "discussion": "Discussed pricing and payment options. Customer is interested.",
  "nextFollowUpDate": "2024-12-28",
  "status": "FOLLOW_UP"
}
```

**Notes:**
- `leadId` (required): ID of the associated lead
- `discussion` (required): Discussion details
- `nextFollowUpDate` (required): Next follow-up date (format: YYYY-MM-DD)
- `status` (required): Follow-up status (NEW, CONTACTED, INTERESTED, FOLLOW_UP, VISIT_SCHEDULED, NEGOTIATION, CLOSED_WON, CLOSED_LOST, NOT_INTERESTED)

### 5.2 Get Follow-up by ID
**Endpoint:** `GET /api/followups/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 5.3 Get Follow-ups by Lead ID
**Endpoint:** `GET /api/followups/lead/{leadId}`

**Sample Path Parameter:**
```
leadId: 1
```

### 5.4 Get All Follow-ups
**Endpoint:** `GET /api/followups`

**No request body required**

### 5.5 Update Follow-up
**Endpoint:** `PUT /api/followups/{id}`

**Sample Path Parameter:**
```
id: 1
```

**Sample Request Body:**
```json
{
  "leadId": 1,
  "discussion": "Discussed pricing and payment options. Customer agreed to visit site.",
  "nextFollowUpDate": "2024-12-30",
  "status": "VISIT_SCHEDULED"
}
```

### 5.6 Delete Follow-up
**Endpoint:** `DELETE /api/followups/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 5.7 Get Today's Follow-ups
**Endpoint:** `GET /api/followups/today`

**No request body required**

### 5.8 Get Overdue Follow-ups
**Endpoint:** `GET /api/followups/overdue`

**No request body required**

### 5.9 Get Tomorrow's Follow-ups
**Endpoint:** `GET /api/followups/tomorrow`

**No request body required**

### 5.10 Get Recent Follow-ups
**Endpoint:** `GET /api/followups/recent`

**No request body required**

---

## 6. Notes Management APIs (`/api/notes`)

### 6.1 Create Note
**Endpoint:** `POST /api/notes`

**Sample Request:**
```json
{
  "leadId": 1,
  "note": "Customer called and asked about pricing details. Sent brochure via email."
}
```

**Notes:**
- `leadId` (required): ID of the associated lead
- `note` (required): Note content

### 6.2 Get Note by ID
**Endpoint:** `GET /api/notes/{id}`

**Sample Path Parameter:**
```
id: 1
```

### 6.3 Get Notes by Lead ID
**Endpoint:** `GET /api/notes/lead/{leadId}`

**Sample Path Parameter:**
```
leadId: 1
```

### 6.4 Get All Notes
**Endpoint:** `GET /api/notes`

**No request body required**

### 6.5 Update Note
**Endpoint:** `PUT /api/notes/{id}`

**Sample Path Parameter:**
```
id: 1
```

**Sample Request Body:**
```json
{
  "leadId": 1,
  "note": "Customer called and asked about pricing details. Sent brochure via email. Follow-up scheduled for next week."
}
```

### 6.6 Delete Note
**Endpoint:** `DELETE /api/notes/{id}`

**Sample Path Parameter:**
```
id: 1
```

---

## 7. Dashboard APIs (`/api/dashboard`)

### 7.1 Get Dashboard Statistics
**Endpoint:** `GET /api/dashboard/stats`

**No request body required**

**Response includes:**
- Total leads
- New leads
- Follow-ups scheduled
- Closed won leads
- Closed lost leads
- Hot leads count
- Warm leads count
- Cold leads count

---

## 8. Reminder APIs (`/api/reminders`)

### 8.1 Get Today's Reminders
**Endpoint:** `GET /api/reminders/today`

**No request body required**

### 8.2 Get Upcoming Reminders
**Endpoint:** `GET /api/reminders/upcoming`

**No request body required**

### 8.3 Get Overdue Reminders
**Endpoint:** `GET /api/reminders/overdue`

**No request body required**

---

## 9. Report APIs (`/api/reports`)

### 9.1 Get Report Summary
**Endpoint:** `GET /api/reports/summary`

**No request body required**

**Response includes:**
- Total leads
- Total follow-ups
- Conversion rate
- Lead source distribution
- Status distribution
- Priority distribution

---

## Enum Values Reference

### Status Values
- `NEW`
- `CONTACTED`
- `INTERESTED`
- `FOLLOW_UP`
- `VISIT_SCHEDULED`
- `NEGOTIATION`
- `CLOSED_WON`
- `CLOSED_LOST`
- `NOT_INTERESTED`

### Priority Values
- `HOT`
- `WARM`
- `COLD`
- `NOT_CUSTOMER`

### User Roles
- `ADMIN`
- `USER`
- `MANAGER`

---

## Testing Workflow

### Step 1: Authentication
1. First, test the login endpoint to get authenticated
2. Use the returned token for subsequent requests if authentication is required

### Step 2: Setup Master Data
1. Create Lead Types (POST `/api/lead-types`)
2. Create Users (POST `/api/users`)

### Step 3: Create Customer Leads
1. Create a new lead (POST `/api/leads`)
2. Note the lead ID returned in the response

### Step 4: Add Follow-ups and Notes
1. Create follow-ups for the lead (POST `/api/followups`)
2. Create notes for the lead (POST `/api/notes`)

### Step 5: Test Retrieval Operations
1. Get lead by ID
2. Get all leads
3. Search leads with various criteria
4. Get follow-ups for a lead
5. Get notes for a lead

### Step 6: Test Update Operations
1. Update lead details
2. Update follow-up details
3. Update note details

### Step 7: Test Dashboard and Reports
1. Get dashboard statistics
2. Get report summary
3. Get recent leads
4. Get reminders

### Step 8: Test Cleanup
1. Delete notes
2. Delete follow-ups
3. Delete leads
4. Delete lead types
5. Delete users

---

## Notes for Testing

1. **Date Format**: All dates should be in ISO format (YYYY-MM-DD)
2. **Date-Time Format**: Date-time fields use ISO format (YYYY-MM-DDTHH:MM:SS)
3. **Status and Priority**: Must use exact enum values (case-sensitive)
4. **Required Fields**: Fields marked as required must be provided
5. **ID References**: When creating related entities (follow-ups, notes), ensure the referenced lead ID exists
6. **Sequential Testing**: Some endpoints depend on data created by previous endpoints (e.g., follow-ups require a lead ID)

---

## Quick Reference - Endpoint Summary

| # | Method | Endpoint | Description |
|---|--------|----------|-------------|
| 1 | POST | /api/auth/login | Login user |
| 2 | POST | /api/users | Create user |
| 3 | GET | /api/users/{id} | Get user by ID |
| 4 | GET | /api/users/username/{username} | Get user by username |
| 5 | GET | /api/users | Get all users |
| 6 | PUT | /api/users/{id} | Update user |
| 7 | DELETE | /api/users/{id} | Delete user |
| 8 | POST | /api/lead-types | Create lead type |
| 9 | GET | /api/lead-types/{id} | Get lead type by ID |
| 10 | GET | /api/lead-types | Get all lead types |
| 11 | PUT | /api/lead-types/{id} | Update lead type |
| 12 | DELETE | /api/lead-types/{id} | Delete lead type |
| 13 | POST | /api/leads | Create lead |
| 14 | GET | /api/leads/{id} | Get lead by ID |
| 15 | GET | /api/leads | Get all leads |
| 16 | PUT | /api/leads/{id} | Update lead |
| 17 | DELETE | /api/leads/{id} | Delete lead |
| 18 | POST | /api/leads/search | Search leads |
| 19 | GET | /api/leads/dashboard | Get dashboard stats |
| 20 | GET | /api/leads/recent | Get recent leads |
| 21 | GET | /api/leads/{leadId}/followups | Get follow-ups for lead |
| 22 | GET | /api/leads/{leadId}/notes | Get notes for lead |
| 23 | POST | /api/followups | Create follow-up |
| 24 | GET | /api/followups/{id} | Get follow-up by ID |
| 25 | GET | /api/followups/lead/{leadId} | Get follow-ups by lead ID |
| 26 | GET | /api/followups | Get all follow-ups |
| 27 | PUT | /api/followups/{id} | Update follow-up |
| 28 | DELETE | /api/followups/{id} | Delete follow-up |
| 29 | GET | /api/followups/today | Get today's follow-ups |
| 30 | GET | /api/followups/overdue | Get overdue follow-ups |
| 31 | GET | /api/followups/tomorrow | Get tomorrow's follow-ups |
| 32 | GET | /api/followups/recent | Get recent follow-ups |
| 33 | POST | /api/notes | Create note |
| 34 | GET | /api/notes/{id} | Get note by ID |
| 35 | GET | /api/notes/lead/{leadId} | Get notes by lead ID |
| 36 | GET | /api/notes | Get all notes |
| 37 | PUT | /api/notes/{id} | Update note |
| 38 | DELETE | /api/notes/{id} | Delete note |
| 39 | GET | /api/dashboard/stats | Get dashboard statistics |
| 40 | GET | /api/reminders/today | Get today's reminders |
| 41 | GET | /api/reminders/upcoming | Get upcoming reminders |
| 42 | GET | /api/reminders/overdue | Get overdue reminders |
| 43 | GET | /api/reports/summary | Get report summary |

**Total Endpoints: 43**