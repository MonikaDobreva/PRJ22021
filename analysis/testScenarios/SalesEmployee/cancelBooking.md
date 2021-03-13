### **Test Scenarios for Cancel Booking**

**General:**
1. Sales Employee chooses to manage a booking.

**Test Scenario 1: System shouldn't display booking if there aren't**

2. System tries to display available bookings and filtering/search options without bookings available.
3. System informs Sales Employee that there are no bookings available.

**Test Scenario 2: System with several bookings must be displayed**

2. System displays list of bookings and filtering/search options.
3. System displays the bookings inside of it.

**Test Scenario 3: System with bookings {("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...), ("103", "2021-03-01", "Anna Frank", ...), ("104", "2021-03-10", "Albert Einstein", ...)} must display ("101", "2021-01-01", "Albert Einstein", ...) and ("104", "2021-03-10", "Albert Einstein", ...) when searching for name "Albert Einstein"**

2. System displays list of bookings and filtering/search options.
3. Sales Employee uses searching options to look for bookings containing the name "Albert Einstein".
4. System displays bookings ("101", "2021-01-01", "Albert Einstein", ...) and ("104", "2021-03-10", "Albert Einstein", ...)

**Test Scenario 4: System with bookings {("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...), ("103", "2021-03-01", "Anna Frank", ...)} must display ("101", "2021-01-01", "Albert Einstein", ...) when searching for date "2021-01-01"**

2. System displays list of bookings and filtering/search options.
3. Sales Employee uses searching options to look for bookings containing the date "2021-01-01".
4. System displays booking ("101", "2021-01-01", "Albert Einstein", ...)

**Test Scenario 5: System with bookings {("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...), ("103", "2021-03-01", "Anna Frank", ...)} must display ("102", "2021-02-01", "Nikolav Tesla ", ...) when searching for order number "102"**

2. System displays list of bookings and filtering/search options.
3. Sales Employee uses searching options to look for bookings containing order number "102".
4. System displays booking details for booking ("102", "2021-02-01", "Nikolav Tesla ", ...)

**Test Scenario 6: System with bookings {("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...), ("103", "2021-03-01", "Anna Frank", ...)} must not display any booking when search parameters doesn't match any booking**

2. System displays list of bookings and filtering/search options.
3. Sales Employee uses searching options to look for bookings containing order number "105".
4. System informs Sales Employye that there is no booking matching with searching options.

**Test Scenario 7: System with {("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...), ("103", "2021-03-01", "Anna Frank", ...)} must not display booking ("101", "2021-01-01", "Albert Einstein", ...) when it has been cancelled**

2. System displays bookings ("101", "2021-01-01", "Albert Einstein", ...), ("102", "2021-02-01", "Nikolav Tesla ", ...) and ("103", "2021-03-01", "Anna Frank", ...) and filtering/search options.
3. Sales Employee selects booking ("101", "2021-01-01", "Albert Einstein", ...) and cancels it.
4. System asks for confirmation.
5. Sales Employee confirms.
6. System cancels booking and shows confirmation message.
7. Sales Employee chooses to manage more bookings.
8. System displays bookings ("102", "2021-02-01", "Nikolav Tesla ", ...) and ("103", "2021-03-01", "Anna Frank", ...) and filtering/search options.

**To read the Use Case** Click [**here**]( ../../UseCasesSalesEmployee.md )
