package com.zinza.server.service.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.database.Constants;
import com.zinza.server.service.model.TokenEntity;

public class TokenService implements TokenServiceInterface {
	private MongoDatabase database;

	public TokenService(MongoDatabase database) {
		super();
		this.database = database;
	}

	@Override
	public String createToken(String appId) {
		String token = "";
		if (database != null) {
			// Create a token key
			ObjectId objectId = new ObjectId();
			token = appId + objectId.toString();
			// Get time current
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOKEN);
			Document document = new Document();
			document.append(Constants.TOKEN_KEY_APP_ID, appId);
			document.append(Constants.TOKEN_KEY_TOKEN, token);
			document.append(Constants.TOKEN_KEY_TIME_CREATED, dtf.format(now));
			document.append(Constants.TOKEN_KEY_DELETE, false);
			collection.insertOne(document);
		}
		return token;
	}

	@Override
	public void deleteToken(String tokenId) {
		if (database != null) {
			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOKEN);
			BasicDBObject where = new BasicDBObject(Constants.TOPIC_KEY_ID, new ObjectId(tokenId));
			Document clause = new Document("$set", new Document(Constants.TOKEN_KEY_DELETE, true));
			collection.updateOne(where, clause);
		}
	}

	@Override
	public ArrayList<TokenEntity> getAllTokenOfAp(String appId) {
		ArrayList<TokenEntity> list = null;
		if (database != null) {
			list = new ArrayList<>();

			MongoCollection<Document> collection = database.getCollection(Constants.COLLECTION_TOKEN);
			BasicDBObject object = new BasicDBObject();
			object.append(Constants.TOKEN_KEY_APP_ID, appId);
			object.append(Constants.TOKEN_KEY_DELETE, false);
			FindIterable<Document> iterable = collection.find(object);

			for (Document document : iterable) {
				TokenEntity token = new TokenEntity();
				token.setAppId(document.getString(Constants.TOKEN_KEY_APP_ID));
				token.setTokenId(document.getObjectId(Constants.TOKEN_KEY_ID).toString());
				token.setToken(document.getString(Constants.TOKEN_KEY_TOKEN));
				token.setTimeCreated(document.getString(Constants.TOKEN_KEY_TIME_CREATED));
				list.add(token);
			}
		}
		return list;
	}

}
