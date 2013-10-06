package com.opensource.springdao.mongodb.configuration;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.Mongo;

@Component
public class CustomMongoJNDIFactory implements ObjectFactory {

	private MongoTemplate mongoTemplate;
	private String db;
	private String host;
	private String username ;
	private String password;
	private int port = 27017;

	@Bean
	public MongoTemplate getMongoTemplate() {
		return this.mongoTemplate;
	}

//	@Bean
//	public void setMongoTemplate(MongoTemplate mongoTemplate) {
//		this.mongoTemplate = mongoTemplate;
//	}

	public Object getObjectInstance(Object obj, Name name, Context nameCtx,
			Hashtable<?, ?> environment) throws Exception {

		 Object result = null;
		 
		validateProperty(obj, "Invalid JNDI object reference");

		Reference ref = (Reference) obj;
		Enumeration<RefAddr> props = ref.getAll();
		while (props.hasMoreElements()) {
			RefAddr addr = (RefAddr) props.nextElement();
			String propName = addr.getType();
			String propValue = (String) addr.getContent();
			if (propName.equals("db")) {
				db = propValue;
			} else if (propName.equals("host")) {
				host = propValue;
			} else if (propName.equals("username")) {
				username = propValue;
			} else if (propName.equals("password")) {
				password = propValue;
			} else if (propName.equals("port")) {
				try {
					port = Integer.parseInt(propValue);
				} catch (NumberFormatException e) {
					throw new NamingException("Invalid port value " + propValue);
				}
			}
		}
		
		System.out.println(db + " - "+ host +" - " + username + " - " + password + " - " + port);
		
		// validate properties
		validateProperty(db, "Invalid or empty mongo database name");
		validateProperty(host, "Invalid or empty mongo host");
		validateProperty(username, "Invalid or empty mongo username");
		validateProperty(password, "Invalid or empty mongo password");

		this.mongoTemplate = new MongoTemplate(new Mongo(host, port), db,
				new UserCredentials(username, password));

		result = this.mongoTemplate;
		
		System.out.println(this.mongoTemplate);
		
		return result;
		
//		return null;

	}

	/**
	 * Validate internal String properties
	 * 
	 * @param property
	 * @param errorMessage
	 * @throws NamingException
	 */
	private void validateProperty(String property, String errorMessage)
			throws NamingException {
		if (property == null || property.trim().equals("")) {
			throw new NamingException(errorMessage);
		}
	}

	/**
	 * Validate internal Object properties
	 * 
	 * @param property
	 * @param errorMessage
	 * @throws NamingException
	 */
	private void validateProperty(Object property, String errorMessage)
			throws NamingException {
		if (property == null) {
			throw new NamingException(errorMessage);
		}
	}
}