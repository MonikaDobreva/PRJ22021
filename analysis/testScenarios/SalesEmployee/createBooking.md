### **Test Scenarios for Create booking**

**General:**

1. Sales Employee <ins>looks up a flight</ins>.
2. System gives an option to book this flight.
3. Sales Employee chooses the option to book this flight.
4. System prompts the Sales Employee for information(first name, last name, id number, birthday, email, phone number) for the ticket.


**Test Scenario 1: Everything works fine (input information is acceptable)**

5. Sales Employee inputs John, Smith, 346058078, 03/04/1986, johnsmth@gmail.com and +359887463390.
6. System saves the booking.


**Test Scenario 2: Everything works fine with extension**

5. Sales Employee inputs Jane, Peters, 355058078, 06/08/1997, janep@hotmail.com and +31657895435.
6. Sales Employee chooses to add booking options.
7. System gives the option to add luggage, add handluggage and food option.
8. Sales Employee selects add handluggage.
9. System saves the booking.


**Test Scenario 3: Input information incomplete (missing id number)**

5. Sales Employee inputs John, Smith, 09/12/1975, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 4: The birthdate inserted is in the future**

5. Sales Employee inputs John, Smith, 566053978, 09/12/2075, johnsmith@hotmail.com and +31658234987.
6. System does not accept and returns to 5.


**Test Scenario 5: The inserted birthdate is before 1900**

5. Sales Employee inputs John, Smith, 566053978, 01/11/1775, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 6: Input information incomplete (missing birthdate)**

5. Sales Employee inputs John, Smith, 566053978, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 7: Input information incomplete (missing first name)**

5. Sales Employee inputs John, 566053978, 01/11/1775, 06/08/1997, john@gmail.com and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 8: Input information incomplete (missing email)**

5. Sales Employee inputs Jane, Peters, 355058078, 06/08/1997 and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 9: Input information incomplete (missing phone number)**

5. Sales Employee inputs John, Smith, 346058078, 03/04/1986 and johnsmth@gmail.com.
6. System does not accept and returns to step 5.


**Test Scenario 10: Input information incomplete (missing last name)**

5. Sales Employee inputs Smith, 566053978, 01/11/1775, 06/08/1997, john@gmail.com and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 11: Invalid email**

5. Sales Employee inputs John, Smith, 346058078, 03/04/1986, johnsmthgmail.com and +359887463390.
6. System does not accept and returns to step 5.


**Test Scenario 12: Invalid phone number**

5. Sales Employee inputs John, Smith, 346058078, 03/04/1986, johnsmth@gmail.com and 87463390.
6. System does not accept and returns to step 5.
