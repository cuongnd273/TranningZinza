package com.zinza.server.service.model;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.AppService;
import com.zinza.server.service.service.ProjectService;

public class Project {
	private ProjectService projectService;
	private MongoDatabase database;

	public Project() {
		super();
	}

	public Project(MongoDatabase database) {
		super();
		this.database = database;
		this.projectService = new ProjectService(database);
	}

	public boolean insertProject(ProjectEntity project) {
		if (this.projectService.searchProject(project.getProjectName()) != null)
			return false;
		this.projectService.insertProject(project);
		return true;
	}

	public boolean insertProject(String projectName) {
		if (this.projectService.searchProject(projectName) != null)
			return false;
		this.projectService.insertProject(projectName);
		return true;
	}

	public ProjectEntity getProject(String projectId) {
		return this.projectService.getProject(projectId);
	}

	public ArrayList<ProjectEntity> getAllProject() {
		return this.projectService.getAllProject();
	}

	public void deleteProject(String projectId) {
		// Delete app
		AppService appService = new AppService(database);
		ArrayList<AppEntity> list = appService.getAllAppOfProject(projectId);
		for (AppEntity app : list) {
			appService.deleteApp(app.getAppId());
		}
		// Delete project
		this.projectService.deleteProject(projectId);
	}
}
