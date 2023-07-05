Feature: Manage Sauce Demo
Background: Log in User
  Given User is logged in

  @Regression
 Scenario Outline: Add product to cart and do checkout
    When User adds a <Product adder> to cart
    And Navigate to cart
    And Checkout with the following identity
      | Fields          | Values  |
      | First Name      | Ann     |
      | Last Name       | Johnson |
      | Zip/Postal Code | 194348  |
    Then Checkout is successfully complete
    Examples:
      | Product adder                     |
      | "add-to-cart-sauce-labs-onesie"   |
      | "add-to-cart-sauce-labs-backpack" |

  @Regression
  Scenario: Add and remove several products to and from cart
    When User adds several products to cart
      | Sauce Labs Backpack | add-to-cart-sauce-labs-backpack |
      | Sauce Labs Onesie   | add-to-cart-sauce-labs-onesie   |
    And Navigate to cart
    And User removes several products from cart
      | Sauce Labs Backpack | remove-sauce-labs-backpack |
      | Sauce Labs Onesie   | remove-sauce-labs-onesie   |
    Then Cart is empty

  @Regression
Scenario: Add to and remove product from cart on Inventory Details page
  When User opens Inventory Details page for "Sauce Labs Backpack"
  And User adds a "add-to-cart-sauce-labs-backpack" to cart
  And User removes a "remove-sauce-labs-backpack" from cart on Inventory Details page
  Then Shopping cart badge should be equal to 0