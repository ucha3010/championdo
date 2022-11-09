package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.mapper.MapperCategoria;
import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.service.CategoriaService;
import com.championdo.torneo.util.Constantes;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service()
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MapperCategoria mapperCategoria;

    @Override
    public CategoriaModel findById(int id) {
        return mapperCategoria.entity2Model(categoriaRepository.getById(id));
    }

    @Override
    public void add(CategoriaModel categoriaModel) {

    }

    @Override
    public void update(CategoriaModel categoriaModel) {

    }

    @Override
    public void delete(int idCategoria) {

    }

    @Override
    public CategoriaModel calcularCategoria(UserModel usuarioInscripto) {
        Categoria categoria;
        if(usuarioInscripto.isInclusivo()) {
            categoria = categoriaRepository.findByNombre(Constantes.INCLUSIVO);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(usuarioInscripto.getFechaNacimiento());
            int anioNacimiento = calendar.get(Calendar.YEAR);
            calendar.setTime(new Date());
            int anioActual = calendar.get(Calendar.YEAR);
            int edad = anioActual - anioNacimiento;
            if ((edad <= 6) ||
                    (edad <= 8 &&
                            (!StringUtils.isNullOrEmpty(usuarioInscripto.getMenorEntreCategorias())
                             && usuarioInscripto.getMenorEntreCategorias().equalsIgnoreCase("Kicho")))) {
                categoria = categoriaRepository.findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilFalse(edad, edad);
            } else {
                int idCinturon = usuarioInscripto.getCinturon().getId();
                categoria = categoriaRepository.findByEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndIdCinturonInicioLessThanEqualAndIdCinturonFinGreaterThanEqualAndInfantil(edad, edad, idCinturon, idCinturon, usuarioInscripto.isMenor());
            }
        }
        return mapperCategoria.entity2Model(categoria);
    }
}
