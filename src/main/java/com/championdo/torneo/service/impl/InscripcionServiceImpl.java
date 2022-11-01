package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Inscripcion;
import com.championdo.torneo.mapper.MapperInscripcion;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.InscripcionRepository;
import com.championdo.torneo.service.CategoriaService;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service()
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private MapperInscripcion mapperInscripcion;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public InscripcionModel findById(int id) {
        return mapperInscripcion.entity2Model(inscripcionRepository.getById(id));
    }

    @Override
    public List<InscripcionModel> findByDniAutorizador(String dniAutorizador) {
        List<Inscripcion> inscripcionList = inscripcionRepository.findByDniAutorizador(dniAutorizador);
        List<InscripcionModel> inscripcionModelList = new ArrayList<>();
        if (inscripcionList != null) {
            for (Inscripcion inscripcion : inscripcionList) {
                inscripcionModelList.add(mapperInscripcion.entity2Model(inscripcion));
            }
        }
        LoggerMapper.log(Level.INFO, "findByDniAutorizador", inscripcionList, getClass());
        return inscripcionModelList;
    }

    @Override
    public InscripcionModel findByDniInscripto(String dniInscripto) {
        InscripcionModel inscripcion = mapperInscripcion.entity2Model(inscripcionRepository.findByDniInscripto(dniInscripto));
        LoggerMapper.log(Level.INFO, "findByDniInscripto", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public InscripcionModel add(InscripcionModel inscripcionModel) {
        inscripcionModel.setFechaInscripcion(new Date());
        inscripcionModel.setCategoria(categoriaService.calcularCategoria(inscripcionModel.getUsuarioInscripto()));
        InscripcionModel inscripcion = mapperInscripcion.entity2Model(inscripcionRepository.save(mapperInscripcion.model2Entity(inscripcionModel)));
        LoggerMapper.log(Level.INFO, "add", inscripcion, getClass());
        return inscripcion;
    }

    @Override
    public InscripcionModel addPropia(UserModel userModel) {
        InscripcionModel inscripcionModel = new InscripcionModel();
        inscripcionModel.setInscripcionPropia(true);
        inscripcionModel.setUsuarioAutorizador(null);
        inscripcionModel.setUsuarioInscripto(userModel);
        return add(inscripcionModel);
    }

    @Override
    public InscripcionModel addMenor(UserModel usuarioAutorizador, UserModel usuarioInscripto, boolean menorNoPreinfantil) {
        InscripcionModel inscripcionModel = new InscripcionModel();
        inscripcionModel.setInscripcionMenor(menorNoPreinfantil);
        inscripcionModel.setUsuarioAutorizador(usuarioAutorizador);
        inscripcionModel.setUsuarioInscripto(usuarioInscripto);
        return add(inscripcionModel);
    }

    @Override
    public InscripcionModel addInclusivo(UserModel usuarioAutorizador, UserModel usuarioInscripto) {
        InscripcionModel inscripcionModel = new InscripcionModel();
        inscripcionModel.setInscripcionInclusiva(true);
        inscripcionModel.setUsuarioAutorizador(usuarioAutorizador);
        inscripcionModel.setUsuarioInscripto(usuarioInscripto);
        return add(inscripcionModel);
    }

    @Override
    public void update(InscripcionModel inscripcionModel) {

    }

    @Override
    public void delete(int idInscripcion) {
        Inscripcion inscripcion = inscripcionRepository.getById(idInscripcion);
        if (inscripcion != null) {
            inscripcionRepository.delete(inscripcion);
        }
        LoggerMapper.log(Level.INFO, "delete", inscripcion, getClass());
    }
}
