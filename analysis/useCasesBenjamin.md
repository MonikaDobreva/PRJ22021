## Flight Use Cases

|     |     |
| --- | --- |
| Name | **Register flight** |
| Actor | Sales officer |
| Description | A sales officer creates a new flight with the help of the application |
| Precondition | The sales officer needs to have an account for the app |
| Scenario | 1. The sales officer opens the application </br> 2. The sales officer chooses the option to register a new flight </br> 3. The system offers the sales officer to enter the necessary information about the flight </br> 4. The sales officer inputs all the data they can provide </br> 5. The sales officer submits the process </br> 6. The system adds the new flight to the list |
| Result | The sales officer has successfully created a new flight for the airline company |
| Exception | 6.1 The sales officer has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 4 </br> 6.2 The sales officer has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 4 |

|     |     |
| --- | --- |
| Name | **Edit flight** |
| Actor | Sales officer |
| Description | A sales officer edits an existing flight with the help of the application |
| Precondition | The sales officer must have an account for the app and the flight to be edited must exists |
| Scenario |1. The sales officer opens the application </br>2. The sales officer views all flights </br>3. The sales officer chooses the option to edit a flight </br>4. The system offers the sales officer to enter the necessary information about the flight </br>5. The sales officer adds/changes/deletes the needed information </br>6. The sales officer submits the process </br>7. The system displays the changed flight inside of the list </br> |
| Result | The sales officer has successfully edited an existing flight for the airline company |
| Exception | 6.1 The sales officer has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 5 </br> 6.2 The sales officer has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 5 |

|     |     |
| --- | --- |
| Name | **Delete flight** |
| Actor | Sales officer |
| Description | A sales officer deletes an existing flight with the help of the application |
| Pre-condition | The sales officer needs to have an account for the app and the flight to be deleted must exists |
| Scenario |1. The sales officer opens the application </br>2. The sales officer views all flights </br>3. The sales officer chooses the option to delete a flight </br>4. The system asks for confirmation </br>5. The sales officer submits with &quot;Yes&quot; </br>6. The system removes the flight </br>|
| Result | The sales officer has successfully deleted an existing flight for the airline company |
| Exception | - |