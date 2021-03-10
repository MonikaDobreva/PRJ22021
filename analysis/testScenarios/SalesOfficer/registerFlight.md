### **Test Scenarios for Register flight**

**General**

1. The Sales Officer opens the application
2. The Sales Officer chooses the option to register a new flight
3. The system offers the Sales Officer to enter the necessary information about the flight

**Test Scenario 1: The ideal scenario (correct and sufficient input)**
4. The Sales Officer enters the necessary flight data
5. The Sales Officer submits the process
6. The system adds the new flight to the list
7. The Sales Officer searches for the flight.
8. The search results in exactly one flight being displayed.

**Test Scenario 2: Missing flight data**
4. The Sales Officer enters not enough data
5. 

## Test scenarios
| Use case | Input | Test scenario |
| --- | --- | --- |
| Register flight | Airports, Route, Plane,..., Flight Number "LH384" | Searching for a flight by the number "LH384" should return one search result. |
| Edit flight | Change flight number from "LH384" to "LH334" | Searching for flight "LH384" should no longer return a result, but "LH334" |
| Edit flight | Change departure airport of flight "LH1234" from "DUS" to "MUC" | A list of all flights leaving from DUS should no longer contain the flight "LH1234" |
| Delete flight | Deletion of flight "LH1234" | Searching for the flight "LH1234" should return zero results. |
| Log in | - | Managing flights or bookings should not throw an exception (UnauthorizedUserException). |
