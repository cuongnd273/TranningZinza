package com.zinza.server.service.model;

public class AppEntity {
	private String projectId;
	private String appId;
	private String appName;
	private String appTimeCreated;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppTimeCreated() {
		return appTimeCreated;
	}
	public void setAppTimeCreated(String appTimeCreated) {
		this.appTimeCreated = appTimeCreated;
	}
	
}
