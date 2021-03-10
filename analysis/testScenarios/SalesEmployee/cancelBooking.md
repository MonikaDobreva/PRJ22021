### **Test Scenarios for Cancel Booking**

**General:**
1. Sales Employee chooses to manage a booking.
2. System displays list of bookings and shows filtering/search options.
3. Sales Employee selects desired booking and cancels it
4. System asks for confirmation.
5. Sales Employee confirms.
6. System cancels booking and shows confirmation message.

|Extensions     |3a. Sales Employee apply filter/search options.<br>&nbsp;&nbsp;&nbsp;&nbsp;1. System shows bookings matching with search options. Returns to step 3.<br>|
|Exceptions     |3a.1. System reports not matching booking found.<br> &nbsp;&nbsp;&nbsp;&nbsp;4a.1.1. Return to step 4a.<br><br>2. System reports no bookings available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|

## Test scenarios

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |If system has 1 or more bookings, listing them shouldn't report emptiness.|
|2.                    |If system has 0 bookings, listing them should report emptiness.|
|3.                    |When looking for a specific booking that is in the system, using filter/search options, must return desired booking or match results|
|4.                    |Use filter/search options for looking for a flight with no matching results must report emptiness.|
|4.                    |Cancelation of a booking must delete booking from the system.|

**Test Scenario 1: System shouldn't display booking if there aren't**
2. System tries to display available bookings and filtering/search options without bookings available.
3. System informs Sales Employee that there are no bookings available.
3.
...

Test Scenario 2:
1.
2.
3.
...

### **Test Scenarios for Enable Dynamic Price Reduction**

1. Sales Officer selects a flight (route).
2. System displays multiple options for that specific flight (route).
3. Sales Officer selects "Configure dynamic discount".


**Test Scenario 1: Everything works fine (monetary value inserted in €)**

4. System displays various types of dynamic discounts, including "daily sun hours at destination", "date and time of booking", or "time left before flight departure" and asks the Sales Officer to select the desired type and the amount or percentage that should be deducted.
5. Sales Officer selects "daily sun hours at destintaion" as discount type and 10€ as discount amount.
6. System saves the change and adapts prices accordingly.

## Use cases

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




