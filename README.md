# Java 21 | Playwright | TestNG | Maven |
TODO: Give a short introduction of your project. Let this section explain the objectives or the motivation behind this project. 

# Getting Started
TODO: Guide users through getting your code up and running on their own system. In this section you can talk about:
1. Copy src/test/resources/conf.properties.TEMPLATE and pass into src/test/resources/ with name "conf.properties" (do not push this file into GIT)
2. Copy src/test/resources/config.properties.TEMPLATE and pass into src/test/resources/ with name "config.properties" (do not push this file into GIT)
3. Fill data into conf.properties and config.properties.

# Build and Test
TODO: Describe and show how to build your code and run the tests. 
1. Enable Lombok functionality when IDE ask to enable.(When it asks 2 times than enable 2 times)
2. Run tests from PWWrapperTests class

# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

# To run tests on Test env from command line use this(don't forget to update conf.properties with userdata for Test env):
1. Start all tests from the "Smoke" suite:
   mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/Smoke.xml
2. If we want to execute a single test class, we can execute the command: 
   mvn test -Dtest=”TestClassName”
3. If we want to execute a single test method, we can execute the command: 
   mvn test -Dtest=”TestClassName#TestMethodName”
4. Execute multiple test classes by name:
   mvn test -Dtest=”TestClassName1, TestClassName2, TestClassName3…”
5. Execute multiple test classes by name pattern:
   mvn test -Dtest=”*ServiceUnitTest” or -Dtest=”The*UnitTest, Controller*Test”
6. Specify multiple test methods by name:
   mvn test -Dtest=”ClassName#method1+method2″
7. Specify multiple method names by name pattern:
   mvn test -Dtest=”ClassName#whenSomethingHappens_*”


