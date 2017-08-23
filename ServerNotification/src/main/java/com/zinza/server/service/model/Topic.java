package com.zinza.server.service.model;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.TopicService;

public class Topic {
	private TopicService topicService;

	public Topic() {
		super();
	}

	public Topic(MongoDatabase database) {
		super();
		this.topicService = new TopicService(database);
	}

	public void insertTopic(TopicEntity topic) {
		if (this.topicService.getTopic(topic.getName()) != null)
			return;
		this.topicService.insertTopic(topic);
	}

	public void insertTopic(String topicName) {
		if (this.topicService.getTopic(topicName) != null)
			return;
		this.topicService.insertTopic(topicName);
	}

	public TopicEntity getTopic(String topicName) {
		return this.topicService.getTopic(topicName);
	}

	public ArrayList<TopicEntity> getAllTopic() {
		return this.topicService.getAllTopic();
	}
}
