package com.zinza.server.service.service;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.AppEntity;
import com.zinza.server.service.model.AppTopicEntity;
import com.zinza.server.service.model.TopicEntity;

public class AppTopicService implements AppTopicServiceInterface {
	private MongoDatabase database;

	public AppTopicService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void insertAppTopic(AppTopicEntity appTopic) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			Document document = new Document();
			document.append(Constants.APP_TOPIC_KEY_APP_ID, appTopic.getAppId());
			document.append(Constants.APP_TOPIC_KEY_TOPIC_ID, appTopic.getAppTopicId());
			document.append(Constants.APP_TOPIC_KEY_DELETE, false);
			collection.insertOne(document);
		}
	}

	@Override
	public void insertAppTopic(String appId, String topicId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			Document document = new Document();
			document.append(Constants.APP_TOPIC_KEY_APP_ID, appId);
			document.append(Constants.APP_TOPIC_KEY_TOPIC_ID, topicId);
			document.append(Constants.APP_TOPIC_KEY_DELETE, false);
			collection.insertOne(document);
		}
	}

	@Override
	public void deleteAppTopic(String appTopicId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			BasicDBObject where = new BasicDBObject(Constants.APP_TOPIC_KEY_APP_ID, new ObjectId(appTopicId));
			Document clause = new Document("$set", new Document(Constants.APP_TOPIC_KEY_DELETE, true));
			collection.updateOne(where, clause);
		}
	}

	@Override
	public ArrayList<TopicEntity> getAllTopicOfApp(String appId) {
		ArrayList<TopicEntity> list = null;
		if (database != null) {
			list = new ArrayList<>();
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_TOPIC_KEY_APP_ID, appId);
			object.append(Constants.APP_TOPIC_KEY_DELETE, false);
			FindIterable<Document> iterable = collection.find(object);
			TopicService topicService = new TopicService(database);
			for (Document document : iterable) {
				TopicEntity topic = topicService.getTopic(document.getString(Constants.APP_TOPIC_KEY_APP_ID));
				if (topic != null)
					list.add(topic);
			}
		}
		return list;
	}

	@Override
	public ArrayList<AppEntity> getAllAppOfTopic(String topicId) {
		ArrayList<AppEntity> list = null;
		if (database != null) {
			list = new ArrayList<>();
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_TOPIC_KEY_TOPIC_ID, topicId);
			object.append(Constants.APP_TOPIC_KEY_DELETE, false);
			FindIterable<Document> iterable = collection.find(object);
			AppService appService = new AppService(database);
			for (Document document : iterable) {
				AppEntity app = appService.getApp(document.getString(Constants.APP_TOPIC_KEY_APP_ID));
				if (app != null)
					list.add(app);
			}
		}
		return list;
	}

	@Override
	public AppTopicEntity getAppTopic(String appId, String topicId) {
		AppTopicEntity appTopic = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP_TOPIC);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_TOPIC_KEY_APP_ID, appId);
			object.append(Constants.APP_TOPIC_KEY_TOPIC_ID, topicId);
			object.append(Constants.APP_TOPIC_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document != null) {
				appTopic = new AppTopicEntity();
				appTopic.setAppId(document.getString(Constants.APP_TOPIC_KEY_APP_ID));
				appTopic.setTopicId(document.getString(Constants.APP_TOPIC_KEY_TOPIC_ID));
				appTopic.setAppTopicId(document.getString(Constants.APP_TOPIC_KEY_APP_ID));
			}
		}
		return appTopic;
	}

}
