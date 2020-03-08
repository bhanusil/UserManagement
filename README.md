# UserManagement
MoneyLion User Access API. 


### Prerequests
Java 8, Maven, Apache Tomcat


### Deploying the Project
Just run below command to build the project. It will clean and rebuiding the package. 
```
mvn clean install
```
##### During the execution it will run 4 unit test cases. Please make sure project will buiding without and error. 


### Run the Project
Deploy the project into Apache Tomcat server. 


### Connect to the Database
Project is using embedded H2 database. Once you run the project please refer below steps to see the H2 dabase console using browser. 

Note : If your Tomcat Server is running different port use that port instead 8080. 
```
http://localhost:8080/h2-console/login.jsp
```
Change the JDBC URL as below
```
jdbc:h2:mem:testdb
```
Username should be sa and leave password as empty. Then click the connect button. Then it will connect to the database.
