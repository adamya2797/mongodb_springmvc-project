package com.airtel.mogo.repository;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

@SuppressWarnings("deprecation")
public class MongoFactory {
	
	private static Mongo mongo;
	
	public MongoFactory() {}
	
	
	public static Mongo getMongo() {
		int port_no = 27017;
		String hostname = "localhost";
		
		if(mongo == null) {
			try {
				mongo = new Mongo(hostname, port_no);
			}catch(MongoException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return mongo;
	}
	
	public static DB getDB(String db_name) {
		return getMongo().getDB(db_name);
	}
	
	public static DBCollection getCollection(String db_name, String db_collection) {
		return getDB(db_name).getCollection(db_collection);
	}
	

}
