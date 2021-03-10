|Use case Number|x|
|---|---|
| **Name** | **Register flight** |
| **Actor** | Sales Officer |
| **Description** | A Sales Officer creates a new flight with the help of the application |
| **Pre-condition** | The Sales Officer needs to have an account for the app |
| **Scenario** | 1. The Sales Officer opens the application </br> 2. The Sales Officer chooses the option to register a new flight </br> 3. The system offers the Sales Officer to enter the necessary information about the flight </br> 4. The Sales Officer inputs all the data they can provide </br> 5. The Sales Officer submits the process </br> 6. The system adds the new flight to the list |
| **Result** | The Sales Officer has successfully created a new flight for the airline company |
| **Exceptions** | 6.1 The Sales Officer has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 4 </br> 6.2 The Sales Officer has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 4 |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/registerFlight.md)|

|Use case Number|x|
|---|---|
| **Name** | **Edit flight** |
| **Actor** | Sales Officer |
| **Description** | A Sales Officer edits an existing flight with the help of the application |
| **Pre-condition** | The Sales Officer must have an account for the app and the flight to be edited must exists |
| **Scenario** |1. The Sales Officer opens the application </br>2. The Sales Officer views all flights </br>3. The Sales Officer chooses the option to edit a flight </br>4. The system offers the Sales Officer to enter the necessary information about the flight </br>5. The Sales Officer adds/changes/deletes the needed information </br>6. The Sales Officer submits the process </br>7. The system displays the changed flight inside of the list </br> |
| **Result** | The Sales Officer has successfully edited an existing flight for the airline company |
| **Exceptions** | 6.1 The Sales Officer has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 5 </br> 6.2 The Sales Officer has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 5 |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/editFlight.md)|

|Use case Number|x|
|---|---|
| **Name** | **Delete flight** |
| **Actor** | Sales Officer |
| **Description** | A Sales Officer deletes an existing flight with the help of the application |
| **Pre-condition** | The Sales Officer needs to have an account for the app and the flight to be deleted must exists |
| **Scenario** |1. The Sales Officer opens the application </br>2. The Sales Officer views all flights </br>3. The Sales Officer chooses the option to delete a flight </br>4. The system asks for confirmation </br>5. The Sales Officer submits with &quot;Yes&quot; </br>6. The system removes the flight </br>|
| **Result** | The Sales Officer has successfully deleted an existing flight for the airline company |
| **Exceptions** | - |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/deleteFlight.md)|

|Use case Number|x|
|---|---|
|**Name:**|**Enable static price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to configure and enable a temporary static price reduction for a specific flight or a flight route.|
|**Pre-condition:**|At least one flight (route) has already been registered in the system.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Configure static discount".<br> 4. System asks for the start and end date of the discount, as well as the amount or percentage of money that should be deducted from the original price value.<br> 5. Sales Officer inputs the desired dates and the discount amount and decides to confirm the change.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales Officer has successfully configured and enabled a temporary static price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|4. System finds an existing dynamic discount for this specific flight (route).<br> 4.1 System informs the Sales Officer that they have to delete the dynamic discount before they can enable a static discount.<br> 4.2 Use case ends here.<br> 6. System does not receive an input for both the desired dates and the discount amount.<br> 6.1 Returns to Step 5. |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/enableStaticPriceReduction.md)|

|Use case Number|x|
|---|---|
|**Name:**|**Delete static price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to manually delete a temporary static price reduction for a specific flight or a flight route.
|**Pre-condition:**|-At least one flight (route) has already been registered in the system.<br> -At least one flight (route) currently has a static discount assigned to it.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Delete static discount".<br> 4. System displays the discount amount and the end date of the discount and asks the Sales Officer if they are certain that they want to delete this discount.<br> 5. Sales Officer decides to manually delete the displayed discount.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales officer has sucessfully deleted a temporary static price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|5. Sales Officer decides not to delete the displayed discount.<br> 5.1 Use case ends here. |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/deleteStaticPriceReduction.md)|

|Use case Number|x|
|---|---|
|**Name:**|**Enable dynamic price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to configure and enable a temporary dynamic price reduction for a specific flight or a flight route.|
|**Pre-condition:**|At least one flight (route) has already been registered in the system.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Configure dynamic discount".<br> 4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.<br> 5. Sales Officer selects the type of discount they want to apply and the according amount or percentage and decides to confirm the change.<br> 6. System saves the change and adapts prices accordingly. |
|**Result:**|A Sales Officer has sucessfully configured and enabled a temporary dynamic price reduction for a specific flight or a flight route.|
|**Extensions:**|5a. Sales Officer also makes use of the option to define a start and end date for the dynamic discount. Returns to step 6.|
|**Exceptions:**|4. System finds an existing static discount for this specific flight (route).<br> 4.1 System informs the Sales Officer that they have to delete the static discount before they can enable a dynamic discount.<br> 4.2 Use case ends here.<br> 6. System does not receive an input for the type of dynamic discount.<br> 6.1 Returns to Step 5. |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/enableDynamicPriceReduction.md)|

|Use case Number|x|
|---|---|
|**Name:**|**Delete dynamic price reduction**|
|**Actor:**|Sales Officer|
|**Description:**|A Sales Officer intends to manually delete a temporary dynamic price reduction for a specific flight or a flight route.|
|**Pre-condition:**|-At least one flight (route) has already been registered in the system.<br> -At least one flight (route) currently has a dynamic discount assigned to it.|
|**Scenario:**|1. Sales Officer selects a flight (route).<br> 2. System displays multiple options for that specific flight (route).<br> 3. Sales Officer selects "Delete dynamic discount".<br> 4. System displays the discount type and the end date of the discount (if given) and asks the Sales Officer if they are certain that they want to delete this discount.<br> 5. Sales Officer decides to manually delete the displayed discount.<br> 6. System saves the change and adapts prices accordingly.  |
|**Result:**|A Sales Officer has successfully deleted a temporary dynamic price reduction for a specific flight or a flight route.|
|**Extensions:**|-|
|**Exceptions:**|5. Sales Officer decides not to delete the displayed discount.<br> 5.1 Use case ends here. |
|**Test Scenarios**|Click [**here**](testScenarios/SalesOfficer/deleteDynamicPriceReduction.md)|

