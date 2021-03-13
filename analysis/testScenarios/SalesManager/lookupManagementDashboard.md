### **Test Scenarios for Lookup Management Dashboard**

To read Use Case click [**here**]( ../../UseCasesSalesManager.md )

**General:**
1. Sales Manager chooses to use the Management Dashboard.

**Test Scenario 1: System with KPIs {"Monthly Sales", "Sales per Employee"} should display them**

2. System shows KPIs "Monthly Sales" and "Sales per Employee".

**Test Scenario 2: System without KPIs shouldn't display them**

2. System tries to show list of available KPIs.
3. System informs Sales Manager that there are no KPIs available 

**Test Scenario 3: System with KPIs {"Monthly Sales", "Sales per Employee"} should display "Monthly Sales" when selected**

2. System shows KPIs "Monthly Sales" and "Sales per Employee" and asks the Sales Manager to select KPIs to show.
3. Sales Manager select "Monthly Sales" to lookup into the Management Dashboard and confirms.
4. System displays data for "Monthly Sales" KPI.

**Test Scenario 4: System with KPIs {"Monthly Sales", "Sales per Employee"} and no data to display for "Monthly sales",should display "Monthly Sales" when selected and report no data available**

2. System shows KPIs "Monthly Sales" and "Sales per Employee" and asks the Sales Manager to select KPIs to show.
3. Sales Manager select "Monthly Sales" to lookup into the Management Dashboard and confirms.
4. System displays "Monthly Sales" and informs Sales Manager that no data is available to display.

