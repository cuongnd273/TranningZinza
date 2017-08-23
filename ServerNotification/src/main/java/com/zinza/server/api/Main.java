package com.zinza.server.api;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.DatabaseControls;
import com.zinza.server.service.model.Project;
import com.zinza.server.service.model.ProjectEntity;

public class Main {

	public static void main(String[] args) {
		DatabaseControls databaseControls = new DatabaseControls();
		MongoDatabase database = databaseControls.connect();
		Project project = new Project(database);
		ProjectEntity p=project.getProject("59912c49232ad72afc6bfcc8");
		if(p==null)System.out.println("Khong tim thay");
		else System.out.println("Tim thay");
	}

}
