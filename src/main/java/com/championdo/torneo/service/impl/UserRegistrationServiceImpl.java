package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.repository.InscripcionTaekwondoRepository;
import com.championdo.torneo.service.UserRegistrationService;
import com.championdo.torneo.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private InscripcionTaekwondoRepository inscripcionTaekwondoRepository;
    //INFORMACIÓN cada vez que se agregue una actividad para inscribirse, hay que agregarla en este Service

    @Autowired
    private UserService userService;

    @Override
    public List<UserModel> findAll() {
        List<UserModel> userModelList = new ArrayList<>();
        fillUsers(userModelList, inscripcionTaekwondoRepository.findAllByOrderByMayorApellido1Desc(), Constantes.ACTIVITY_TAEKWONDO);
        return sortList(userModelList);
    }
    @Override
    public List<UserModel> findByActivity(String activity) {
        List<UserModel> userModelList = new ArrayList<>();
        if(Constantes.ACTIVITY_TAEKWONDO.equalsIgnoreCase(activity)) {
            fillUsers(userModelList, inscripcionTaekwondoRepository.findAllByOrderByMayorApellido1Desc(), Constantes.ACTIVITY_TAEKWONDO);
        }
        return sortList(userModelList);
    }
    @Override
    public List<UserModel> findByGym(int idGym) {
        List<UserModel> userModelList = new ArrayList<>();
        fillUsers(userModelList, inscripcionTaekwondoRepository.findByCodigoGimnasioOrderByMayorApellido1Desc(idGym), Constantes.ACTIVITY_TAEKWONDO);
        return sortList(userModelList);
    }
    @Override
    public List<UserModel> findByActivityAndGym(String activity, int idGym) {
        List<UserModel> userModelList = new ArrayList<>();
        if(Constantes.ACTIVITY_TAEKWONDO.equalsIgnoreCase(activity)) {
            fillUsers(userModelList, inscripcionTaekwondoRepository.findByCodigoGimnasioOrderByMayorApellido1Desc(idGym), Constantes.ACTIVITY_TAEKWONDO);
        }
        return sortList(userModelList);
    }
    @Override
    public List<UtilModel> getActivities() {
        List<UtilModel> utilModelList = new ArrayList<>();
        utilModelList.add(new UtilModel("","Borrar filtro"));
        utilModelList.add(new UtilModel(Constantes.ACTIVITY_TAEKWONDO, Constantes.ACTIVITY_TAEKWONDO));
        return utilModelList;
    }

    private void fillUsers(List<UserModel> userModelList, List<InscripcionTaekwondo> inscripcionTaekwondoList, String registration) {
        UserModel userModelAux;
        for (InscripcionTaekwondo inscripcionTaekwondo : inscripcionTaekwondoList) {
            try {
                UserModel userModel = userService.findModelByUsername(inscripcionTaekwondo.getMayorDni());
                if (inscripcionTaekwondo.getMayorCalidad() == null) {
                    userModelAux = userModel;
                } else {
                    userModelAux = new UserModel(inscripcionTaekwondo.getAutorizadoNombre(), inscripcionTaekwondo.getAutorizadoApellido1(),
                            inscripcionTaekwondo.getAutorizadoApellido2(), inscripcionTaekwondo.getAutorizadoFechaNacimiento(), true,
                            inscripcionTaekwondo.getMayorDni());
                }
                userModelAux.setGymName(inscripcionTaekwondo.getNombreGimnasio());
                userModelAux.getRegistrations().add(registration);
                userModelList.add(userModelAux);
            } catch (NoResultException nre) {
                // mayor dado de baja del sistema
            }
        }
    }

    private List<UserModel> sortList(List<UserModel> userModelList) {
        List<UserModel> userModelListSorted = new ArrayList<>();
        UserModel userModelAux;
        boolean auxValid;
        for (UserModel userModel1: userModelList) {
            userModelAux = null;
            auxValid = true;
            for (UserModel userModel2 : userModelListSorted){
                if (userModel1.isMenor()) {
                    if (userModel2.isMenor() && userModel1.getUsernameACargo().equals(userModel2.getUsernameACargo())
                            && userModel1.getName().equals(userModel2.getName())
                            && userModel1.getLastname().equals(userModel2.getLastname())
                            && userModel1.getSecondLastname().equals(userModel2.getSecondLastname())) {
                        if (!userModel2.getRegistrations().contains(userModel1.getRegistrations().get(0))) {
                            userModel2.getRegistrations().add(userModel1.getRegistrations().get(0)); //agrego la actividad al usuario
                            auxValid = false;
                            userModelAux = null;
                        }
                    } else if(auxValid){
                        userModelAux = userModel1;
                    }
                } else {
                    if (!userModel2.isMenor() && userModel1.getUsername().equals(userModel2.getUsername())) {
                        if (!userModel2.getRegistrations().contains(userModel1.getRegistrations().get(0))) {
                            userModel2.getRegistrations().add(userModel1.getRegistrations().get(0)); //agrego la actividad al usuario
                            auxValid = false;
                            userModelAux = null;
                        }
                    } else if(auxValid) {
                        userModelAux = userModel1;
                    }
                }
            }
            if (userModelList.get(0) == userModel1) {
                userModelListSorted.add(userModel1);
            } else if(userModelAux != null) {
                userModelListSorted.add(userModelAux);
            }
        }
        return userModelListSorted;
    }
}
