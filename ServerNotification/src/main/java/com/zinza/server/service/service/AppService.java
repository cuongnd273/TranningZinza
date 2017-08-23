package com.zinza.server.service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.AppEntity;

public class AppService implements AppServiceInterface {
	private MongoDatabase database;

	public AppService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void insertApp(AppEntity app) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			Document document = new Document();
			document.append(Constants.APP_KEY_PROJECT_ID, app.getProjectId());
			document.append(Constants.APP_KEY_NAME, app.getAppName());
			document.append(Constants.APP_KEY_TIME_CREATED, app.getAppTimeCreated());
			document.append(Constants.APP_KEY_DELETE, false);
			collection.insertOne(document);
		}

	}

	@Override
	public void insertApp(String projectId, String appName, String apiKey) {
		if (database != null) {
			// Get time current
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String timeCreated = dtf.format(now);

			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			Document document = new Document();
			document.append(Constants.APP_KEY_PROJECT_ID, projectId);
			document.append(Constants.APP_KEY_NAME, appName);
			document.append(Constants.APP_KEY_API, apiKey);
			document.append(Constants.APP_KEY_TIME_CREATED, timeCreated);
			document.append(Constants.APP_KEY_DELETE, false);
			collection.insertOne(document);
		}
	}

	@Override
	public void deleteApp(String appId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			BasicDBObject where = new BasicDBObject(Constants.APP_KEY_ID, appId);
			Document clause = new Document("$set", new Document(Constants.APP_KEY_DELETE, true));
			collection.updateOne(where, clause);
		}
	}

	@Override
	public AppEntity getApp(String projectId, String appId) {
		AppEntity app = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_KEY_ID, new ObjectId(appId));
			object.append(Constants.APP_KEY_PROJECT_ID, projectId);
			object.append(Constants.APP_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return app;
			app = new AppEntity();
			app.setProjectId(document.getString(Constants.APP_KEY_PROJECT_ID));
			app.setAppId(document.getObjectId(Constants.APP_KEY_ID).toString());
			app.setAppName(document.getString(Constants.APP_KEY_NAME));
			app.setAppTimeCreated(document.getString(Constants.APP_KEY_TIME_CREATED));
		}
		return app;
	}

	@Override
	public AppEntity getApp(String appId) {
		AppEntity app = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_KEY_ID, new ObjectId(appId));
			object.append(Constants.APP_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return app;
			app = new AppEntity();
			app.setProjectId(document.getString(Constants.APP_KEY_PROJECT_ID));
			app.setAppId(document.getObjectId(Constants.APP_KEY_ID).toString());
			app.setAppName(document.getString(Constants.APP_KEY_NAME));
			app.setAppTimeCreated(document.getString(Constants.APP_KEY_TIME_CREATED));
		}
		return app;
	}

	@Override
	public AppEntity searchApp(String projectId, String appName) {
		AppEntity app = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_KEY_NAME, appName);
			object.append(Constants.APP_KEY_PROJECT_ID, projectId);
			object.append(Constants.APP_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return app;
			app = new AppEntity();
			app.setProjectId(document.getString(Constants.APP_KEY_PROJECT_ID));
			app.setAppId(document.getObjectId(Constants.APP_KEY_ID).toString());
			app.setAppName(document.getString(Constants.APP_KEY_NAME));
			app.setAppTimeCreated(document.getString(Constants.APP_KEY_TIME_CREATED));
		}
		return app;
	}

	@Override
	public ArrayList<AppEntity> getAllAppOfProject(String projectId) {

		ArrayList<AppEntity> list = null;
		if (database != null) {
			list = new ArrayList<>();
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_APP);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.APP_KEY_PROJECT_ID, projectId);
			object.append(Constants.APP_KEY_DELETE, false);
			FindIterable<Document> iterable = collection.find(object);
			for (Document document : iterable) {
				AppEntity app = new AppEntity();
				app.setProjectId(document.getString(Constants.APP_KEY_PROJECT_ID));
				app.setAppId(document.getObjectId(Constants.APP_KEY_ID).toString());
				app.setAppName(document.getString(Constants.APP_KEY_NAME));
				app.setAppTimeCreated(document.getString(Constants.APP_KEY_TIME_CREATED));
				list.add(app);
			}
		}
		return list;
	}

}
