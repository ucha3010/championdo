package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.TournamentRegistrationModel;
import com.championdo.torneo.model.PrincipalUserModel;
import com.championdo.torneo.service.TournamentRegistrationService;
import com.championdo.torneo.service.Menu1Service;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service()
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private TournamentRegistrationService tournamentRegistrationService;
    @Autowired
    private Menu1Service menu1Service;
    @Autowired
    private UserService userService;

    @Override
    public List<PrincipalUserModel> findByDni(String dni) {

        PrincipalUserModel principalUserModel;
        List<TournamentRegistrationModel> inscripcionList = tournamentRegistrationService.findByRegisteredIdCard(dni);
        inscripcionList.addAll(tournamentRegistrationService.findByAuthorizerIdCard(dni));
        List<PrincipalUserModel> principalUserModelList = new ArrayList<>();
        if (!inscripcionList.isEmpty()) {
            inscripcionList.sort(Comparator.comparing(TournamentRegistrationModel::getRegistrationDate).reversed());
            for (TournamentRegistrationModel tournamentRegistrationModel : inscripcionList) {
                principalUserModel = new PrincipalUserModel();
                principalUserModel.setId(tournamentRegistrationModel.getId());
                principalUserModel.setNombre(tournamentRegistrationModel.getRegisteredName());
                principalUserModel.setApellido1(tournamentRegistrationModel.getRegistered1Lastname());
                principalUserModel.setApellido2(tournamentRegistrationModel.getRegistered2Lastname());
                principalUserModel.setFechaInscripcion(tournamentRegistrationModel.getRegistrationDate());
                principalUserModel.setNombreTorneo(tournamentRegistrationModel.getTournamentName());
                principalUserModel.setFechaTorneo(tournamentRegistrationModel.getTournamentDate());
                principalUserModel.setInscripcionPropia(tournamentRegistrationModel.isRegistrationAdult());
                principalUserModelList.add(principalUserModel);
            }
        }
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), principalUserModelList, getClass());
        return principalUserModelList;
    }

    @Override
    public void deleteTournamentRegistration(TournamentRegistrationModel tournamentRegistrationModel) {
        tournamentRegistrationService.delete(tournamentRegistrationModel);
        LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), tournamentRegistrationModel, getClass());
    }

    @Override
    public User cargaBasicaCompleta(ModelAndView modelAndView) {
        com.championdo.torneo.entity.User usuario = userService.getLoggedUser();
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("menu1List", menu1Service.findAll());
        return usuario;
    }
}
