### **Test Scenarios for Register flight**

To read Use Case click [**here**]( ../../UseCasesSalesOfficer.md )

**General**

1. The Sales Officer opens the application
2. The Sales Officer chooses the option to register a new flight
3. The system offers the Sales Officer to enter the necessary information about the flight

**Test Scenario 1: The ideal scenario (correct and sufficient input)**

4. The Sales Officer enters the necessary and proper flight data
5. The Sales Officer submits the process
6. The system make information checks and do not show any error message
7. The system add flight route to storage if not existent and adds the new flight together with the flight seats.
8. Confirmation message is displayed.

**Test Scenario 2: Missing flight data**

4. The Sales Officer enters not enough data (empty fields)
5. The System tries to process the data but some is missing.
6. The System displays an error message telling the user that some data is missing.
7. No flight, flight route or flight seat is added to storage.

**Test Scenario 3: Invalid flight data**

4. The Sales Officer enters wrong data
5. The system tries to process the data but some is wrong
6. The System displays an error message telling the user which data is wrong and why.
7. No flight, flight route or flight seat is added to storage.

**Remark**
 - Flight data consists of departure time, arrival time, origin airport, destination airport, base price (flight ID will be given automatically)
 - Wrong flight data consists on:
   · arrival date before the departure date
   · departure date and/or arrival date in the past
   · price less or equal to 0
   · Airport IATA code consisting of more than 3 letters and with more characters ratter than letters
   · Airplane code consisting of more than 6 characters and containing more characters rather than letters and "-"
