-- Customer Lead CRM Database Script
-- Database: customer_lead_crm

-- Create Database
CREATE DATABASE IF NOT EXISTS customer_lead_crm;
USE customer_lead_crm;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS notes;
DROP TABLE IF EXISTS follow_up;
DROP TABLE IF EXISTS customer_lead;
DROP TABLE IF EXISTS lead_type;
DROP TABLE IF EXISTS users;

-- Create Users Table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Lead Type Table
CREATE TABLE lead_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Customer Lead Table
CREATE TABLE customer_lead (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customerName VARCHAR(255) NOT NULL,
    mobile VARCHAR(20) NOT NULL,
    alternateMobile VARCHAR(20),
    email VARCHAR(255),
    leadType BIGINT NOT NULL,
    city VARCHAR(255),
    address TEXT,
    requirement TEXT,
    leadSource VARCHAR(255),
    assignedExecutive VARCHAR(255),
    discussionDetails TEXT,
    visitDate DATE,
    nextFollowUpDate DATE,
    status VARCHAR(50) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (leadType) REFERENCES lead_type(id) ON DELETE RESTRICT,
    INDEX idx_customerName (customerName),
    INDEX idx_mobile (mobile),
    INDEX idx_city (city),
    INDEX idx_status (status),
    INDEX idx_priority (priority),
    INDEX idx_assignedExecutive (assignedExecutive),
    INDEX idx_createdDate (createdDate)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Follow Up Table
CREATE TABLE follow_up (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    leadId BIGINT NOT NULL,
    discussion TEXT NOT NULL,
    nextFollowUpDate DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (leadId) REFERENCES customer_lead(id) ON DELETE CASCADE,
    INDEX idx_leadId (leadId),
    INDEX idx_nextFollowUpDate (nextFollowUpDate),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create Notes Table
CREATE TABLE notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    leadId BIGINT NOT NULL,
    note TEXT NOT NULL,
    createdDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (leadId) REFERENCES customer_lead(id) ON DELETE CASCADE,
    INDEX idx_leadId (leadId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert Sample Users
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('executive', 'executive123', 'EXECUTIVE');

-- Insert Sample Lead Types
INSERT INTO lead_type (name, description) VALUES
('Website', 'Leads from website inquiries'),
('Referral', 'Leads from customer referrals'),
('Cold Call', 'Leads from cold calling'),
('Social Media', 'Leads from social media platforms');

-- Insert Sample Customer Leads
INSERT INTO customer_lead (customerName, mobile, alternateMobile, email, leadType, city, address, requirement, leadSource, assignedExecutive, discussionDetails, visitDate, nextFollowUpDate, status, priority, createdDate) VALUES
('John Smith', '9876543210', '9876543211', 'john.smith@example.com', 1, 'New York', '123 Main St, New York, NY 10001', 'Interested in premium CRM solution', 'Website Contact Form', 'executive', 'Initial call completed, showed interest in product', DATE_ADD(CURDATE(), INTERVAL 2 DAY), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'INTERESTED', 'HOT', NOW()),
('Sarah Johnson', '9876543212', NULL, 'sarah.johnson@example.com', 2, 'Los Angeles', '456 Oak Ave, Los Angeles, CA 90001', 'Small business CRM needs', 'Customer Referral', 'executive', 'Referred by existing customer, needs demo', NULL, DATE_ADD(CURDATE(), INTERVAL 3 DAY), 'CONTACTED', 'WARM', DATE_SUB(NOW(), INTERVAL 2 DAY)),
('Michael Brown', '9876543213', NULL, 'michael.brown@example.com', 3, 'Chicago', '789 Pine Rd, Chicago, IL 60601', 'Enterprise CRM solution', 'Cold Call', 'executive', 'Cold call made, requested information', NULL, DATE_ADD(CURDATE(), INTERVAL 5 DAY), 'NEW', 'COLD', DATE_SUB(NOW(), INTERVAL 1 DAY)),
('Emily Davis', '9876543214', NULL, 'emily.davis@example.com', 4, 'Houston', '321 Elm St, Houston, TX 77001', 'CRM for startup', 'LinkedIn', 'executive', 'LinkedIn inquiry, sent pricing', DATE_ADD(CURDATE(), INTERVAL 1 DAY), CURDATE(), 'VISIT_SCHEDULED', 'HOT', DATE_SUB(NOW(), INTERVAL 3 DAY));

-- Insert Sample Follow Ups
INSERT INTO follow_up (leadId, discussion, nextFollowUpDate, status, createdDate) VALUES
(1, 'Discussed pricing and features, customer interested', DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'FOLLOW_UP', NOW()),
(2, 'Scheduled demo for next week', DATE_ADD(CURDATE(), INTERVAL 3 DAY), 'FOLLOW_UP', DATE_SUB(NOW(), INTERVAL 1 DAY));

-- Insert Sample Notes
INSERT INTO notes (leadId, note, createdDate) VALUES
(1, 'Customer has budget approval, ready to proceed', NOW()),
(4, 'Customer requested technical documentation', DATE_SUB(NOW(), INTERVAL 1 DAY));

-- Display summary
SELECT 'Database setup completed successfully!' AS Message;
SELECT COUNT(*) AS TotalUsers FROM users;
SELECT COUNT(*) AS TotalLeadTypes FROM lead_type;
SELECT COUNT(*) AS TotalLeads FROM customer_lead;
SELECT COUNT(*) AS TotalFollowUps FROM follow_up;
SELECT COUNT(*) AS TotalNotes FROM notes;
