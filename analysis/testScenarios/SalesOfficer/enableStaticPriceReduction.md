**Test Scenarios for Enable Static Price Reduction**

General:

1. Sales Officer selects a flight (route).
2. System displays multiple options for that specific flight (route).
3. Sales Officer selects "Configure static discount".


**Test Scenario 1: Everything works fine (monetary value inserted in €)**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and 5€.
6. System saves the change and adapts prices accordingly.


**Test Scenario 2: Everything works fine (monetary value inserted in %)**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and 15%.
6. System saves the change and adapts prices accordingly.


Test Scenario 3: **There is already a dynamic discount for this specific flight**

4. System finds an existing dynamic discount for this specific flight (route).
5. System informs the Sales Officer that they have to delete the dynamic discount before they can enable a static discount.


Test Scenario 4: **The date inserted is not in the future, but in the past**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/1950 - 14/14/1950 and 5€.
6. System gives an error message and asks for another input.


Test Scenario 5: **The monetary value inserted equals zero, less than zero or more than the actual price**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and -8€.
6. System gives an error message and asks for another input.


Test Scenario 6: **The monetary value, expressed as a percentage exceeds 99%, or is below 1%**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and 450%.
6. System gives an error message and asks for another input.


Test Scenario 7: **Sales Officer does not input a date**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 5€ and does not input a date.
6. System gives an error messsage and asks for a date.


Test Scenario 8: **Sales Officer does not input a monetary value**

4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.
5. Sales Officer inputs 01/12/2021 - 14/14/2021 and no monetary value.
6. System gives an error messsage and asks for a monetary value.
