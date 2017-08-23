package com.zinza.server.service.database;

public class Constants {
	public static final String HOST = "ds127883.mlab.com";
	public static final int PORT = 27883;
	public static final String DATABASE = "message_publish";
	public static final String USER = "admin";
	public static final String PASS = "admin";
	// Collection project
	public static final String COLLECTION_PROJECT = "project";
	public static final String PROJECT_KEY_ID = "_id";
	public static final String PROJECT_KEY_NAME = "name";
	public static final String PROJECT_KEY_TIME_CREATED = "time_created";
	public static final String PROJECT_KEY_DELETE = "isDelete";
	// Collection app
	public static final String COLLECTION_APP = "app";
	public static final String APP_KEY_PROJECT_ID = "project_id";
	public static final String APP_KEY_ID = "_id";
	public static final String APP_KEY_NAME = "name";
	public static final String APP_KEY_API = "api_key";
	public static final String APP_KEY_TIME_CREATED = "time_created";
	public static final String APP_KEY_DELETE = "isDelete";
	// Collection token
	public static final String COLLECTION_TOKEN = "token";
	public static final String TOKEN_KEY_ID = "_id";
	public static final String TOKEN_KEY_APP_ID = "app_id";
	public static final String TOKEN_KEY_TOKEN = "token_key";
	public static final String TOKEN_KEY_TIME_CREATED = "time_created";
	public static final String TOKEN_KEY_DELETE = "isDelete";
	// Collection topic
	public static final String COLLECTION_TOPIC = "topic";
	public static final String TOPIC_KEY_ID = "_id";
	public static final String TOPIC_KEY_NAME = "name";
	public static final String TOPIC_KEY_DELETE = "isDelete";
	// Collection app_topic
	public static final String COLLECTION_APP_TOPIC = "app_topic";
	public static final String APP_TOPIC_KEY_ID = "_id";
	public static final String APP_TOPIC_KEY_APP_ID = "app_id";
	public static final String APP_TOPIC_KEY_TOPIC_ID = "topic_id";
	public static final String APP_TOPIC_KEY_DELETE = "isDelete";
	// Collection name of collection message
	public static final String COLLECTION_COLLECTION_MESSAGE = "message";
	public static final String COLLECTION_MESSAGE_KEY_ID = "_id";
	public static final String COLLECTION_MESSAGE_KEY_NAME = "name";
	// Collection message
	public static final String COLLECTION_MESSAGE = "message_";
	public static final String MESSAGE_KEY_ID = "_id";
	public static final String MESSAGE_KEY_TOPIC_ID = "topic_id";
	public static final String MESSAGE_KEY_APP_ID = "app_id";
	public static final String MESSAGE_KEY_SEND_ALL = "send_all";
	public static final String MESSAGE_KEY_SEND_TO = "send_to";
	public static final String MESSAGE_KEY_CONTENT = "content";
	public static final String MESSAGE_KEY_COLLECTION_NAME = "collection_name";
	public static final String MESSAGE_TIME_CREATED = "time_created";
}
