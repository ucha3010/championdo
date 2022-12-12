package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Util;
import com.championdo.torneo.model.UtilModel;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {
    public UtilModel entity2Model(Util externObject) {
        UtilModel localObject = new UtilModel();
        localObject.setClave(externObject.getClave());
        localObject.setValor(externObject.getValor());
        return localObject;
    }
    public Util model2Entity(UtilModel externObject) {
        Util localObject = new Util();
        localObject.setClave(externObject.getClave());
        localObject.setValor(externObject.getValor());
        return localObject;
    }
}
