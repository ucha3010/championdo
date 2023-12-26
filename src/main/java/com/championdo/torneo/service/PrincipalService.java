package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PrincipalUserModel;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PrincipalService {

    List<PrincipalUserModel> findByDni(String dni);

    void deleteInscripcion(InscripcionModel inscripcionModel);

    User cargaBasicaCompleta(ModelAndView modelAndView);
}
