**Test Scenarios for Lookup Management Dashboard**

Test Scenario 1:
1.
2.
3.
...

Test Scenario 2:
1.
2.
3.
...



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

|Test for<br>Scenario x|Description|
|----------------------|---|
|1.                    |Accessing the Management Dashboard must show available KPI (KPIs have been defined previously).|
|2.                    |Accessing Management Dashboard with no KPIs should return null - report that there are no KPIs in the system.|
|3.                    |Selected KPIs must display/show desired information/data.|
|4.                    |Select KPI without data to make calculations/statistics must report no data available.|
