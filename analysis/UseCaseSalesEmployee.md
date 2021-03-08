## Use cases sales employee

|Use case Number|x|
|---------------|---|
|Name           |Cancel a booking|
|Actor          |Sales Employee|
|Description    |A Sales Employee wants to cancel a booking|
|Pre-condition  |None|
|Scenario       |1. Sales Employee chooses to manage a booking.<br>2. System displays list of bookings and shows filtering/search options.<br>3. Sales Employee selects desired booking and cancels it.<br>5. System asks for confirmation.<br>6. Sales Employee confirms.<br>7. System cancels booking and shows confirmation message.|
|Result         |Chosen booking is cancelled.|
|Extensions     |3a. Sales Employee apply filter/search options.<br>&nbsp;&nbsp;&nbsp;&nbsp;1. System shows bookings matching with search options. Returns to step 3.<br>|
|Exceptions     |3a.1. System reports not matching booking found.<br> &nbsp;&nbsp;&nbsp;&nbsp;4a.1.1. Return to step 4a.<br><br>2. System reports no bookings available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|

|Use case Number|x|
|---|---|
|Name:|Lookup flights|
|Actor:|Sales Employee|
|Description:|Sales employee looks up certain flights to see prices as well as discounts.|
|Pre-condition:|Flights need to have been added to the system|
|Scenario:|1.Actor selects the search option.<br>2. System promps for input in form of search kewords(time,date,location)<br>3.Actor inputs his search option in form of the key words<br>4.System shows list of all flights that match the keywords<br>5.Actor can select a flight<br>6.System displays all information about the selected flight|
|Result:|The sales employee can look at a certain flight with the prices shown|
|Extension:|-|
|Exceptions:|4a. No flights with such keywords<br>1.System displays message that no such flights are avaible. Use case neds here.|
  
|Use case Number|x|
|---|---|
|Name:|Create booking|
|Actor:|Sales employee|
|Description:|The sales employee creates a booking for a customer including adding ticket options.|
|Pre-condition:|flights need to have been added to the system and the flight needs to have at least one availbe ticket.|
|Scenario:|1. Actor <ins>Looks up a flight</ins><br>2.System gives the option to book this flight<br>3. Actor chooses the option to book this flight<br>4.System prompts the actor for Information(name, id number, birthday…) for the ticket<br>5. Actor inputs the private data from the customer<br>6. System safes it|
|Result:|A customer has a booking  that was done thorugh a sales employee|
|Extension:|5a.Actor chooses to add booking options<br>1.System gives the option to add Luggage, add handluggage and food options.<br>2.Actor selects which options should be added.<br>returns to step 6.|
|Exceptions:|6a. User has not inputed all options. <br>6.1.System does not accept and returns to 5.|
  
|Use case Number|x|
|---|---|
|Name:|Edit ticket options|
|Actor:|Sales employee|
|Description:|Actor can add options for the customer|
|Pre-condition:|The passenger for who the actor changes options needs to have a bought ticket|
|Scenario:|1.Actor selects the booked flight<br>2.System gives the option to add booking options<br>3.System shows which booking otpions are availbe<br>4.Actor selects which options should be added.<br>5.Systems stores these to the ticket|
|Result:|Actor has altered the ticket options|
|Extension:||
|Exceptions:||
