package com.zinza.server.service.model;
import com.zinza.server.service.service.CollectionMessageService;

public class CollectionMessage {
	private CollectionMessageService collectionMessageService;

	public CollectionMessage() {
		super();
	}

	public void insertCollectionMessage(String collectionName) {
		if (getCollectionMessage(collectionName) != null)
			return;
		this.collectionMessageService.insertCollectionMessage(collectionName);
	}

	public CollectionMessageEntity getCollectionMessage(String collectionName) {
		return this.collectionMessageService.getCollectionMessage(collectionName);
	}
}
