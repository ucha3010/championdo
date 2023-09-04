package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.FirmaCodigo;
import com.championdo.torneo.model.FirmaCodigoModel;
import org.springframework.stereotype.Component;

@Component
public class MapperFirmaCodigo {

    public FirmaCodigoModel entity2Model(FirmaCodigo externObject) {
        FirmaCodigoModel localObject = new FirmaCodigoModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdOperacion(externObject.getIdOperacion());
            localObject.setFechaCreacion(externObject.getFechaCreacion());
            localObject.setFechaCaducidad(externObject.getFechaCaducidad());
            localObject.setCodigo(externObject.getCodigo());
            localObject.setDni(externObject.getDni());
            localObject.setPaginaFirmaOk(externObject.getPaginaFirmaOk());
            localObject.setOperativaOriginal(externObject.getOperativaOriginal());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
    public FirmaCodigo model2Entity(FirmaCodigoModel externObject) {
        FirmaCodigo localObject = new FirmaCodigo();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setIdOperacion(externObject.getIdOperacion());
            localObject.setFechaCreacion(externObject.getFechaCreacion());
            localObject.setFechaCaducidad(externObject.getFechaCaducidad());
            localObject.setCodigo(externObject.getCodigo());
            localObject.setDni(externObject.getDni());
            localObject.setPaginaFirmaOk(externObject.getPaginaFirmaOk());
            localObject.setOperativaOriginal(externObject.getOperativaOriginal());
            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
        }
        return localObject;
    }
}
