package com.championdo.torneo.service;


import com.championdo.torneo.model.PrincipalUserModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface PrincipalService {

    List<PrincipalUserModel> findByDni(String dni);

    void deleteInscripcion(int id);

    UtilModel getDeleteEnable(int codigoGimnasio);
}
