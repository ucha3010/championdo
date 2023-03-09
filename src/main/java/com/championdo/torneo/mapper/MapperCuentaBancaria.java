package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.CuentaBancaria;
import com.championdo.torneo.model.CuentaBancariaModel;
import org.springframework.stereotype.Component;

@Component
public class MapperCuentaBancaria {

    public CuentaBancariaModel entity2Model(CuentaBancaria externObject) {
        CuentaBancariaModel localObject = new CuentaBancariaModel();
        localObject.setId(externObject.getId());
        localObject.setTitular(externObject.getTitular());
        localObject.setIban(externObject.getIban());
        localObject.setSwift(externObject.getSwift());
        return localObject;
    }
    public CuentaBancaria model2Entity(CuentaBancariaModel externObject) {
        CuentaBancaria localObject = new CuentaBancaria();
        localObject.setId(externObject.getId());
        localObject.setTitular(externObject.getTitular());
        localObject.setIban(externObject.getIban());
        localObject.setSwift(externObject.getSwift());
        return localObject;
    }
}
