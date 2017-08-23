package com.zinza.server.service.model;

public class ProjectEntity {
	private String projectId;
	private String projectName;
	private String projectTimeCreate;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectTimeCreate() {
		return projectTimeCreate;
	}
	public void setProjectTimeCreate(String projectTimeCreate) {
		this.projectTimeCreate = projectTimeCreate;
	}
}
