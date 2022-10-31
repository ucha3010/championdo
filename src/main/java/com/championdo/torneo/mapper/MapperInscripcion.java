package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.Inscripcion;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.service.CategoriaService;
import com.championdo.torneo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperInscripcion {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UserService userService;

    public InscripcionModel entity2Model(Inscripcion externObject) {
        InscripcionModel localObject = new InscripcionModel();
        localObject.setId(externObject.getId());
        localObject.setFechaInscripcion(externObject.getFechaInscripcion());
        localObject.setUsuario(userService.findModelByUsername(externObject.getUsername()));
        localObject.setUsuarioInscripto(userService.findModelByUsername(externObject.getUsernameInscripto()));
        localObject.setEnvioJustificanteEmail(externObject.isEnvioJustificanteEmail());
        localObject.setCategoria(categoriaService.findById(externObject.getIdCategoria()));
        localObject.setPagoRealizado(externObject.isPagoRealizado());
        localObject.setNotas(externObject.getNotas());
        localObject.setFechaPago(externObject.getFechaPago());
        return localObject;
    }

    public Inscripcion model2Entity(InscripcionModel externObject) {
        Inscripcion localObject = new Inscripcion();
        localObject.setId(externObject.getId());
        localObject.setFechaInscripcion(externObject.getFechaInscripcion());
        localObject.setUsername(externObject.getUsuario().getUsername());
        localObject.setUsernameInscripto(externObject.getUsuarioInscripto().getUsername());
        localObject.setEnvioJustificanteEmail(externObject.isEnvioJustificanteEmail());
        localObject.setIdCategoria(externObject.getCategoria().getId());
        localObject.setPagoRealizado(externObject.isPagoRealizado());
        localObject.setNotas(externObject.getNotas());
        localObject.setFechaPago(externObject.getFechaPago());
        return localObject;
    }
}
