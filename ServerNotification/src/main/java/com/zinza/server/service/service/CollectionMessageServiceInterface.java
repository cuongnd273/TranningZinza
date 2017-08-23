package com.zinza.server.service.service;

import com.zinza.server.service.model.CollectionMessageEntity;

public interface CollectionMessageServiceInterface {
	void insertCollectionMessage(String collectionName);
	CollectionMessageEntity getCollectionMessage(String collectionName);
}
