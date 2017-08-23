package com.zinza.server.service.database;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class DatabaseControls {
	private MongoCredential mongoCredential;
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	public  MongoDatabase connect() {
		mongoCredential = MongoCredential.createCredential(Constants.USER, Constants.DATABASE,
				Constants.PASS.toCharArray());
		mongoClient = new MongoClient(new ServerAddress(Constants.HOST, Constants.PORT),
				Arrays.asList(mongoCredential));
		mongoDatabase = mongoClient.getDatabase(Constants.DATABASE);
		return mongoDatabase;
	}
}
