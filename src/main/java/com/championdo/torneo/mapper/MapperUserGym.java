package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.UserGym;
import com.championdo.torneo.model.UserGymModel;
import org.springframework.stereotype.Component;

@Component
public class MapperUserGym {

    public UserGymModel entity2Model(UserGym externObject) {
        UserGymModel localObject = new UserGymModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setUsername(externObject.getUsername());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setUsernameAdd(externObject.getUsernameAdd());
            localObject.setDateAdd(externObject.getDateAdd());
        }
        return localObject;
    }
    public UserGym model2Entity(UserGymModel externObject) {
        UserGym localObject = new UserGym();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setUsername(externObject.getUsername());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setUsernameAdd(externObject.getUsernameAdd());
            localObject.setDateAdd(externObject.getDateAdd());
        }
        return localObject;
    }
}
