package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Token;
import com.championdo.torneo.model.TokenModel;
import org.springframework.stereotype.Component;

@Component
public class MapperToken {

    public TokenModel entity2Model(Token externObject) {
        TokenModel localObject = new TokenModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setUsername(externObject.getUsername());
            localObject.setExpiration(externObject.getExpiration());
            localObject.setAttempts(externObject.getAttempts());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public Token model2Entity(TokenModel externObject) {
        Token localObject = new Token();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setUsername(externObject.getUsername());
            localObject.setExpiration(externObject.getExpiration());
            localObject.setAttempts(externObject.getAttempts());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
