package com.zinza.server.service.model;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;
import com.zinza.server.service.service.TokenService;

public class Token {
	private TokenService tokenService;

	public Token() {
		super();
	}

	public Token(MongoDatabase database) {
		super();
		this.tokenService = new TokenService(database);
	}

	public String createToken(String appId) {
		return this.tokenService.createToken(appId);
	}

	public ArrayList<TokenEntity> getAllTokenOfApp(String appId) {
		return this.tokenService.getAllTokenOfAp(appId);
	}

	public void deleteToken(String tokenId) {
		this.tokenService.deleteToken(tokenId);
	}
}
