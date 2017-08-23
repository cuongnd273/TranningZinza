package com.zinza.server.service.service;

import com.zinza.server.service.model.MessageEntity;

public interface MessageServiceInterface {
	void inserMessage(MessageEntity message);
	void inserMessage(String topicId,String appId,boolean sendAll,String sendTo,String content);
	MessageEntity getMessage(String messageId);
}
