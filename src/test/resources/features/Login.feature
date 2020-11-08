@login
Feature: As user I want to be able to login under different roles
# this is a comment
#Background - test pre-condition
#  will be executed before every scenario in the particular feature file
  Background: common steps
    Given user is on the login page

  Scenario: Login as a sales manger
    When user logs in
    Then user should see dashboard page

  @parametrized_test @smoke_test
  Scenario: Parametrized login
    When user logs in as a "store manager"
    Then user should see dashboard page

  @parametrized_test @smoke_test
  Scenario: Parametrized login
    When user logs in as a "sales manager"
    Then user should see dashboard page

  @s_o @with_two_columns
  Scenario Outline: Parametrized login as <role>
    When user logs in as a "<role>"
    Then user should see "<page_title>" page
    Examples:
      | role          | page_title      |
      | sales manager | Dashboard       |
      | store manager | Dashboard       |
      | driver        | Quick Launchpad |

#    role - variable. You can name parameters as you want.
#   1st row always reserved for parameters
# auto-formatting on mac:     command + option + L
# auto-formatting on windows: control + alt + L
#"driver" - is a parameter. "" allows to do test parametrization which helps to re-use test steps

  @negative_login
  Scenario: Invalid password
    When user logs in with "storemanager85" username and "wrong" password
    Then user verifies that "Invalid user name or password." message is displayed user verifies that "Invalid user name or password." message is displayed

  @negative_scenario_outline
  Scenario Outline: Invalid login
    When user logs in with "<username>" username and "<password>" password
    Then user verifies that "<message>" message is displayed
    Examples: data set
      | username | password | message                        |
      | wrong    | bad      | Invalid user name or password. |
      | wrong    | bad      | Invalid user name or password. |