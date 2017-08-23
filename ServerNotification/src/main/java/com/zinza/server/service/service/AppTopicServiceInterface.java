package com.zinza.server.service.service;

import java.util.ArrayList;

import com.zinza.server.service.model.AppEntity;
import com.zinza.server.service.model.AppTopicEntity;
import com.zinza.server.service.model.TopicEntity;

public interface AppTopicServiceInterface {
	void insertAppTopic(AppTopicEntity appTopic);

	void insertAppTopic(String appId, String topicId);

	void deleteAppTopic(String appTopicId);

	AppTopicEntity getAppTopic(String appId, String topicId);

	ArrayList<TopicEntity> getAllTopicOfApp(String appId);

	ArrayList<AppEntity> getAllAppOfTopic(String topicId);
}
