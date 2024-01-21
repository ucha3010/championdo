package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.UtilManager;
import com.championdo.torneo.model.UtilManagerModel;
import org.springframework.stereotype.Component;

@Component
public class MapperUtilManager {

    public UtilManagerModel entity2Model(UtilManager externObject) {
        UtilManagerModel localObject = new UtilManagerModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setHostPageName(externObject.getHostPageName());
            localObject.setEmail(externObject.getEmail());
            localObject.setPassword(externObject.getPassword());
            localObject.setEmailHost(externObject.getEmailHost());
            localObject.setEmailPort(externObject.getEmailPort());
        }
        return localObject;
    }
    public UtilManager model2Entity(UtilManagerModel externObject) {
        UtilManager localObject = new UtilManager();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setHostPageName(externObject.getHostPageName());
            localObject.setEmail(externObject.getEmail());
            localObject.setPassword(externObject.getPassword());
            localObject.setEmailHost(externObject.getEmailHost());
            localObject.setEmailPort(externObject.getEmailPort());
        }
        return localObject;
    }
}
