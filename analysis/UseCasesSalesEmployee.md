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
|**Description**|The Sales Employee creates a booking for a customer including adding ticket options.|
|**Pre-condition**|At least one flight needs to be registered and have tickets available.|
|**Scenario**|1. Sales Employee <ins>looks up a flight</ins><br>2. System gives the opportunity to book one or more tickets for this flight<br>3. Sales Employee chooses the option to book one or more tickets for this flight<br>4. System asks the Sales Employee for information such as name, gender, birthday, e-mail, phone number for the customer and additionally asks for the same information plus the passport number of every passenger<br>5. Sales Employee inputs all the data<br>6. System saves the information, reserves the seat(s) and issues the ticket(s)|
|**Result**|The Sales Employee has successfully created a booking for a customer, that includes either one or multiple tickets for a specific flight.|
|**Extensions**|5a. Sales Employee chooses to also input options, such as additional baggage or a different meal<br>1.Sales Employee chooses at least one of the options.<br>2. System saves the information, and replaces the default values. Returns to step 6.|
|**Exceptions**|6a. Sales Employee did not input all the required information. <br>6.1. System asks the Sales Employee to fill the remaining fields. Returns to step 5.|
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
