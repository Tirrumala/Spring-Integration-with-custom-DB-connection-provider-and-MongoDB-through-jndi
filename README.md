Spring-Integration-with-custom-DB-connection-provider-and-MongoDB-through-jndi
==============================================================================

This project provides spring integration with 
- Our own customized DataBase ConnectionProvider which is used by hibernate for DB connection.
- And MongoDB through JNDI Resource using Custom Mongodb Factory class.

The dao objects of DB and MongoDB will be created during server startup. 
The spring is initialized by spring context loader during application server startup through web.xml.

Checkedin two projects for DB and Mongodb Objects initizalition.

++++ Our own customized DataBase ConnectionProvider

ConnectionProviderImpl.java, is a java class with implemented Connection provider to handle the db operations instead using the Apach DB Connection Source.
I only implemented getconnection and closeconnection.

In hibernate.properties, specify connection Provider class and other hibernate properties.

applicationContext-springdao-config.xml - main spring application context xml to load the db  objects.

hibernate-config.xml  - read the hibernate properties and create session factory.

TestCustomDBAndMongoDB.java - test class shows to use DB objects.


++++ MongoDB through JNDI using property file.

context.xml - create Jndi Resource entry, which provides the mongodb properties and its custom factory class information.

web.xml - create context load for spring initialization
		  Have resource-ref entry giving jndi name and its return type.

applicationContext-springdao-config.xml - main spring application context xml to load the db  objects.
J2ee tag to specify the Custom Mongodb factory for creating the mongotemplate.
					
CustomMongoJNDIFactory.java - Creates the mongotemplate fetching mongodb properties through jndi.

TestCustomDBAndMongoDB.java - test class shows to use MongoDB objects.


+++Contribution:
	
Thanks to Gopalakrishnan.P for support.				

http://stackoverflow.com/questions/4076254/mongodb-via-jndi



