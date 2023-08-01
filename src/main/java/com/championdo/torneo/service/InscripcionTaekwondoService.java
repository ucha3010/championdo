package com.championdo.torneo.service;


import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.UserAutorizacionModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface InscripcionTaekwondoService {

    List<InscripcionTaekwondoModel> findAll();

    InscripcionTaekwondoModel findById(int id);

    List<InscripcionTaekwondoModel> findByMayorDni(String mayorDni);

    InscripcionTaekwondoModel add(InscripcionTaekwondoModel inscripcionModel);

    InscripcionTaekwondoModel add(UserAutorizacionModel userAutorizacionModel);

    void update(InscripcionTaekwondoModel inscripcionModel);

    void delete(int idInscripcion);

    void deleteAll();

    UtilModel getDeleteEnable();

    boolean changeValueDeleteEnable();
}
