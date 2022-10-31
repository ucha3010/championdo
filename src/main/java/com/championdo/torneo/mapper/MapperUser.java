package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private CalidadService calidadService;

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private UserRoleService userRoleService;

    public UserModel entity2Model(User externObject) {
        UserModel localObject = new UserModel();
        localObject.setUsername(externObject.getUsername());
        localObject.setPassword(externObject.getPassword());
        localObject.setEnabled(externObject.isEnabled());
        localObject.setName(externObject.getName());
        localObject.setLastname(externObject.getLastname());
        localObject.setSecondLastname(externObject.getSecondLastname());
        localObject.setSexo(externObject.getSexo());
        localObject.setFechaNacimiento(externObject.getFechaNacimiento());
        localObject.setGimnasio(gimnasioService.findById(externObject.getIdGimnasio()));
        localObject.setCalidad(calidadService.findById(externObject.getIdCalidad()));
        localObject.setPais(paisService.findById(externObject.getIdPais()));
        localObject.setCinturon(cinturonService.findById(externObject.getIdCinturon()));
        localObject.setFechaAlta(externObject.getFechaAlta());
        localObject.setFechaModificacion(externObject.getFechaModificacion());
        localObject.setUsernameModificacione(externObject.getUsernameModificacione());
        localObject.setCorreo(externObject.getCorreo());
        localObject.setMenor(externObject.isMenor());
        localObject.setDniMenor(externObject.getDniMenor());
        localObject.setUsernameACargo(externObject.getUsernameACargo());
        localObject.setDomicilioCalle(externObject.getDomicilioCalle());
        localObject.setDomicilioNumero(externObject.getDomicilioNumero());
        localObject.setDomicilioOtros(externObject.getDomicilioOtros());
        localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
        localObject.setDomicilioCp(externObject.getDomicilioCp());
        localObject.setInclusivo(externObject.isInclusivo());
        localObject.setUserRoles(userRoleService.findByUsername(externObject.getUsername()));

        return localObject;
    }
}
