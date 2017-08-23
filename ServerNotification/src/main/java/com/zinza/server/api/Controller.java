package com.zinza.server.api;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.DatabaseControls;
import com.zinza.server.service.model.App;
import com.zinza.server.service.model.AppEntity;
import com.zinza.server.service.model.Project;
import com.zinza.server.service.model.ProjectEntity;

@RestController
@RequestMapping("/api")
public class Controller {
	DatabaseControls controls = new DatabaseControls();
	MongoDatabase database = controls.connect();

	// Get all project
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ResponseEntity<?> getProjects() {
		Project project = new Project(database);
		List<ProjectEntity> projects = project.getAllProject();
		return new ResponseEntity<List<ProjectEntity>>(projects, HttpStatus.OK);
	}

	// Get project with id
	@RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProject(@PathVariable("id") String projectId) {
		Project project = new Project(database);
		if (!ObjectId.isValid(projectId))
			return new ResponseEntity<String>("Id invalid format !", HttpStatus.BAD_REQUEST);
		ProjectEntity projectEntity = project.getProject(projectId);

		if (projectEntity == null) {
			return new ResponseEntity<String>("Project not found !", HttpStatus.FORBIDDEN);
		}

		return new ResponseEntity<>(projectEntity, HttpStatus.OK);
	}

	// Create project
	@RequestMapping(value = "/project", method = RequestMethod.PUT)
	public ResponseEntity<?> createProject(@RequestBody ProjectEntity projectEntity, UriComponentsBuilder ucBuilder) {
		Project project = new Project(database);
		if (!project.insertProject(projectEntity)) {
			return new ResponseEntity<String>("Project already exists !", HttpStatus.FORBIDDEN);
		} else {
			return new ResponseEntity<String>("Project created !", HttpStatus.CREATED);
		}
	}

	// Delete project
	@RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProject(@PathVariable("id") String projectId) {
		if (!ObjectId.isValid(projectId))
			return new ResponseEntity<String>("Id invalid format !", HttpStatus.BAD_REQUEST);
		Project project = new Project(database);
		project.deleteProject(projectId);
		return new ResponseEntity<String>("Project deleted !", HttpStatus.OK);
	}

	// Get all app of project
	@RequestMapping(value = "/app/all/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOfProject(@PathVariable("id") String projectId) {
		if (!ObjectId.isValid(projectId))
			return new ResponseEntity<String>("Id invalid format !", HttpStatus.BAD_REQUEST);
		App app = new App(database);
		List<AppEntity> apps = app.getAllAppOfProject(projectId);
		return new ResponseEntity<List<AppEntity>>(apps, HttpStatus.OK);

	}
}
