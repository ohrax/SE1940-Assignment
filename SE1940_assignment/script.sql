CREATE DATABASE SE1940_LeaveManagementDB
GO

USE SE1940_LeaveManagementDB
GO

CREATE TABLE Division (
    divisionId INT PRIMARY KEY IDENTITY(1,1),
    divisionName NVARCHAR(255) NOT NULL,
    createdDate DATETIME DEFAULT GETDATE(),
    updatedDate DATETIME DEFAULT GETDATE()
);

CREATE TABLE Permission (
    permissionId INT PRIMARY KEY IDENTITY(1,1),
    path NVARCHAR(255) NOT NULL,
    permissionList NVARCHAR(255) NOT NULL
);

CREATE TABLE [Role] (
    roleId INT PRIMARY KEY IDENTITY(1,1),
    roleName NVARCHAR(255) NOT NULL,
    createdDate DATETIME DEFAULT GETDATE(),
    updatedDate DATETIME DEFAULT GETDATE()
);

CREATE TABLE RolePermission (
    id INT PRIMARY KEY IDENTITY(1,1),
    roleId INT FOREIGN KEY REFERENCES Role(roleId),
    permissionId INT FOREIGN KEY REFERENCES Permission(permissionId)
);

CREATE TABLE [User] (
    userId INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(255) UNIQUE NOT NULL,
    fullname NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) UNIQUE NOT NULL,
    password NVARCHAR(255) NOT NULL,
    status NVARCHAR(50) CHECK (status IN ('Active', 'Inactive')),
    createdDate DATETIME DEFAULT GETDATE(),
    updatedDate DATETIME DEFAULT GETDATE(),
    directManagerId INT NULL FOREIGN KEY REFERENCES [User](userId)
);

CREATE TABLE UserDivision (
    id INT PRIMARY KEY IDENTITY(1,1),
    userId INT FOREIGN KEY REFERENCES [User](userId),
    divisionId INT FOREIGN KEY REFERENCES Division(divisionId),
    position NVARCHAR(255) NOT NULL
);

CREATE TABLE UserRole (
    id INT PRIMARY KEY IDENTITY(1,1),
    userId INT FOREIGN KEY REFERENCES [User](userId),
    roleId INT FOREIGN KEY REFERENCES Role(roleId)
);

CREATE TABLE AbsentRequest (
    requestId INT PRIMARY KEY IDENTITY(1,1),
    title NVARCHAR(255) NOT NULL,
    fromDate DATETIME NOT NULL,
    toDate DATETIME NOT NULL,
    createdBy INT FOREIGN KEY REFERENCES [User](userId),
    status NVARCHAR(50) CHECK (status IN ('Approved', 'In Progress', 'Rejected')),
    processedBy INT NULL FOREIGN KEY REFERENCES [User](userId),
    reason NVARCHAR(255),
    createdDate DATETIME DEFAULT GETDATE(),
    updatedDate DATETIME DEFAULT GETDATE()
);

INSERT INTO Division (divisionName) VALUES 
('IT'),
('HR');

INSERT INTO Permission (path, permissionList) VALUES 
('/request/create', 'CRUD'),
('/request/review', 'CRUD'),
('/request/list', 'R');

INSERT INTO [Role] (roleName) VALUES 
('Admin'),
('Leader'),
('Employee');

INSERT INTO RolePermission (roleId, permissionId) VALUES 
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 3),
(3, 1), (3, 3);

INSERT INTO [User] (username, fullname, email, password, status, directManagerId) VALUES 
('admin1', 'Admin One', 'admin1@company.com', 'adminpass', 'Active', NULL),
('leader1', 'Leader One', 'leader1@company.com', 'leaderpass', 'Active', NULL),
('leader2', 'Leader Two', 'leader2@company.com', 'leaderpass', 'Active', NULL),
('emp1', 'Employee One', 'emp1@company.com', 'emppass', 'Active', 2),
('emp2', 'Employee Two', 'emp2@company.com', 'emppass', 'Active', 3);

INSERT INTO UserDivision (userId, divisionId, position) VALUES 
(1, 1, 'DULead'),
(2, 1, 'TLead'),
(3, 2, 'TLead'),
(4, 1, 'Member'),
(5, 2, 'Member');

INSERT INTO UserRole (userId, roleId) VALUES 
(1, 1),
(2, 2),
(3, 2),
(4, 3),
(5, 3);

INSERT INTO AbsentRequest (title, fromDate, toDate, createdBy, status, processedBy, reason) VALUES 
('Vacation', '2025-03-10 08:00:00', '2025-03-12 17:00:00', 4, 'In Progress', NULL, 'Nghỉ đi du lịch'),
('Sick Leave', '2025-03-15 08:00:00', '2025-03-16 17:00:00', 5, 'Approved', 3, 'Ốm cần nghỉ'),
('Conference', '2025-03-20 08:00:00', '2025-03-22 17:00:00', 4, 'Rejected', 2, 'Tham gia hội nghị'),
('Personal Day', '2025-03-25 08:00:00', '2025-03-25 17:00:00', 5, 'In Progress', NULL, 'Việc cá nhân');

