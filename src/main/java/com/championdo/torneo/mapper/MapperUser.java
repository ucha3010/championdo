package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperUser {

/*    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private CalidadService calidadService;*/

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private UserRoleService userRoleService;

    public UserModel entity2Model(User externObject) {
        UserModel localObject = new UserModel();
        if (externObject != null) {
            localObject.setUsername(externObject.getUsername());
            localObject.setPassword(externObject.getPassword());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setName(externObject.getName());
            localObject.setLastname(externObject.getLastname());
            localObject.setSecondLastname(externObject.getSecondLastname());
            localObject.setSexo(externObject.getSexo());
            localObject.setFechaNacimiento(externObject.getFechaNacimiento());
            localObject.setGimnasio(gimnasioService.findById(externObject.getIdGimnasio()));
//            localObject.setCalidad(calidadService.findById(externObject.getIdCalidad()));
            localObject.setPais(paisService.findById(externObject.getIdPais()));
//            localObject.setCinturon(cinturonService.findById(externObject.getIdCinturon()));
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsernameModificacione(externObject.getUsernameModificacione());
            localObject.setCorreo(externObject.getCorreo());
//            localObject.setMenor(externObject.isMenor());
//            localObject.setDniMenor(externObject.getDniMenor());
//            localObject.setUsernameACargo(externObject.getUsernameACargo());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
//            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setTelefono(externObject.getTelefono());
//            localObject.setIdTorneo(externObject.getIdTorneo());
//            localObject.setIdTorneoGimnasio(externObject.getIdTorneoGimnasio());
            localObject.setCodigoGimnasio(localObject.getGimnasio().getCodigoGimnasio());
//            localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
            localObject.setUserRoles(userRoleService.findByUsername(externObject.getUsername()));
        }

        return localObject;
    }

    public User model2Entity(UserModel externObject) {
        User localObject = new User();
        if (externObject != null) {
            localObject.setUsername(externObject.getUsername());
            localObject.setPassword(externObject.getPassword());
            localObject.setEnabled(externObject.isEnabled());
            localObject.setName(externObject.getName());
            localObject.setLastname(externObject.getLastname());
            localObject.setSecondLastname(externObject.getSecondLastname());
            localObject.setSexo(externObject.getSexo());
            localObject.setFechaNacimiento(externObject.getFechaNacimiento());
            if (externObject.getGimnasio() != null) {
                localObject.setIdGimnasio(externObject.getGimnasio().getId());
/*                if (externObject.getGimnasio().getCodigoGimnasio() != 0) {
                    localObject.setCodigoGimnasio(externObject.getGimnasio().getCodigoGimnasio());
                } else {
                    localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
                }*/
            } /*else {*/
//                localObject.setCodigoGimnasio(externObject.getCodigoGimnasio());
/*            }*/
/*            if (externObject.getCalidad() != null) {
                localObject.setIdCalidad(externObject.getCalidad().getId());
            }*/
            if (externObject.getPais() != null) {
                localObject.setIdPais(externObject.getPais().getId());
            }
/*            if (externObject.getCinturon() != null) {
                localObject.setIdCinturon(externObject.getCinturon().getId());
            }*/
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsernameModificacione(externObject.getUsernameModificacione());
            localObject.setCorreo(externObject.getCorreo());
//            localObject.setMenor(externObject.isMenor());
//            localObject.setDniMenor(externObject.getDniMenor());
//            localObject.setUsernameACargo(externObject.getUsernameACargo());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
//            localObject.setInclusivo(externObject.isInclusivo());
            localObject.setTelefono(externObject.getTelefono());
//            localObject.setIdTorneo(externObject.getIdTorneo());
//            localObject.setIdTorneoGimnasio(externObject.getIdTorneoGimnasio());
        }
        return localObject;
    }
}
