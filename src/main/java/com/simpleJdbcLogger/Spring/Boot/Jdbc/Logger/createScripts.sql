-- Drop procedures if they exist
IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'InsertEmployees')
    DROP PROCEDURE InsertEmployees;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'InsertDepartment')
    DROP PROCEDURE InsertDepartment;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'GetAllEmployees')
    DROP PROCEDURE GetAllEmployees;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'GetSettingByKey')
    DROP PROCEDURE GetSettingByKey;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'InsertEmployeesMultiple')
    DROP PROCEDURE InsertEmployeesMultiple;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'P'
             AND name = 'GetProcedureParameters')
    DROP PROCEDURE GetProcedureParameters;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'U'
             AND name = 'Settings')
    DROP TABLE Settings;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'U'
             AND name = 'Department')
    DROP TABLE Department;
GO

IF EXISTS (SELECT *
           FROM sys.objects
           WHERE type = 'U'
             AND name = 'Employee')
    DROP TABLE Employee;
GO

IF EXISTS (SELECT *
           FROM sys.types
           WHERE name = 'EmployeeTableType')
    DROP TYPE EmployeeTableType;
GO


-- CREATE TABLE Department
CREATE TABLE Department
(
    DepartmentId   INT PRIMARY KEY IDENTITY,
    DepartmentName NVARCHAR(100) NOT NULL
);
GO

-- CREATE TABLE Employee WITHOUT FOREIGN KEY
CREATE TABLE Employee
(
    EmployeeId   INT PRIMARY KEY IDENTITY,
    EmployeeName NVARCHAR(100) NOT NULL,
    DepartmentId INT           NOT NULL,
    HireDate     DATE          NOT NULL
);
GO

-- CREATE TYPE EmployeeTableType
CREATE TYPE EmployeeTableType AS TABLE
(
    EmployeeName NVARCHAR(100),
    DepartmentId INT,
    HireDate     DATE
);
GO

-- INSERT EMPLOYEE PROCEDURE
CREATE PROCEDURE InsertEmployees @Employees EmployeeTableType READONLY,
                                 @DefaultHireDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Inserted TABLE
                      (
                          EmployeeId   INT,
                          EmployeeName NVARCHAR(100),
                          DepartmentId INT,
                          HireDate     DATE
                      );

    INSERT INTO Employee (EmployeeName, DepartmentId, HireDate)
    OUTPUT INSERTED.EmployeeId,
           INSERTED.EmployeeName,
           INSERTED.DepartmentId,
           INSERTED.HireDate
        INTO @Inserted
    SELECT e.EmployeeName,
           e.DepartmentId,
           ISNULL(e.HireDate, @DefaultHireDate)
    FROM @Employees e;

    SELECT TOP 1 * FROM @Inserted;
END;
GO

-- INSERT DEPARTMENT PROCEDURE
CREATE PROCEDURE InsertDepartment @DepartmentName NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Inserted TABLE
                      (
                          DepartmentId   INT,
                          DepartmentName NVARCHAR(100)
                      );

    INSERT INTO Department (DepartmentName)
    OUTPUT INSERTED.DepartmentId,
           INSERTED.DepartmentName INTO @Inserted
    VALUES (@DepartmentName);

    SELECT * FROM @Inserted;
END;
GO

-- GET ALL EMPLOYEES PROCEDURE
CREATE PROCEDURE GetAllEmployees
AS
BEGIN
    SET NOCOUNT ON;

    SELECT EmployeeId,
           EmployeeName,
           DepartmentId,
           HireDate
    FROM Employee;
END;
GO

-- CREATE TABLE SETTINGS
CREATE TABLE Settings
(
    Id            INT IDENTITY (1,1) PRIMARY KEY,
    [Key]         NVARCHAR(100) NOT NULL UNIQUE,
    [Value]       NVARCHAR(MAX) NOT NULL,
    [Description] NVARCHAR(255) NULL
);
GO

-- GET SETTING BY KEY PROCEDURE
CREATE PROCEDURE GetSettingByKey @Key NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    SELECT Id,
           [Key],
           [Value],
           [Description]
    FROM Settings
    WHERE [Key] = @Key;
END;
GO

-- INSERT MULTIPLE EMPLOYEES PROCEDURE
CREATE PROCEDURE InsertEmployeesMultiple @Employees EmployeeTableType READONLY,
                                         @DefaultHireDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Inserted TABLE
                      (
                          EmployeeId   INT,
                          EmployeeName NVARCHAR(100),
                          DepartmentId INT,
                          HireDate     DATE
                      );

    INSERT INTO Employee (EmployeeName, DepartmentId, HireDate)
    OUTPUT INSERTED.EmployeeId,
           INSERTED.EmployeeName,
           INSERTED.DepartmentId,
           INSERTED.HireDate
        INTO @Inserted
    SELECT e.EmployeeName,
           e.DepartmentId,
           ISNULL(e.HireDate, @DefaultHireDate)
    FROM @Employees e;

    SELECT * FROM @Inserted;
END;
GO

-- CREATE PROCEDURE GET PROCEDURE PARAMETERS
CREATE PROCEDURE GetProcedureParameters
AS
BEGIN
    SELECT o.name AS ProcedureName,
           p.name AS ParameterName,
           t.name AS ParameterType
    FROM sys.procedures o
             JOIN sys.parameters p ON o.object_id = p.object_id
             JOIN sys.types t ON p.user_type_id = t.user_type_id
    ORDER BY o.name, p.parameter_id;
END;
GO

-- INSERT DEFAULT SETTINGS
INSERT INTO Settings([key], value, description)
VALUES ('CUSTOM_JDBC_LOGGER_ENABLED', 'true', 'Add logger over SimpleJdbcCall');
INSERT INTO Settings([key], value, description)
VALUES ('MAX_ROWS_TO_PRINT', '3', 'Set numbers of rows to print');
GO
