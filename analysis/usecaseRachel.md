## Use case

|   |   |
|---|---|
|Name:|Lookup flights|
|Actor:|Sales Employee|
|Description:|Sales employee looks up certain flights to see prices as well as discounts.|
|Pre-condition:|Flights need to have been added to the system|
|Scenario:|1.Actor selects the search option.<br>2. System promps for input in form of search kewords(time,date,location)<br>3.Actor inputs his search option in form of the key words<br>4.System shows list of all flights that match the keywords<br>5.Actor can select a flight<br>6.System displays all information about the selected flight|
|Result:|The sales employee can look at a certain flight with the prices shown|
|Extension:|-|
|Exceptions:|4a. No flights with such keywords<br>1.System displays message that no such flights are avaible. Use case neds here.|
|   |   |
|Name:|Create booking|
|Actor:|Sales employee|
|Description:|The sales employee creates a booking for a customer including adding ticket options.|
|Pre-condition:|flights need to have been added to the system|
|Scenario:|1. Actor <ins>Looks up a flight</ins><br>2.System gives the option to book this flight<br>3. Actor chooses the option to book this flight<br>4.System prompts the actor for Information(name, id number, birthday…) for the ticket<br>5. Actor inputs the private data from the customer|
|Result:|A customer has a booking  that was done thorugh a sales employee|
|Extension:|5a.Actor chooses to add bokking options<br>1.System gives the option to <ins>edit ticket options</ins>|
|Exceptions:||
|   |   |
|Name:|Edit ticket options|
|Actor:|Sales employee|
|Description:|Actor can add options for the customer|
|Pre-condition:|The passenger for who the actor changes options needs to have a bought ticket|
|Scenario:|1.Actor selects the booked flight<br>2.System gives the option to add booking options<br>3.System shows which booking otpions are availbe<br>4.Actor selects which options should be added.<br>5.Systems stores these to the ticket|
|Result:|Actor has altered the ticket options|
|Extension:||
|Exceptions:||
