## Use case for all the same

|Use case Number|x|
| --- | --- |
| **Name** | **Log in** |
| **Actor** | Sales Officer, Sales Employee, Sales Manager |
| **Description** | A User logs in to the application |
| **Pre-condition** | The User needs to have a registered account for the application |
| **Scenario** |1. The User opens the application </br>2. The User chooses the option to log in </br>3. The System asks for the credentials of the User </br>4. The User inputs their login data </br> 5. The User submits </br>6. The System informs the User that they are logged in. </br>|
| **Result** | The User has successfully logged in to the application |
| **Exceptions** | 6.1 The User has not passed all the necessary information to the system.  System message: &quot;Information missing&quot; </br> 6.1.1 Returns to step 4 </br> 6.2 The User has passed false or invalid information to the system. System message: &quot;Provided information invalid or false&quot; </br> 6.2.1 Returns to step 4 |Test 
|**Test Scenarios**|Click [**here**](testScenarios/FishLevel/logIn.md)|
