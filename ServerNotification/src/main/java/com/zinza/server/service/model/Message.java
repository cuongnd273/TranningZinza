package com.zinza.server.service.model;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.MessageService;

public class Message {
	private MessageService messageService;

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MongoDatabase database) {
		this.messageService = new MessageService(database);
	}

	public void insertMessage(MessageEntity message) {
		this.messageService.inserMessage(message);
	}

	public void insertMessage(String topicId, String appId, boolean sendAll, String sendTo, String content) {
		this.messageService.inserMessage(topicId, appId, sendAll, sendTo, content);
	}

	public MessageEntity getMessage(String messageId) {
		return this.messageService.getMessage(messageId);
	}
}
