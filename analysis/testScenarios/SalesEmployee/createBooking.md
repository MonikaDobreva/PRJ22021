### **Test Scenarios for Create booking**

**General:**

1. Sales Employee <ins>looks up a flight</ins>.
2. System gives an option to book this flight.
3. Sales Employee chooses the option to book this flight.
4. System prompts the Sales Employee for information(name, id number, birthday…) for the ticket.


**Test Scenario 1: Everything works fine (input information is acceptable)**

5. Sales Employee inputs John Smith, 346058078 and 03/04/1986.
6. System saves the booking.


**Test Scenario 2: Everything works fine with extension**

5. Sales Employee inputs Jane Peters, 355058078 and 06/08/1997.
6. Sales Employee chooses to add booking options.
7. System gives the option to add luggage, add handluggage and food option.
8. Sales Employee selects add handluggage.
9. System saves the booking.


**Test Scenario 3: Input information incomplete (missing id number)**

4. Sales Employee inputs John Smith and 09/12/1975.
5. System does not accept and returns to 5.


**Test Scenario 4: The birthdate inserted is in the future**

4. Sales Employee inputs John Smith, 566053978 and 09/12/2075.
5. System does not accept and returns to 5.


**Test Scenario 5: The inserted birthdate is before 1900**

4. Sales Employee inputs John Smith, 566053978 and 01/11/1775.
5. System does not accept and returns to 5.


**Test Scenario 6: Input information incomplete (missing birthdate)**

4. Sales Employee inputs John Smith and 566053978.
5. System does not accept and returns to 5.


**Test Scenario 7: Input information incomplete (missing name)**

4. Sales Employee inputs 566053978 and 01/11/1775.
5. System does not accept and returns to 5.
