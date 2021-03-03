## Use cases

|Use case Number|x|
|---------------|---|
|Name           |Cancel a booking|
|Actor          |Sales Employee|
|Description    |A Sales Employee wants to cancel a booking|
|Pre-condition  |None|
|Scenario       |1. Sales Employee chooses to cancel a booking.<br>2. System displays list of bookings.<br>3. System also shows filtering/search options.<br>4. Sales Employee selects desired booking and cancel it.<br>5. System asks to confirm cancelation.<br>6. Sales Employee confirms.<br>7. System cancels booking and shows confirmation message.|
|Result         |Chosen booking is cancelled.|
|Extensions     |4a. Sales Employee apply filter/search options.<br>&nbsp;&nbsp;&nbsp;&nbsp;1. System shows bookings matching with search options. Returns to step 4.<br>|
|Exceptions     |4a.1. System reports not matching booking found.<br> &nbsp;&nbsp;&nbsp;&nbsp;4a.1.1. Return to step 4a.<br><br>2. System reports no bookings available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|

|Use case Number|x|
|---------------|---|
|Name           |Lookup Management Dashboard|
|Actor          |Sales Manager|
|Description    |Sales Manager uses Management Dashboard to check KPIs|
|Pre-condition  |None|
|Scenario       |1. Sales Manager chooses to use the Management Dashboard<br>2. System shows list of available KPIs<br>3. System asks the user to select KPIs to show<br>4. Sales Manager select desired KPIs to lookup into the Management Dashboard and confirms<br>5. System shows the selected KPIs in a GUI |
|Result         |Sales Manager is able to check current status of the company by use of KPIs|
|Extensions     |None|
|Exception      |2. System reports no KPIs available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|

## Test scenarios

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |Creating/Having 1 or more bookings in the system shouldn't return null when listing the bookings |
|2.                    |Looking for a booking that is not in the list, using filter/search options, or no bookings in the system must return null value - report no matching or not available bookings|
|3.                    |Using filter/search options displays the bookings that match with the search|
|4.                    |Delete booking from the system makes the booking not to appear anymore in the booking list|

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |Accessing the Management Dashboard must show available KPI (Some KPI have been defined previously)|
|2.                    |Accessing Management Dashboard with no KPIs should return null - report that no KPIs in the system |
|3.                    |Selected KPIs must display/show desired information/data|
|4.                    |Select KPI without data to make calculations must report no data available|
