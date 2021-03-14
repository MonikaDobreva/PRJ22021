### Test Scenarios for Edit Flight

To read Use Case click [**here**]( ../../UseCasesSalesOfficer.md )

**General:**
1. The Sales Officer opens the application
2. The Sales Officer views all flights
3. The Sales Officer chooses the option to edit a flight
4. The system offers the Sales Officer to change the necessary information about the flight

**Test Scenario 1: The ideal scenario (correct and sufficient input)**

5. The Sales Officer edits the needed information
6. The Sales Officer submits the process
7. The system displays the changed flight inside of the list
8. The Sales Officer searches for the previously edited flight by the changed information
9. The system displayes the correct flight.

**Test Scenario 2: Necessary information was deleted during the edit**

5. The Sales Officer edits the needed information
6. The Sales Officer submits the process
7. The system complains about insufficient information
8. The Sales Officer searches for the previously edited flight
9. The system still displayes the same flight.

**Test Scenario 3: Invalid information was added during the edit**

5. The Sales Officer edits the needed information
6. The Sales Officer submits the process
7. The system complains about insufficient information
8. The Sales Officer searches for the previously edited flight by the changed information
9. The system does not display the flight.
