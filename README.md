SOAProject
==========

Sample SOA project with JMS, JPA, SOAP and CXF.

Please take a look at CarServiceImpl Class for JPA and JMS.
After saving a new Car Object, a JMS queue message is sent to ask to refresh the cached Car list stored in CarServiceImpl.


Run HSQLDB DataBase
java -cp ./lib/hsqldb.jar org.hsqldb.Server
java -cp ./lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

Run ActiveMQ JMS Server