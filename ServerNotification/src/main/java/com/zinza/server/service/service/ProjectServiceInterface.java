package com.zinza.server.service.service;

import java.util.ArrayList;

import com.zinza.server.service.model.ProjectEntity;

public interface ProjectServiceInterface {
	void insertProject(ProjectEntity project);

	void insertProject(String name);

	void deleteProject(String projectId);

	ProjectEntity getProject(String projectId);

	ProjectEntity searchProject(String projectName);

	ArrayList<ProjectEntity> getAllProject();
}
