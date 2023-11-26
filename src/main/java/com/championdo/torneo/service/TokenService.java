package com.championdo.torneo.service;

import com.championdo.torneo.model.TokenModel;
import com.championdo.torneo.model.UserModel;

import java.util.Date;

public interface TokenService {

    TokenModel findById(String id);

    void deleteExpired();

    void add(TokenModel tokenModel);

    void update(TokenModel tokenModel);

    void delete(String id);

    boolean isExpired(Date expired);

    TokenModel fillTokenToSend(TokenModel tokenModel, UserModel userModel);
}
