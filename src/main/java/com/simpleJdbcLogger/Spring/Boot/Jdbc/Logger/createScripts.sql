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
CREATE PROCEDURE InsertEmployees @Employees EmployeeTableType READONLY,
                                 @DefaultHireDate DATE = NULL
AS
BEGIN
    SET NOCOUNT ON;


INSERT INTO Employee (EmployeeName, DepartmentId, HireDate)
SELECT e.EmployeeName,
       e.DepartmentId,
       ISNULL(e.HireDate, @DefaultHireDate)
FROM @Employees e;
END

--INSERT DEPARTMENT
CREATE PROCEDURE InsertDepartment @DepartmentName NVARCHAR(100)
    AS
BEGIN
        SET NOCOUNT ON;

INSERT INTO Department (DepartmentName)
VALUES (@DepartmentName);
END

