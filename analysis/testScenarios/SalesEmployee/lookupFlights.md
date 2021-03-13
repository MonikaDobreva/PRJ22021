### **Test Scenarios for look up flights**

To read Use Case click [**here**]( ../../UseCasesSalesEmployee.md )

**Flights in database**

1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
2. time "07.30", date "25.08.2022", arrival airport "Paris", destination airport "Amsterdam"
3. time "15.00", date "27.09.2022", arrival airport "Berlin", destination airport "London"
4. time "16.00", date "12.11.2022", arrival airport "London", destination airport "Berlin"


**General:**

1. Actor selects the search option.
2. System promps for input in form of search kewords(time, date, arrival airport, destination airport)



**Test Scenario 1: Searched Flight exists - Input time, date, arrival airport, destination airport)**

3. Sales employee inputs in the time box "15.00", date box "25.08.2022", arrival Airport "London" and destination airport "Amsterdam"
4. System shows list of all matches:
  1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
5. Actor can select a flight
6. System displays all information about the selected flight(all listed abve plus price and options to book)


**Test Scenario 2: Searched Flight exists - Input time**

3. Sales employee inputs in the time box "15.00", date box "", arrival Airport "" and destination airport""
4. System shows list of all matches:
  1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
  2. time "15.00", date "27.09.2022", arrival airport "Berlin", destination airport "London"
5. Actor can select a flight
6. System displays all information about the selected flight(all listed abve plus price and options to book)


**Test Scenario 3: Searched Flight exists - Input date**

3. Sales employee inputs in the time box "", date box "25.08.2022", arrival Airport "" and destination airport""
4. System shows list of all matches:
  1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
  2. time "07.30", date "25.08.2022", arrival airport "Paris", destination airport "Amsterdam"
5. Actor can select a flight
6. System displays all information about the selected flight(all listed abve plus price and options to book)


**Test Scenario 4: Searched Flight exists - Input arrival airport**

3. Sales employee inputs in the time box "", date box "", arrival Airport "London" and destination airport""
4. System shows list of all matches:
  1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
  2. time "16.00", date "12.11.2022", arrival airport "London", destination airport "Berlin"
5. Actor can select a flight
6. System displays all information about the selected flight(all listed abve plus price and options to book)


**Test Scenario 5: Searched Flight exists - Input destination airport**

3. Sales employee inputs in the time box "", date box "", arrival Airport "" and destination airport"Amsterdam"
4. System shows list of all matches:
  1. time "15.00", date "25.08.2022", arrival airport "London", destination airport "Amsterdam"
  2. time "07.30", date "25.08.2022", arrival airport "Paris", destination airport "Amsterdam"
5. Actor can select a flight
6. System displays all information about the selected flight(all listed abve plus price and options to book)




**Test Scenario 6: Searched Flight does not exist - Input time, date, arrival airport, destination airport)**

3. Sales employee inputs in the time box "12.00", date box "25.04.2022", arrival Airport "Tokyo" and destination airport "Amsterdam"
4. System displays message that no such flights are avaible.


**Test Scenario 7: Searched Flight does not exist - Input time**

3. Sales employee inputs in the time box "08.45", date box "", arrival Airport "" and destination airport""
4. System displays message that no such flights are avaible.


**Test Scenario 8: Searched Flight does not exist - Input date**

3. Sales employee inputs in the time box "", date box "01.01.2022", arrival Airport "" and destination airport""
4. System displays message that no such flights are avaible.


**Test Scenario 9: Searched Flight does not exist - Input arrival airport**

3. Sales employee inputs in the time box "", date box "", arrival Airport "Tokyo" and destination airport""
4. System displays message that no such flights are avaible.


**Test Scenario 10: Searched Flight does not exist - Input destination airport**

3. Sales employee inputs in the time box "", date box "", arrival Airport "" and destination airport "Tokyo"
4. System displays message that no such flights are avaible.
