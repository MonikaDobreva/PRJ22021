## Flight Use Cases

_SO = Sales officer | FA = Flight application_

|     |     |
| --- | --- |
| Name | **Register flight** |
| Actor | Sales officer |
| Description | A sales officer creates a new flight in the database with the help of the application |
| Pre-condition | The sales officer needs to have an account for the app (?) |
| Scenario | 1. The SO opens the FA </br> 2. The SO clicks the button called &quot;Register new flight&quot; </br> 3. The FA opens a new window and offers the SO to enter the necessary information about the flight into the respective fields </br> 4. The SO inputs all the data they can provide </br> 5. The SO completes the process by hitting &quot;Publish flight&quot; </br> 6. The FA adds the new flight to the database to show up in the FA itself |
| Result | The SO has successfully created a new flight for the airline company |
| Exception | 6.1 The SO has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Use case ends here </br> 6.2 The SO has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Use case ends here |

|     |     |
| --- | --- |
| Name | **Edit flight** |
| Actor | Sales officer |
| Description | A sales officer edits an existing flight in the database with the help of the application |
| Pre-condition | The sales officer must have an account for the app and the flight to be edited must exists in the database |
| Scenario |1. The SO opens the FA </br>2. The SO clicks the button called &quot;see all flight&quot; </br>3. The SO clicks the button called &quot;edit [this] flight&quot; next to the respective flight in the list </br>4. The FA opens a new window similar to the registration of a new flight </br>5. The SO inputs/changes/deletes the needed information </br>6. The SO completes the process by hitting &quot;Change flight&quot; </br>7. The FA displays the changed flight inside of the list </br> |
| Result | The SO has successfully edited an existing flight for the airline company |
| Exception | 6.1 The SO has not passed all the needed information to the system. System message: &quot;Information missing&quot; </br> 6.1.1 Use case ends here </br> 6.2 The SO has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Use case ends here |

|     |     |
| --- | --- |
| Name | **Delete flight** |
| Actor | Sales officer |
| Description | A sales officer deletes an existing flight from the database with the help of the application |
| Pre-condition | The sales officer needs to have an account for the app (?) and the flight to be deleted must exists in the database |
| Scenario |1. The SO opens the FA </br>2. The SO clicks the button called &quot;see all flight&quot; </br>3. The SO clicks the button called &quot;delete [this] flight&quot; next to the respective flight in the list </br>4. The FA asks for confirmation </br>5. The SO submits with &quot;Yes&quot; </br>6. The FA deletes the flight from the database which removes it from the list itself </br>|
| Result | The SO has successfully deleted an existing flight for the airline company |
| Exception | - |
