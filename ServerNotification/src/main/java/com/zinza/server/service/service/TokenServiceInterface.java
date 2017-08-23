package com.zinza.server.service.service;

import java.util.ArrayList;

import com.zinza.server.service.model.TokenEntity;

public interface TokenServiceInterface {

	String createToken(String appId);

	void deleteToken(String tokenId);

	ArrayList<TokenEntity> getAllTokenOfAp(String appId);
}
