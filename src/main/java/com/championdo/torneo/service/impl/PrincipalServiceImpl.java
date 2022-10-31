package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.PrincipalModel;
import com.championdo.torneo.model.PrincipalUserModel;
import com.championdo.torneo.repository.InscripcionRepository;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.service.PrincipalService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service()
public class PrincipalServiceImpl implements PrincipalService {

    @Autowired
    private InscripcionService inscripcionService;

    @Override
    public PrincipalModel findByUsername(String username) {

        PrincipalModel principalModel = null;
        List<InscripcionModel> inscripcionList = inscripcionService.findByUsername(username);
        List<PrincipalUserModel> autorizaciones = new ArrayList<>();
        PrincipalUserModel principalUserModel;

        if (!inscripcionList.isEmpty()) {
            principalModel = new PrincipalModel();
            for (InscripcionModel inscripcion : inscripcionList) {
                if (inscripcion.getUsuario().getUsername().equals(inscripcion.getUsuarioInscripto().getUsername())) {
                    principalModel.setUsername(inscripcion.getUsuario().getUsername());
                    principalModel.setId(inscripcion.getId());
                    principalModel.setFecha(inscripcion.getFechaInscripcion());
                } else {
                    principalUserModel = new PrincipalUserModel();
                    principalUserModel.setId(inscripcion.getId());
                    principalUserModel.setNombre(inscripcion.getUsuarioInscripto().getName());
                    principalUserModel.setApellido1(inscripcion.getUsuarioInscripto().getLastname());
                    principalUserModel.setApellido2(inscripcion.getUsuarioInscripto().getSecondLastname());
                    principalUserModel.setFecha(inscripcion.getFechaInscripcion());

                    autorizaciones.add(principalUserModel);
                }
            }

            principalModel.setAutorizaciones(autorizaciones);
        }
        return principalModel;
    }

    @Override
    public void deleteInscripcion(int id) {
        inscripcionService.delete(id);
    }
}
