## Test scenarios
| Use case | Input | Test scenario |
| --- | --- | --- |
| Register flight | Airports, Route, Plane,..., Flight Number "LH384" | Searching for a flight by the number "LH384" should return one search result. |
| Edit flight | Change flight number from "LH384" to "LH334" | Searching for flight "LH384" should no longer return a result, but "LH334" |
| Edit flight | Change departure airport of flight "LH1234" from "DUS" to "MUC" | A list of all flights leaving from DUS should no longer contain the flight "LH1234" |
| Delete flight | Deletion of flight "LH1234" | Searching for the flight "LH1234" should return zero results. |
| Log in | - | Managing flights or bookings should not throw an exception (UnauthorizedUserException). |


|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |If system has 1 or more bookings, listing them shouldn't report emptiness.|
|2.                    |If system has 0 bookings, listing them should report emptiness.|
|3.                    |When looking for a specific booking that is in the system, using filter/search options, must return desired booking or match results|
|4.                    |Use filter/search options for looking for a flight with no matching results must report emptiness.|
|5.                    |Cancelation of a booking must delete booking from the system.|

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |Accessing the Management Dashboard must show available KPI (KPIs have been defined previously).|
|2.                    |Accessing Management Dashboard with no KPIs should return null - report that there are no KPIs in the system.|
|3.                    |Selected KPIs must display/show desired information/data.|
|4.                    |Select KPI without data to make calculations/statistics must report no data available.|

|   |   |
|---|---|
|**Test scenarios**|**Enable static price reduction**|
|Actor:|**Sales Officer**|
|1.|If Sales Officer inputs start date that's after the flight departure, System reports error|
|2.|If Sales Officer inputs more than 100% or less than 1% as a price reduction, System reports error|
|3.|If Sales Officer inputs a bigger amount than the original flight price as a discount value, System reports error|
|4.|If Sales Officer inputs a negative value as the discount value, System reports error|
|   |   |
|   |   |
|**Test scenarios**|**Enable dynamic price reduction**|
|Actor:|**Sales Officer**|
|1.|If Sales Officer inputs start date that's after the flight departure, System reports error|
|2.|If Sales Officer inputs more than 100% or less than 1% as a price reduction, System reports error|
|3.|If Sales Officer inputs a bigger amount than the original flight price as a discount value, System reports error|
|4.|If Sales Officer inputs a negative value as the discount value, System reports error|
|5.|If Sales Officer does not input one of the listed discount types, System reports error|
