## Use cases


|   |   |
|---|---|
|**Name:**|**Enable static price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to configure and enable a temporary static price reduction for a specific flight or a flight route.|
|**Pre-condition:**|At least one flight (route) has already been registered in the system.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Configure static discount".<br> 4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.<br> 5. Sales Officer inputs the desired dates and the discount amount and decides to confirm the change.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales Officer has successfully configured and enabled a temporary static price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|4. System finds an existing dynamic discount for this specific flight (route).<br> 4.1 System informs the Sales Officer that they have to disable the dynamic discount before they can enable a static discount.<br> 4.2 Use case ends here.<br> 6. System does not receive an input for both the desired dates and the discount amount.<br> 6.1 Returns to Step 5. |
|   |   |
|---|---|
|**Name:**|**Disable static price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to manually disable a temporary static price reduction for a specific flight or a flight route.
|**Pre-condition:**|-At least one flight (route) has already been registered in the system.<br> -At least one flight (route) currently has a static discount assigned to it.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Disable static discount".<br> 4. System displays the discount amount and the end date of the discount and asks the Sales Officer if they are certain that they want to disable this discount.<br> 5. Sales Officer decides to manually disable the displayed discount.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales officer has sucessfully disabled a temporary static price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|5. Sales Officer decides not to disable the displayed discount.<br> 5.1 Use case ends here. |
|   |   |
|---|---|
|**Name:**|**Enable dynamic price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to configure and enable a temporary dynamic price reduction for a specific flight or a flight route.|
|**Pre-condition:**|At least one flight (route) has already been registered in the system.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Configure dynamic discount".<br> 4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", and others and asks the Sales Officer to select the desired type.<br> 5. Sales Officer selects the type of discount they want to apply and decides to confirm the change.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales Officer has sucessfully configured and enabled a temporary dynamic price reduction for a specific flight or a flight route.|
|**Extensions:**|5a. Sales Officer also makes use of the option to define a start and end date for the dynamic discount. Returns to step 6.|
|**Exceptions:**|4. System finds an existing static discount for this specific flight (route).<br> 4.1 System informs the Sales Officer that they have to disable the static discount before they can enable a dynamic discount.<br> 4.2 Use case ends here.<br> 6. System does not receive an input for the type of dynamic discount.<br> 6.1 Returns to Step 5. |
|   |   |
|---|---|
|**Name:**|**Disable dynamic price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to manually disable a temporary dynamic price reduction for a specific flight or a flight route.|
|**Pre-condition:**|-At least one flight (route) has already been registered in the system.<br> -At least one flight (route) currently has a dynamic discount assigned to it.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Disable dynamic discount".<br> 4. System displays the discount type and the end date of the discount (if given) and asks the Sales Officer if they are certain that they want to disable this discount.<br> 5. Sales Officer decides to manually disable the displayed discount.<br> 6. System saves the change and adapts prices accordingly.  |
|**Result:**|A Sales Officer has successfully disabled a temporary dynamic price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|5. Sales Officer decides not to disable the displayed discount.<br> 5.1 Use case ends here. |
|   |   |
|   |   |
|---|---|
|**Name:**|**Template**|
|**Actor:**|Sales Officer|
|**Description:**|-|
|**Pre-condition:**|-|
|**Scenario:**|-|
|**Result:**|-|
|**Extensions:**|-|
|**Exceptions:**|-|
