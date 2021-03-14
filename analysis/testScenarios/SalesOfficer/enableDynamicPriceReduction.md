### **Test Scenarios for Enable Dynamic Price Reduction**

To read Use Case click [**here**]( ../../UseCasesSalesOfficer.md )

**General:**

1. Sales Officer selects a flight (route).
2. System displays multiple options for that specific flight (route).
3. Sales Officer selects "Configure dynamic discount".


**Test Scenario 1: Everything works fine (monetary value inserted in €)**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and 10 € as discount amount.
6. System saves the change and adapts prices accordingly.


**Test Scenario 2: Everything works fine (monetary value inserted in %)**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and 20% as discount amount.
6. System saves the change and adapts prices accordingly.


**Test Scenario 3: There is already a static discount for this specific flight**

4. System finds an existing static discount for this specific flight (route).
5. System informs the Sales Officer that they have to delete the static discount before they can enable a static discount.


**Test Scenario 4: The monetary value inserted equals zero, less than zero or more than the actual price**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and -17 € as discount amount.
6. System gives an error message and asks for another input.


**Test Scenario 6: The monetary value, expressed as a percentage exceeds 99%, or is below 1%**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and 146% as discount amount.
6. System gives an error message and asks for another input.


**Test Scenario 7: Sales Officer does not input a monetary value**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and does not input a monetary value.
6. System gives an error message and asks for a monetary value.


**Test Scenario 8: Sales Officer does not input a discount type**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects 20% as discount amount and does not select a discount type.
6. System gives an error message and asks for a discount type.


**Test Scenario 9: Everything works fine (Sales Officer also gives a start and end date)**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and 20% as discount amount and also selects the start date 01/12/2021 and the end date 18/12/2021.
6. System saves the change and adapts prices accordingly.


**Test Scenario 10: Sales Officer inserts a start and/or end date that is not in the future, but in the past**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destination" as discount type and 20% as discount amount and also selects the start date 01/12/1930 and the end date 18/12/1930.
6. System gives an error message and asks for a valid start and end date in the future.




