Feature: Login to the system
  As a user
  I should be able to login to the system

  @Login
  Scenario Outline: Verify user allow to login with correct credentials
    Given I have opened the webpage
    When I type "<Username>" in "<UsernameField>" textarea
    And I type "<Password>" in "<PasswordField>" textarea
    And I click button "<Submit>"
    Then I see the exact "<LoginSuccessMessage>" displays on "<HomePageHeader>"
    And I see "<LogoutButton>" getting displayed

    Examples:
      | Username       | UsernameField       | Password       | PasswordField       | Submit             | LoginSuccessMessage  | HomePageHeader | LogoutButton      |
      | Login_Username | Login_UsernameField | Login_Password | Login_PasswordField | Login_SubmitButton | Login_SuccessMessage | Home_Title     | Home_LogoutButton |