package com.championdo.torneo.service;


import com.championdo.torneo.model.*;

import java.util.List;

public interface InscripcionService {//TODO DAMIAN modificar nombre a TournamentRegistrationService

    List<InscripcionModel> findAll();

    InscripcionModel findById(int id);

    List<InscripcionModel> findByDniAutorizador(String dniAutorizador);

    List<InscripcionModel> findByDniInscripto(String dniInscripto);
    List<InscripcionModel> findByIdTorneo(int idTorneo);

    InscripcionModel add(InscripcionModel inscripcionModel);

    InscripcionModel addPropia(UserModel userModel, PdfModel pdfModel, int codigoGimnasio);

    InscripcionModel addMenorOInclusivo(UserAutorizacionModel userAutorizacionModel, PdfModel pdfModel, int codigoGimnasio);

    void update(InscripcionModel inscripcionModel);

    void delete(InscripcionModel inscripcion);

    void deleteByDni(String dni);

    void deleteAll();

    UtilModel getDeleteEnable(int codigoGimnasio);

    void changeValueDeleteEnable(int codigoGimnasio);
}
