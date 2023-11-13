# Java 21 | Playwright | TestNG | Maven |
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1.	Copy conf.properties.TEMPLATE and change name to conf.properties(do not push this file into GIT)
2.	

# Build and Test
TODO: Describe and show how to build your code and run the tests. 

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

# To run tests on Test env from command line use this(don't forget to update conf.properties with userdata for Test env):
1. Start all tests from the "Smoke" suite:
   mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Smoke.xml
3. Start all tests from the "Regression" suite:
   mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Regression.xml
4. If we want to execute a single test class, we can execute the command: 
   mvn test -Dtest=”TestClassName”
5. If we want to execute a single test method, we can execute the command: 
   mvn test -Dtest=”TestClassName#TestMethodName”
6. Execute multiple test classes by name:
   mvn test -Dtest=”TestClassName1, TestClassName2, TestClassName3…”
7. Execute multiple test classes by name pattern:
   mvn test -Dtest=”*ServiceUnitTest” or -Dtest=”The*UnitTest, Controller*Test”
8. Specify multiple test methods by name:
   mvn test -Dtest=”ClassName#method1+method2″
9. Specify multiple method names by name pattern:
   mvn test -Dtest=”ClassName#whenSomethingHappens_*”


