## Use cases sales employee

|Use case Number|x|
|---------------|---|
|**Name**           |Cancel booking|
|**Actor**          |Sales Employee|
|**Description**    |A Sales Employee wants to cancel a booking|
|**Pre-condition**  |-|
|**Scenario**       |1. Sales Employee chooses to manage a booking.<br>2. System displays list of bookings and shows filtering/search options.<br>3. Sales Employee selects desired booking and cancels it.<br>4. System asks for confirmation.<br>5. Sales Employee confirms.<br>6. System cancels booking and shows confirmation message.|
|**Result**         |Chosen booking is cancelled.|
|**Extensions**     |3a. Sales Employee apply filter/search options.<br>&nbsp;&nbsp;&nbsp;&nbsp;1. System shows bookings matching with search options. Returns to step 3.<br>|
|**Exceptions**     |3a.1. System reports not matching booking found.<br> &nbsp;&nbsp;&nbsp;&nbsp;3a.1.1. Return to step 3a.<br><br>2. System reports no bookings available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|
|**Test Scenarios**|Click [**here**](testScenarios/SalesEmployee/cancelBooking.md)|

|Use case Number|x|
|---|---|
|**Name**|Lookup flights|
|**Actor**|Sales Employee, Sales Manager|
|**Description**|User looks up certain flights to see prices as well as discounts.|
|**Pre-condition**|Flights need to have been added to the system|
|**Scenario**|1. User selects the search option.<br>2. System prompts for input in form of search keywords(time,date,location)<br>3. User inputs his search option in form of the key words<br>4. System shows list of all flights that match the keywords<br>5. User can select a flight<br>6. System displays all information about the selected flight|
|**Result**|The User can look at a certain flight with the prices shown|
|**Extensions**|-|
|**Exceptions**|4a. No flights with such keywords<br>1. System displays message that no such flights are available. Use case ends here.|
|**Test Scenarios**|Click [**here**](testScenarios/SalesEmployee/lookupFlights.md)|
  
|Use case Number|x|
|---|---|
|**Name**|Create booking|
|**Actor**|Sales Employee|
|**Description**|The sales Employee creates a booking for a customer including adding ticket options.|
|**Pre-condition**|flights need to have been added to the system and the flight needs to have at least one available ticket.|
|**Scenario**|1. Sales Employee <ins>looks up a flight</ins><br>2. System gives the option to book this flight<br>3. Sales Employee chooses the option to book this flight<br>4.System prompts the actor for Information(first name, last name, id number, birthday, email, phone number) for the ticket<br>5. Actor inputs the private data from the customer<br>6. System safes it|
|**Result**|A customer has a booking  that was done through a Sales Employee|
|**Extensions**|5a. Sales Employee chooses to add booking options<br>1.System gives the option to add Luggage, add handluggage and food options.<br>2. Sales Employee selects which options should be added.<br>returns to step 6.|
|**Exceptions**|6a. User has not inputted all options. <br>6.1. System does not accept and returns to 5.|
|**Test Scenarios**|Click [**here**](testScenarios/SalesEmployee/createBooking.md)|
  
|Use case Number|x|
|---|---|
|**Name**|Edit ticket options|
|**Actor**|Sales Employee|
|**Description**|Sales Employee can add options for the customer|
|**Pre-condition**|The passenger for who the actor changes options needs to have a bought ticket|
|**Scenario**|1. Sales Employee selects the booked flight<br>2. System gives the option to add booking options<br>3. System shows which booking options are available<br>4. Sales Employee selects which options should be added.<br>5. Systems stores these to the ticket|
|**Result**|Sales Employee has altered the ticket options|
|**Extensions**|-|
|**Exceptions**|-|
|**Test Scenarios**|Click [**here**](testScenarios/SalesEmployee/editTicketOptions.md)|
