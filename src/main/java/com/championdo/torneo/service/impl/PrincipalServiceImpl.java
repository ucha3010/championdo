package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PrincipalUserModel;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.Menu1Service;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.util.LoggerMapper;
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
    private InscripcionService inscripcionService;
    @Autowired
    private Menu1Service menu1Service;
    @Autowired
    private UserService userService;

    @Override
    public List<PrincipalUserModel> findByDni(String dni) {

        PrincipalUserModel principalUserModel;
        List<InscripcionModel> inscripcionList = inscripcionService.findByDniInscripto(dni);
        inscripcionList.addAll(inscripcionService.findByDniAutorizador(dni));
        List<PrincipalUserModel> principalUserModelList = new ArrayList<>();
        if (!inscripcionList.isEmpty()) {
            inscripcionList.sort(Comparator.comparing(InscripcionModel::getFechaInscripcion).reversed());
            for (InscripcionModel inscripcionModel : inscripcionList) {
                principalUserModel = new PrincipalUserModel();
                principalUserModel.setId(inscripcionModel.getId());
                principalUserModel.setNombre(inscripcionModel.getNombreInscripto());
                principalUserModel.setApellido1(inscripcionModel.getApellido1Inscripto());
                principalUserModel.setApellido2(inscripcionModel.getApellido2Inscripto());
                principalUserModel.setFechaInscripcion(inscripcionModel.getFechaInscripcion());
                principalUserModel.setNombreTorneo(inscripcionModel.getNombreCampeonato());
                principalUserModel.setFechaTorneo(inscripcionModel.getFechaCampeonato());
                principalUserModel.setInscripcionPropia(inscripcionModel.isInscripcionPropia());
                principalUserModelList.add(principalUserModel);
            }
        }
        LoggerMapper.methodOut(Level.INFO, "findByDni", principalUserModelList, getClass());
        return principalUserModelList;
    }

    @Override
    public void deleteInscripcion(InscripcionModel inscripcionModel) {
        inscripcionService.delete(inscripcionModel);
        LoggerMapper.methodOut(Level.INFO, "deleteInscripcion", inscripcionModel, getClass());
    }

    @Override
    public User cargaBasicaCompleta(ModelAndView modelAndView) {
        com.championdo.torneo.entity.User usuario = userService.getLoggedUser();
        modelAndView.addObject("usuario", usuario);
        modelAndView.addObject("menu1List", menu1Service.findAll());
        return usuario;
    }
}
