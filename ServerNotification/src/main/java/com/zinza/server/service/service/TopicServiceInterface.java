package com.zinza.server.service.service;

import java.util.ArrayList;

import com.zinza.server.service.model.TopicEntity;

public interface TopicServiceInterface {
	void insertTopic(TopicEntity topic);

	void insertTopic(String topicName);

	void deleteTopic(String topicId);

	TopicEntity searchTopic(String topicName);

	TopicEntity getTopic(String topicId);

	ArrayList<TopicEntity> getAllTopic();
}
