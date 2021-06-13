### **Test Scenarios for Create booking**

To read Use Case click [**here**]( ../../useCase/UseCasesSalesEmployee.md )

**General:**

1. Sales Employee selects 'Create booking'
2. System gives the opportunity to select a flight and to book one or more tickets for this flight
3. Sales Employee selects a flight
4. System asks the Sales Employee for information such as name, gender, e-mail, birthday for the customer and additionally asks for the same information plus the passport number of every passenger
5. Sales Employee inputs all the data
6. System also asks for a seat type (e.g. Economy) and then displays all seats that are still available for this seat type
7. Sales Employee selects a seat type and a seat
8. System saves the information, reserves the seat(s) and issues the ticket(s)


**Test Scenario 1: Name contains a digit**

5. Sales Employee inputs 'Carl0s' as a name.
6. System does not accept, prints 'Name should not contain digits' and returns to 5.


**Test Scenario 2: Name is not at least two letters long**

5. Sales Employee inputs 'X' as a name.
6. System does not accept, prints 'Name should be at least two letters long' and returns to 5.


**Test Scenario 3: Email address contains a space**

5. Sales Employee inputs 'email address@abc.de' as an email.
6. System does not accept, prints 'Email address may not contain spaces' and returns to 5.


**Test Scenario 4: Email address does not contain the at-symbol**

5. Sales Employee inputs 'email.abc.de' as an email.
6. System does not accept, prints 'Email address needs to contain the @-symbol' and returns to 5.


**Test Scenario 5: Email address does not contain a dot**

5. Sales Employee inputs 'email,email@abcde' as an email.
6. System does not accept, prints 'Email address needs to contain a dot' and returns to 5.


**Test Scenario 6: Birthday value is not a valid date**

5. Sales Employee inputs 'Carsten' as a birth date.
6. System does not accept, prints 'Carsten is not a valid date / should not be empty' and returns to 5.


**Test Scenario 7: Birthday value is today or in the future**

5. Sales Employee inputs '2099-12-12' as a birth date.
6. System does not accept, prints 'Birthday may not be today or in the future' and returns to 5.


**Test Scenario 8: Birthday is before the 21th century**

5. Sales Employee inputs '1888-12-12' as a birth date.
6. System does not accept, prints 'Birthday may not be before the 21th century' and returns to 5.


**Test Scenario 9: Passport number is not 9 digits long**

5. Sales Employee inputs 'PPN45678910' as a passport number.
6. System does not accept, prints 'Passport number needs to be 9 digits long' and returns to 5.


**Test Scenario 10: Passport number contains a non-letter/number character**

5. Sales Employee inputs 'PX1Z3EJ?E' as a passport number.
6. System does not accept, prints 'Passport number may only contain A-Z and 0-9' and returns to 5.







