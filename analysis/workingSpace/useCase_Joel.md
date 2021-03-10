## Use cases

|Use case Number|x|
|---------------|---|
|Name           |Cancel a booking|
|Actor          |Sales Employee|
|Description    |A Sales Employee wants to cancel a booking|
|Pre-condition  |None|
|Scenario       |1. Sales Employee chooses to manage a booking.<br>2. System displays list of bookings and shows filtering/search options.<br>3. Sales Employee selects desired booking and cancels it.<br>4. System asks for confirmation.<br>5. Sales Employee confirms.<br>6. System cancels booking and shows confirmation message.|
|Result         |Chosen booking is cancelled.|
|Extensions     |3a. Sales Employee apply filter/search options.<br>&nbsp;&nbsp;&nbsp;&nbsp;1. System shows bookings matching with search options. Returns to step 3.<br>|
|Exceptions     |3a.1. System reports not matching booking found.<br> &nbsp;&nbsp;&nbsp;&nbsp;4a.1.1. Return to step 3a.<br><br>2. System reports no bookings available.<br>&nbsp;&nbsp;&nbsp;&nbsp;2.1. Use case ends here.|

|Use case Number|x|
|---------------|---|
|Name           |Lookup Management Dashboard|
|Actor          |Sales Manager|
|Description    |Sales Manager uses Management Dashboard to check KPIs|
|Pre-condition  |KPIs have been already created and defined in the system|
|Scenario       |1. Sales Manager chooses to use the Management Dashboard.<br>2. System shows list of available KPIs and asks the Sales Manager to select KPIs to show.<br>4. Sales Manager select desired KPIs to lookup into the Management Dashboard and confirms.<br>5. System displays the selected KPIs.|
|Result         |Sales Manager is able to check current status of the company by use of KPIs|
|Extensions     |None|
|Exception      |None|

## Test scenarios

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |If system has 1 or more bookings, listing them shouldn't report emptiness.|
|2.                    |If system has 0 bookings, listing them should report emptiness.|
|3.                    |When looking for a specific booking that is in the system, using filter/search options, must return desired booking or match results|
|4.                    |Use filter/search options for looking for a flight with no matching results must report emptiness.|
|4.                    |Cancelation of a booking must delete booking from the system.|

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |Accessing the Management Dashboard must show available KPI (KPIs have been defined previously).|
|2.                    |Accessing Management Dashboard with no KPIs should return null - report that there are no KPIs in the system.|
|3.                    |Selected KPIs must display/show desired information/data.|
|4.                    |Select KPI without data to make calculations/statistics must report no data available.|
