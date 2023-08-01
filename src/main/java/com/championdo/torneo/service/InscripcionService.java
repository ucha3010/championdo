package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface InscripcionService {

    List<InscripcionModel> findAll();

    InscripcionModel findById(int id);

    List<InscripcionModel> findByDniAutorizador(String dniAutorizador);

    InscripcionModel findByDniInscripto(String dniInscripto);

    InscripcionModel add(InscripcionModel inscripcionModel);

    InscripcionModel addPropia(UserModel userModel);

    InscripcionModel addMenorOInclusivo(UserAutorizacionModel userAutorizacionModel);

    void update(InscripcionModel inscripcionModel);

    void delete(int idInscripcion);

    void deleteAll();

    UtilModel getDeleteEnable();

    void changeValueDeleteEnable();
}
