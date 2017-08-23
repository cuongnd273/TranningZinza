package com.zinza.server.service.model;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.AppService;

public class App {
	private AppService appService;

	public App() {
		super();
	}

	public App(MongoDatabase database) {
		super();
		this.appService = new AppService(database);
	}

	public boolean insertApp(AppEntity app) {
		if (this.appService.searchApp(app.getProjectId(), app.getAppName()) == null)
			return false;
		this.appService.insertApp(app);
		return true;
	}

	public boolean insertApp(String projectId, String appName, String apiKey) {
		if (this.appService.searchApp(projectId, appName) == null)
			return false;
		this.appService.insertApp(projectId, appName, apiKey);
		return true;
	}
	public void deleteApp(String appId) {
		this.appService.deleteApp(appId);
	}
	public AppEntity getApp(String projectId,String appId) {
		return this.appService.getApp(projectId, appId);
	}
	public ArrayList<AppEntity> getAllAppOfProject(String projectId){
		return this.appService.getAllAppOfProject(projectId);
	}
}
