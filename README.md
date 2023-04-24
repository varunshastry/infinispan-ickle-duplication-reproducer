# infinispan-ickle-duplication-reproducer


Issue simulation steps:

Checkout the repository and run "docker compose up". It should have mysql and infinispan containers running successfully

Run "cd cache-accessor" and "./run-debug.sh"

Run "./test_post_single_entity.sh" to check if a single entity can be posted to the running service. Verify if the entity is present in the database

Once the above step is successful, run "./test_post_multiple_entity.sh 2500 1" to insert 2,500 entities

Check if the database has 2,501 entries

Delete the entry inserted by "./test_post_single_entity.sh" -> {"sourceCode": "mysourcecode", "targetCode": "mytargetcode", "realm": "myrealm"}

Run "./test_post_multiple_entity.sh 2500 2501" to insert another 2,500 entities

Verify the database table to ensure it has 5,000 entries

Navigate to Ickle query tab of the cache "QuestionQuestion" in web console and fetch all records using the query "from life.genny.qwandaq.persistence.questionquestion.QuestionQuestion" - this would should incorrect number of records

Navigate to "Manage Indexes" tab of the cache "QuestionQuestion" in web console - this would should incorrect number of entities
