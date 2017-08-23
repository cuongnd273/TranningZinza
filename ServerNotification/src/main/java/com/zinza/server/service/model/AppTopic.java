package com.zinza.server.service.model;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.AppService;
import com.zinza.server.service.service.AppTopicService;
import com.zinza.server.service.service.TopicService;

public class AppTopic {
	private MongoDatabase database;
	private AppTopicService appTopicService;

	public AppTopic() {
		super();
	}

	public AppTopic(MongoDatabase database) {
		super();
		this.database = database;
		this.appTopicService = new AppTopicService(database);
	}

	public void insertAppTopic(AppTopicEntity appTopic) {
		if (this.appTopicService.getAppTopic(appTopic.getAppId(), appTopic.getTopicId()) != null)
			return;
		this.appTopicService.insertAppTopic(appTopic);
	}

	public void insertAppTopic(String appId, String topicId) {
		if (this.appTopicService.getAppTopic(appId, topicId) != null)
			return;
		this.appTopicService.insertAppTopic(appId, topicId);
	}

	public ArrayList<AppEntity> getAllAppOfTopic(String topicId) {
		TopicService topicService = new TopicService(database);
		if (topicService.getTopic(topicId) == null)
			return null;
		return this.appTopicService.getAllAppOfTopic(topicId);
	}

	public ArrayList<TopicEntity> getAllTopicOfApp(String appId) {
		AppService appService = new AppService(database);
		if (appService.getApp(appId) == null)
			return null;
		return this.appTopicService.getAllTopicOfApp(appId);
	}
}
