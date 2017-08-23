package com.zinza.server.service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.MessageEntity;

public class MessageService implements MessageServiceInterface {
	public MongoDatabase database;

	public MessageService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void inserMessage(MessageEntity message) {
		if (database != null) {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_YYYY");
			LocalDateTime now = LocalDateTime.now();
			String timeCreated = dtf.format(now);

			// Check message in what collection
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_MESSAGE + timeCreated);
			Document document = new Document();
			document.append(Constants.MESSAGE_KEY_TOPIC_ID, message.getTopicId());
			document.append(Constants.MESSAGE_KEY_APP_ID, message.getAppId());
			document.append(Constants.MESSAGE_KEY_SEND_ALL, message.isSendAll());
			document.append(Constants.MESSAGE_KEY_SEND_TO, message.getSendTo());
			document.append(Constants.MESSAGE_KEY_CONTENT, message.getContent());
			document.append(Constants.MESSAGE_TIME_CREATED, message.getTimeCreated());
			document.append(Constants.MESSAGE_KEY_COLLECTION_NAME, Constants.COLLECTION_MESSAGE + timeCreated);
			collection.insertOne(document);

			CollectionMessageService collectionMessage = new CollectionMessageService(database);
			collectionMessage.insertCollectionMessage(Constants.COLLECTION_MESSAGE + timeCreated);
		}
	}

	@Override
	public void inserMessage(String topicId, String appId, boolean sendAll, String sendTo, String content) {
		if (database != null) {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM_YYYY");
			LocalDateTime now = LocalDateTime.now();
			String timeCreated = dtf.format(now);

			// Check message in what collection
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_MESSAGE + timeCreated);
			Document document = new Document();
			document.append(Constants.MESSAGE_KEY_TOPIC_ID, topicId);
			document.append(Constants.MESSAGE_KEY_APP_ID, appId);
			document.append(Constants.MESSAGE_KEY_SEND_ALL, sendAll);
			document.append(Constants.MESSAGE_KEY_SEND_TO, sendTo);
			document.append(Constants.MESSAGE_KEY_CONTENT, content);
			document.append(Constants.MESSAGE_TIME_CREATED, timeCreated);
			document.append(Constants.MESSAGE_KEY_COLLECTION_NAME, Constants.COLLECTION_MESSAGE + timeCreated);
			collection.insertOne(document);

			CollectionMessageService collectionMessage = new CollectionMessageService(database);
			collectionMessage.insertCollectionMessage(Constants.COLLECTION_MESSAGE + timeCreated);
		}
	}

	@Override
	public MessageEntity getMessage(String messageId) {
		MessageEntity message = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_MESSAGE);
			BasicDBObject object = new BasicDBObject(Constants.MESSAGE_KEY_ID, new ObjectId(messageId));
			Document document = collection.find(object).first();
			if (document == null)
				return null;
			message = new MessageEntity();
			message.setMessageId(document.getObjectId(Constants.MESSAGE_KEY_ID).toString());
			message.setTopicId(document.getString(Constants.MESSAGE_KEY_TOPIC_ID));
			message.setAppId(document.getString(Constants.MESSAGE_KEY_APP_ID));
			message.setSendAll(document.getBoolean(Constants.MESSAGE_KEY_SEND_ALL));
			message.setSendTo(document.getString(Constants.MESSAGE_KEY_SEND_TO));
			message.setContent(document.getString(Constants.MESSAGE_KEY_CONTENT));
			message.setTimeCreated(document.getString(Constants.MESSAGE_TIME_CREATED));
			message.setCollectionName(document.getString(Constants.MESSAGE_KEY_COLLECTION_NAME));
			return message;
		}
		return message;
	}

}
