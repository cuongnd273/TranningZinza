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
import com.zinza.server.service.model.ProjectEntity;

public class ProjectService implements ProjectServiceInterface {
	private MongoDatabase database = null;

	public ProjectService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public void insertProject(ProjectEntity project) {

		if (database != null) {

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String timeCreated = dtf.format(now);

			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			Document document = new Document();
			document.append(Constants.PROJECT_KEY_NAME, project.getProjectName());
			document.append(Constants.PROJECT_KEY_TIME_CREATED, timeCreated);
			document.append(Constants.PROJECT_KEY_DELETE, false);
			collection.insertOne(document);
		}
	}

	@Override
	public void insertProject(String name) {
		if (database != null) {
			// Get time current
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String timeCreated = dtf.format(now);

			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			Document document = new Document();
			document.append(Constants.PROJECT_KEY_NAME, name);
			document.append(Constants.PROJECT_KEY_TIME_CREATED, timeCreated);
			document.append(Constants.PROJECT_KEY_DELETE, false);
			collection.insertOne(document);
		}
	}

	@Override
	public void deleteProject(String projectId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			BasicDBObject where = new BasicDBObject(Constants.PROJECT_KEY_ID, new ObjectId(projectId));
			Document clause = new Document("$set", new Document(Constants.TOPIC_KEY_DELETE, true));
			collection.updateOne(where, clause);
		}
	}

	@Override
	public ProjectEntity getProject(String projectId) {
		ProjectEntity project = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.PROJECT_KEY_ID, new ObjectId(projectId));
			object.append(Constants.PROJECT_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return project;
			project = new ProjectEntity();
			project.setProjectId(document.getObjectId(Constants.PROJECT_KEY_ID).toString());
			project.setProjectName(document.getString(Constants.PROJECT_KEY_NAME));
			project.setProjectTimeCreate(document.getString(Constants.PROJECT_KEY_TIME_CREATED));
		}
		return project;
	}

	@Override
	public ArrayList<ProjectEntity> getAllProject() {
		ArrayList<ProjectEntity> list = null;
		if (database != null) {
			list = new ArrayList<>();
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			BasicDBObject object = new BasicDBObject(Constants.PROJECT_KEY_DELETE, false);
			FindIterable<Document> iterable = collection.find(object);
			ProjectEntity project;
			for (Document document : iterable) {
				project = new ProjectEntity();
				project.setProjectId(document.getObjectId(Constants.PROJECT_KEY_ID).toString());
				project.setProjectName(document.getString(Constants.PROJECT_KEY_NAME));
				project.setProjectTimeCreate(document.getString(Constants.PROJECT_KEY_TIME_CREATED));
				list.add(project);
			}
		}
		return list;
	}

	@Override
	public ProjectEntity searchProject(String projectName) {
		ProjectEntity project = null;
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_PROJECT);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.PROJECT_KEY_NAME, projectName);
			object.append(Constants.PROJECT_KEY_DELETE, false);
			Document document = collection.find(object).first();
			if (document == null)
				return project;
			project = new ProjectEntity();
			project.setProjectId(document.getObjectId(Constants.PROJECT_KEY_ID).toString());
			project.setProjectName(document.getString(Constants.PROJECT_KEY_NAME));
			project.setProjectTimeCreate(document.getString(Constants.PROJECT_KEY_TIME_CREATED));
		}
		return project;
	}

}
