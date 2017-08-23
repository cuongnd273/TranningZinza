package com.zinza.server.service.service;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.TopicEntity;

public class TopicService implements TopicServiceInterface {
	public MongoDatabase database;

	public TopicService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void insertTopic(TopicEntity topic) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			Document documentTopic = new Document();
			documentTopic.append(Constants.TOPIC_KEY_NAME, topic.getName());
			documentTopic.append(Constants.TOPIC_KEY_DELETE, false);
			collection.insertOne(documentTopic);
		}

	}

	@Override
	public void insertTopic(String topicName) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			Document documentTopic = new Document();
			documentTopic.append(Constants.TOPIC_KEY_NAME, topicName);
			documentTopic.append(Constants.TOPIC_KEY_DELETE, false);
			collection.insertOne(documentTopic);

		}
	}

	@Override
	public void deleteTopic(String topicId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			BasicDBObject where = new BasicDBObject(Constants.TOPIC_KEY_ID, new ObjectId(topicId));
			Document clause = new Document("$set", new Document(Constants.TOPIC_KEY_DELETE, true));
			collection.updateOne(where, clause);
		}
	}

	@Override
	public TopicEntity searchTopic(String topicName) {
		TopicEntity topic = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.TOPIC_KEY_NAME, topicName);
			object.append(Constants.TOPIC_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return topic;
			else {
				topic = new TopicEntity();
				topic.setName(document.getString(Constants.TOPIC_KEY_NAME));
				topic.setTopicId(document.getObjectId(Constants.TOPIC_KEY_ID).toString());
				return topic;
			}
		}
		return topic;
	}

	@Override
	public TopicEntity getTopic(String topicId) {
		TopicEntity topic = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.TOPIC_KEY_ID, new ObjectId(topicId));
			object.append(Constants.TOPIC_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return topic;
			else {
				topic = new TopicEntity();
				topic.setName(document.getString(Constants.TOPIC_KEY_NAME));
				topic.setTopicId(document.getObjectId(Constants.TOPIC_KEY_ID).toString());
				return topic;
			}
		}
		return topic;
	}

	@Override
	public ArrayList<TopicEntity> getAllTopic() {
		ArrayList<TopicEntity> list = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOPIC);
			BasicDBObject object = new BasicDBObject(Constants.APP_TOPIC_KEY_DELETE, false);
			list = new ArrayList<>();
			FindIterable<Document> iterable = collection.find(object);
			for (Document document : iterable) {
				TopicEntity topic = new TopicEntity();
				topic.setName(document.getString(Constants.TOPIC_KEY_ID));
				topic.setTopicId(document.getString(Constants.TOPIC_KEY_ID));
				list.add(topic);
			}
		}
		return list;
	}
}
