package com.zinza.server.service.service;

import java.util.ArrayList;

import com.zinza.server.service.model.AppEntity;

public interface AppServiceInterface {
	void insertApp(AppEntity app);

	void insertApp(String projectId, String appName, String apiKey);

	void deleteApp(String appId);

	AppEntity getApp(String projectId, String appId);

	AppEntity getApp(String appId);

	AppEntity searchApp(String projectId, String appName);

	ArrayList<AppEntity> getAllAppOfProject(String projectId);
}
