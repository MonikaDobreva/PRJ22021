### **Test Scenarios for Create booking**

To read Use Case click [**here**]( ../../UseCasesSalesEmployee.md )

**General:**

1. Sales Employee looks up a flight
2. System gives the opportunity to book one or more tickets for this flight
3. Sales Employee chooses the option to book one or more tickets for this flight
4. System asks the Sales Employee for information such as name, gender, birthday, e-mail, phone number for the customer and additionally asks for the same information plus the passport number of every passenger


**Test Scenario 1: Everything works fine (input information is acceptable)**

5. Sales Employee inputs John, Smith, m, 346058078, 03/04/1986, johnsmth@gmail.com and +359887463390.
6. System saves the information, reserves the seat(s) and issues the ticket(s).


**Test Scenario 2: Everything works fine with extension**

5. Sales Employee inputs Jane, Peters, f, 355058078, 06/08/1997, janep@hotmail.com and +31657895435.
6. Sales Employee chooses to also input one additional baggage and a vegetarian meal.
7. System saves the information, reserves the seat(s) and issues the ticket(s).


**Test Scenario 3: Input information incomplete (missing passport number)**

5. Sales Employee inputs John, Smith, m, 09/12/1975, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 4: The birth date inserted is in the future**

5. Sales Employee inputs John, Smith, m, 566053978, 09/12/2075, johnsmith@hotmail.com and +31658234987.
6. System does not accept and returns to 5.


**Test Scenario 5: The inserted birth date is before 1900**

5. Sales Employee inputs John, Smith, m, 566053978, 01/11/1775, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 6: Input information incomplete (missing birth date)**

5. Sales Employee inputs John, Smith, m, 566053978, johns@hotmail.com and +359886453688.
6. System does not accept and returns to 5.


**Test Scenario 7: Input information incomplete (missing last name)**

5. Sales Employee inputs John, m, 566053978, 01/11/1775, 06/08/1997, john@gmail.com and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 8: Input information incomplete (missing e-mail)**

5. Sales Employee inputs Jane, Peters, f, 355058078, 06/08/1997 and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 9: Input information incomplete (missing phone number)**

5. Sales Employee inputs John, Smith, m, 346058078, 03/04/1986 and johnsmth@gmail.com.
6. System does not accept and returns to step 5.


**Test Scenario 10: Input information incomplete (missing first name)**

5. Sales Employee inputs Smith, m, 566053978, 01/11/1775, 06/08/1997, john@gmail.com and +31657895435.
6. System does not accept and returns to 5.


**Test Scenario 11: Invalid e-mail format**

5. Sales Employee inputs John, Smith, m, 346058078, 03/04/1986, johnsmthgmail.com and +359887463390.
6. System does not accept and returns to step 5.


**Test Scenario 12: Invalid phone number**

5. Sales Employee inputs John, Smith, m, 346058078, 03/04/1986, johnsmth@gmail.com and 87463390.
6. System does not accept and returns to step 5.


**Test Scenario 12: Input information incomplete (missing gender)**

5. Sales Employee inputs John, Smith, 346058078, 03/04/1986, johnsmth@gmail.com and 87463390.
6. System does not accept and returns to step 5.
