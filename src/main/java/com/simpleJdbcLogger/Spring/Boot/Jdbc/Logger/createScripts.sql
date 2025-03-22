CREATE TABLE Department
(
    DepartmentId   INT PRIMARY KEY IDENTITY,
    DepartmentName NVARCHAR(100) NOT NULL
);

CREATE TABLE Employee
(
    EmployeeId   INT PRIMARY KEY IDENTITY,
    EmployeeName NVARCHAR(100) NOT NULL,
    DepartmentId INT           NOT NULL,
    HireDate     DATE          NOT NULL,
    FOREIGN KEY (DepartmentId) REFERENCES Department (DepartmentId)
);

CREATE TYPE EmployeeTableType AS TABLE
    (
    EmployeeName NVARCHAR(100),
    DepartmentId INT,
    HireDate     DATE
    );

-- INSERT EMPLOYEE
CREATE PROCEDURE InsertEmployees
    @Employees EmployeeTableType READONLY,
    @DefaultHireDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Inserted TABLE (
        EmployeeId INT,
        EmployeeName NVARCHAR(100),
        DepartmentId INT,
        HireDate DATE
    );

INSERT INTO Employee (EmployeeName, DepartmentId, HireDate)
    OUTPUT INSERTED.EmployeeId, INSERTED.EmployeeName, INSERTED.DepartmentId, INSERTED.HireDate
    INTO @Inserted
SELECT
    e.EmployeeName,
    e.DepartmentId,
    ISNULL(e.HireDate, @DefaultHireDate)
FROM @Employees e;

SELECT TOP 1 * FROM @Inserted;
END
GO


--INSERT DEPARTMENT
CREATE PROCEDURE InsertDepartment
    @DepartmentName NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @Inserted TABLE (
                                DepartmentId INT,
                                DepartmentName NVARCHAR(100)
                            );

INSERT INTO Department (DepartmentName)
    OUTPUT INSERTED.DepartmentId, INSERTED.DepartmentName INTO @Inserted
VALUES (@DepartmentName);

SELECT * FROM @Inserted;
END
go


-- GET ALL EMPLOYEES
CREATE PROCEDURE GetAllEmployees
    AS
BEGIN
    SET NOCOUNT ON;

SELECT
    EmployeeId,
    EmployeeName,
    DepartmentId,
    HireDate
FROM Employee;
END
GO



