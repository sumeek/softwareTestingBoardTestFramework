
Feature: Workflow Test

  Scenario: Test end to end workflow

    When I add product "Gwyn Endurance Tee" of size "M", color "Green" and quantity "4"

    And I checkout the product by adding shipping address

    And I go to home page

    Then I make sure order amount is "92.00"

    And I update quantity of item to "3"

    Then I add product "Gwyn Endurance Tee" of size "S", color "Yellow" and quantity "1"

    Then I add product "Quest Lumaflex™ Band" of size "", color "" and quantity "1"

    Then I make sure order amount is "140.00"

    And I checkout the product by using existing shipping address

    And I place the order

    And Make sure order is successful




