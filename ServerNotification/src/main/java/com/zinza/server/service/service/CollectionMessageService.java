package com.zinza.server.service.service;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.CollectionMessageEntity;

public class CollectionMessageService implements CollectionMessageServiceInterface {

	private MongoDatabase database;

	public CollectionMessageService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void insertCollectionMessage(String collectionName) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_COLLECTION_MESSAGE);
			Document document = new Document();
			document.append(Constants.COLLECTION_MESSAGE_KEY_NAME, collectionName);
			collection.insertOne(document);
		}
	}

	@Override
	public CollectionMessageEntity getCollectionMessage(String collectionName) {
		CollectionMessageEntity collectionMessage = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_COLLECTION_MESSAGE);
			BasicDBObject object = new BasicDBObject(Constants.COLLECTION_MESSAGE_KEY_NAME, collectionName);
			Document document = collection.find(object).first();
			if (document == null)
				return null;
			else {
				collectionMessage = new CollectionMessageEntity();
				collectionMessage.setNameCollectionMessage(document.getString(Constants.COLLECTION_MESSAGE_KEY_NAME));
			}
		}
		return collectionMessage;
	}

}
