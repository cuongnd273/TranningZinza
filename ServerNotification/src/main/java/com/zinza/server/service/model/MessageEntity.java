package com.zinza.server.service.model;

public class MessageEntity {
	private String messageId;
	private String topicId;
	private String appId;
	private boolean sendAll;
	private String sendTo;
	private String content;
	private String timeCreated;
	private String collectionName;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public boolean isSendAll() {
		return sendAll;
	}
	public void setSendAll(boolean sendAll) {
		this.sendAll = sendAll;
	}
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
}
