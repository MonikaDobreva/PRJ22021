### **Test Scenarios for NAME OF USE CASE**


**General:**

> All the steps of the Scenario that are exactly the same for all the test scenarios are the general steps. In the following you see an example. These steps of the Scenario always happen, no matter the test.

1. Sales Officer selects a flight (route).
2. System displays multiple options for that specific flight (route).
3. Sales Officer selects "Configure static discount".

> This is the first Test Scenario. It has a quick name/description that shows what is being tested.

**Test Scenario 1: Everything works fine (monetary value inserted in €)**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and 5€.
6. System saves the change and adapts prices accordingly.

> This would be another Test Scenario. You just start from 4. again.

**Test Scenario 2: Everything works fine (monetary value inserted in %)**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and 15%.
6. System saves the change and adapts prices accordingly.

> Of course there are also Test Scenarios for when errors occur.

**Test Scenario 5: The monetary value inserted equals zero, less than zero or more than the actual price**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and -8€.
6. System gives an error message and asks for another input.

> Try to cover every sensible case possible! :)
