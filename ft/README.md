

# Journey Controller Function Tests


| Case Name                  | Desciption                                                   | File                                  |
| -------------------------- | ------------------------------------------------------------ | ------------------------------------- |
| Journey Main Workflow | **This test case tests the main workflow of a journey** <br> 1. Alice login <br> 2. Bob login <br> 3. Alice create a journey <br> 4. Bob join the journey <br> 5. Alice approves Bob to be in this group <br> 6. Alice starts the joureny <br> 7. Alice confirms "arrived" <br> 8. Bob confirms  "arrived" <br> 9. Alice login <br> 10. Bob login <br> 11. Bob create a journey <br> 12. Alice join the journey <br> 13. Bob approves Alice to be in this group <br> 14. Bob starts the joureny <br> 15. Bob confirms "arrived" <br> 16. Alice confirms  "arrived" | journey_main_workflow_test.py |
| When Journey Does not exist | **When a journey does not exist, join operation should fail.** <br>1. Alice login <br>2. Alice join a journey with a non-exist journey id(random generated), expected success == false | journey_journey_does_not_exist_test.py |
| Bob approve himself to join Alice's journey | **Bob wants to join Alice's journey, the result should be false** <br>1. Alice create a journey <br> 2. Bob joins this journey <br> 3. Bob approves himself, expected success == false | journey_malformed_approve_test.py |
| Alice starts a journey before her approve Bob's application | **All unapproved applicants will not be in a journey** <br> 1. Alice creates a journey <br> 2. Bob joints this journey <br> 3. Alice starts this journey <br> 4. Bob should not in this journey | TBD |
| Alice double confirmed "arrived"  | **It is ilegal for each user to confirm "arrived" twice** <br> 1. Alice login <br> 2. Bob login <br> 3. Alice creates a journey <br> 4. Bob joins this journey <br> 5. Alice starts the journey <br> 6. Alice confirms "arrived" <br> 7. Alice confirms "arrived" again, expected success == false | TBD |