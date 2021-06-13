### **Test Scenarios for Lookup Management Dashboard**

To read Use Case click [**here**]( ../../UseCasesSalesManager.md )

_Since the use case of looking up the management dashboard relies on displaying the right key performance indicators, a tested business logic responsible for this data is considered as the respective scenarios_

**General:**
1. Sales Manager chooses to use the Management Dashboard.

**Test Scenario 1: M. Dashboard with KPIs {total flights, total booking, total revenue, popular meal, ...} should display them**

2. System shows KPIs.

**Test Scenario 2: M. Dashboard without KPIs shouldn't display them**

2. System tries to show list of available KPIs.
3. System informs Sales Manager that there are no KPIs available 

**Test Scenario 3: M. Dashboard should display flight KPIs only when a respective flight is selected**

2. System shows option to chose a flight and/or a booking and asks the Sales Manager to select KPIs to show.
3. Sales Manager select a flight to lookup into the Management Dashboard and confirms.
4. System displays data for flight KPI.

**Test Scenario 4: M. Dashboard should display booking KPIs only when a respective flight and booking is selected**

2. System shows option to chose a flight and/or a booking and asks the Sales Manager to select KPIs to show.
3. Sales Manager select a flight and a respective booking to lookup into the Management Dashboard and confirms.
4. System displays data for flight and booking KPI.

**Test Scenario 4: System with flight but no connected booking only shows KPIs for a flight**

2. System shows option to chose a flight and/or a booking and asks the Sales Manager to select KPIs to show.
3. Sales Manager selects a flight from a selection.
4. Sales Manager clicks the a second field to select a booking.
5. System only displays the flight KPIs and informs Sales Manager that no booking data is available to display.

