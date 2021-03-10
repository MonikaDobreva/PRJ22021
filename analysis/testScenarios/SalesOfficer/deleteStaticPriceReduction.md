### **Test Scenarios for Delete Static Price Reduction**

**General**

1. Sales Officer selects a flight (route).
2. System displays multiple options for that specific flight (route).
3. Sales Officer selects "Delete static discount".
4. System displays the discount amount and the end date of the discount and asks the Sales Officer if they are certain that they want to delete this discount.


**Test Scenario 1: Everything works fine, Sales Officer decided to delete the discount** 

5. Sales Officer selects "Delete".
6. System saves the change and adapts prices accordingly.


**Test Scenario 2: Everything works fine, Sales Officer decides to not delete the discount**

5. Sales Officer selects "Cancel".
6. No changes were made.


**Test Scenario 3: The Sales Officer does not select anything**

5. Sales Officer does not select anything.
7. No changes were made.

