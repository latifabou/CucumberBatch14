Feature: Add Employee

  Background:
    # Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Add Employee button

  @sprint3 @regression
  Scenario: Adding one employee
    And user enter firstname and lastname
    And user clicks on save button
    Then employee added successfully

  @test1
  Scenario: Adding one employee using feature file
    And user enter "zalam" and "alia"
    And user clicks on save button
    Then employee added successfully

  @outline
  Scenario Outline: Adding multiple employees using feature file
    And user enter "<firstName>" and "<lastName>" for adding multiple employees
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | lastName |
      |gulnam     |mazar     |
      |rampal     |chambel   |
      |azam       |asel      |

  @datatable
  Scenario: Adding multiple employees using data table
    When user adds multiple employees and verify they are added successfully
      |firstName|middleName|lastName|
      |zara    |MS        |camilullah|
      |birgul  |MS        |ozgin     |
      |alina   |MS        |bob       |

  @excel
  Scenario: Adding multiple employees using excel file
    When user adds multiple employee from excel using "EmployeeData" and verify it
  @dbase
  Scenario: Adding one employee using feature file
    And user enter "HassanAhmad" and "Salama"
    And user captures employee Id
    And user clicks on save button
    Then employee added successfully on the frontEnd
    And employee is displayed in database

    @database
    Scenario: add employee from frontend and get data from db to verify it
      And user enter "Mbark" and "Boussoufa"
      And user captures employee Id
      And user clicks on save button
      Then added employee is available in my database
