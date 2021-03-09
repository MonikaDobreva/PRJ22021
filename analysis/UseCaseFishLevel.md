## Use case for all the same

|Use case Number|x|
| --- | --- |
| **Name** | **Log in** |
| **Actor** | Sales officer, employee, manager |
| **Description** | An user logs in to the application |
| **Pre-condition** | The user needs to have an registered account for the app |
| **Scenario** |1. The user opens the application </br>2. The user chooses the option to log in </br>3. The system asks for the credentials of the user </br>4. The user inputs his login data </br> 5. The user submits </br>6. The system informs the user that he is logged in. </br>|
| **Result** | The sales officer has successfully log in to the application |
| **Exceptions** | 6.1 The user has not passed all the needed information to the system.  System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 4 </br> 6.2 The sales officer has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 4 |Test 
