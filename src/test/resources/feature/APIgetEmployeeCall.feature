@api @ui @smoketest

Feature: Testing Employees API calls

  Scenario: Testing getEmployee API call
    Given user sends API call to "getEmployee" with data
      | :param | 203 |
    When user navigates to HrApp login page
    And user logs in to application
    And user searchs for employeeId "203"
    Then user verifies employee info are matching with api response


  Scenario: Testing postEmployee API call
    Given user sends API call to "createEmployee" with data
      | firstName                        | Naveen  |
      | lastName                         | Ram     |
      | department.departmentName        | IT      |
      | department.location.locationCity | Chicago |
    When user navigates to HrApp login page
    And user logs in to application
    And user searchs for employeeId "createEmployee/employeeId"
#    Then user verifies employee info are matching with api response