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

CREATE TABLE Role (
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

