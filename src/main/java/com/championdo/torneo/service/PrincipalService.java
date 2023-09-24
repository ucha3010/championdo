package com.championdo.torneo.service;


import com.championdo.torneo.model.PrincipalModel;
import com.championdo.torneo.model.UtilModel;

public interface PrincipalService {

    PrincipalModel findByDni(String dni);

    void deleteInscripcion(int id);

    UtilModel getDeleteEnable(int codigoGimnasio);
}
