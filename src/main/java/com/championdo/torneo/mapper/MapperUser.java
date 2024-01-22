package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.PaisService;
import com.championdo.torneo.service.UserRoleService;
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
            localObject.setPais(paisService.findById(externObject.getIdPais()));
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsernameModificacione(externObject.getUsernameModificacione());
            localObject.setCorreo(externObject.getCorreo());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setTelefono(externObject.getTelefono());
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
            }
            if (externObject.getPais() != null) {
                localObject.setIdPais(externObject.getPais().getId());
            }
            localObject.setFechaAlta(externObject.getFechaAlta());
            localObject.setFechaModificacion(externObject.getFechaModificacion());
            localObject.setUsernameModificacione(externObject.getUsernameModificacione());
            localObject.setCorreo(externObject.getCorreo());
            localObject.setDomicilioCalle(externObject.getDomicilioCalle());
            localObject.setDomicilioNumero(externObject.getDomicilioNumero());
            localObject.setDomicilioOtros(externObject.getDomicilioOtros());
            localObject.setDomicilioLocalidad(externObject.getDomicilioLocalidad());
            localObject.setDomicilioCp(externObject.getDomicilioCp());
            localObject.setTelefono(externObject.getTelefono());
        }
        return localObject;
    }
}
