package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PrincipalModel;
import com.championdo.torneo.model.PrincipalUserModel;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private InscripcionService inscripcionService;

    @Override
    public PrincipalModel findByDni(String dni) {

        PrincipalModel principalModel = new PrincipalModel();

        InscripcionModel inscripcionPropia = inscripcionService.findByDniInscripto(dni);
        if (inscripcionPropia != null && inscripcionPropia.getId() != 0) {
            principalModel.setId(inscripcionPropia.getId());
            principalModel.setUsername(inscripcionPropia.getUsuarioInscripto().getUsername());
            principalModel.setFecha(inscripcionPropia.getFechaInscripcion());
        }

        List<InscripcionModel> inscripcionAutorizadaList = inscripcionService.findByDniAutorizador(dni);
        List<PrincipalUserModel> autorizaciones = new ArrayList<>();
        PrincipalUserModel principalUserModel;
        if (!inscripcionAutorizadaList.isEmpty()) {
            for (InscripcionModel inscripcionAutorizada : inscripcionAutorizadaList) {
                principalUserModel = new PrincipalUserModel();
                principalUserModel.setId(inscripcionAutorizada.getId());
                principalUserModel.setNombre(inscripcionAutorizada.getUsuarioInscripto().getName());
                principalUserModel.setApellido1(inscripcionAutorizada.getUsuarioInscripto().getLastname());
                principalUserModel.setApellido2(inscripcionAutorizada.getUsuarioInscripto().getSecondLastname());
                principalUserModel.setFecha(inscripcionAutorizada.getFechaInscripcion());
                autorizaciones.add(principalUserModel);
            }
            principalModel.setAutorizaciones(autorizaciones);
        }
        LoggerMapper.log(Level.INFO, "findByDni", principalModel, getClass());
        return principalModel;
    }

    @Override
    public void deleteInscripcion(int id) {
        inscripcionService.delete(id);
        LoggerMapper.log(Level.INFO, "deleteInscripcion", "id: " + id, getClass());
    }
}
