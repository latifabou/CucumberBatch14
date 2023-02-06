@api
Feature: API workflow test

  Background: for generating the token before every request
    Given a JWT is generated

  @regression1 @smoke1
  Scenario: API test case for creating the employee
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request

  @smoke1
    Scenario: Getting the created employee
      Given a request is prepared for getting a created employee
      When a GET call is made to get this employee
      Then the status code for this emp is 200
      And the employee id "employee.employee_id" should match with global emp id
      And the retrieved data at "employee" object should match with the data used for creating the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday  |emp_status             |emp_job_title|
      |Samir       |   Alfonso   |   ms          |    Male  |  2010-01-14  | confirmed  |     HumanLover |

  @regression1
  Scenario: API test case for creating the employee
    Given a request is prepared for creating an employee by passing json body
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request


  Scenario: API test case for creating the employee using highly dynamic body
    Given a request is prepared for creating an employee with dynamic data "Samir" , "Alfonso"  , "ms" , "M" , "2010-01-14" , "confirmed" , "HumanLover"
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global to be used for other request


  Scenario: API test case for updating the employee using highly dynamic body
    Given a request is prepared for updating an employee with dynamic data "empId","ramzi" , "Nassima"  , "ms" , "F" , "2005-08-20" , "Probation" , "Superviser"
    When a PUT call is made to update an employee
    Then the status code for updating an employee is 200
    And the response body update contains key "Message" and value "Employee record Updated"


  Scenario: Getting the updated employee
    Given a request is prepared for getting an updated employee
    When a GET call is made to get this updated employee
    Then the status code for this emp is 200
    And the employee id "employee.employee_id" at the updated employee object should match with global emp id
    And the retrieved data at "employee" object should match with the data used for updating the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday  |emp_status             |emp_job_title|
      |ramzi       |   Nassima   |   ms          |    Female  |  2005-08-20  | Probation  | Superviser     |
