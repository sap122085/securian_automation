Feature: In Securian
  As a QA
  I want to verify that,
  • User should be able to submit form with all required fields filled in
  • Additional Social Security fields should display/hide based on Social Security benefits toggle
  • User should be able to submit form with all fields filled in
  • User should be able to update default calculator values


  Background: User opens securian application
    Given User navigates to Securian Financial page

  @ui_test @smoke
  Scenario: Verify that user should be able to submit form with all required fields filled in
    When User enters "40" in "current-age" text field
    And User enters "68" in "retirement-age" text field
    And User enters "100000" in "current-income" text field
    And User enters "500000" in "current-total-savings" text field
    And User enters "10" in "current-annual-savings" text field
    And User enters "1" in "savings-increase-rate" text field
    And User selects "social-security-benefits" as "Yes"
    And User selects "marital-status" as "Married"
    And User clicks on "Calculate" button
    Then User should see results
