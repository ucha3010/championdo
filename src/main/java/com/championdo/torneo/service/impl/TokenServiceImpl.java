package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Token;
import com.championdo.torneo.mapper.MapperToken;
import com.championdo.torneo.model.TokenModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.TokenRepository;
import com.championdo.torneo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service()
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MapperToken mapperToken;

    @Override
    public TokenModel findById(String id) {
        return mapperToken.entity2Model(tokenRepository.findById(id));
    }

    @Override
    public void deleteExpired() {
        List<Token> tokenList = tokenRepository.findAllByOrderByExpirationAsc();
        if (tokenList != null) {
            for (Token token : tokenList) {
                if (isExpired(token.getExpiration())) {
                    tokenRepository.delete(token);
                }
            }
        }
    }

    @Override
    public void add(TokenModel tokenModel) {
        tokenRepository.save(mapperToken.model2Entity(tokenModel));
    }

    @Override
    public void update(TokenModel tokenModel) {
        tokenRepository.save(mapperToken.model2Entity(tokenModel));
    }

    @Override
    public void delete(String id) {
        tokenRepository.delete(tokenRepository.findById(id));
    }

    @Override
    public boolean isExpired(Date expired) {
        return expired.before(new Date());
    }

    @Override
    public TokenModel fillTokenToSend(TokenModel tokenModel, UserModel userModel) {
        TokenModel tokenModelSend = new TokenModel();
        tokenModelSend.setId(tokenModel.getId());
        tokenModelSend.setUsername(userModel.getUsername());
        tokenModelSend.setName(userModel.getName());
        tokenModelSend.setLastname(userModel.getLastname());
        tokenModelSend.setSecondLastname(userModel.getSecondLastname());
        return tokenModelSend;
    }
}
