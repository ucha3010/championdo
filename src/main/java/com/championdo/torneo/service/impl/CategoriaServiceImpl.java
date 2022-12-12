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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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
    public List<CategoriaModel> findAll() {
        List<CategoriaModel> categoriaModelList = new ArrayList<>();
        for (Categoria categoria: categoriaRepository.findAllByOrderByPositionAsc()) {
            categoriaModelList.add(mapperCategoria.entity2Model(categoria));
        }
        return categoriaModelList;
    }

    @Override
    public List<CategoriaModel> findAllNameExtended() {
        List<CategoriaModel> categoriaModelList = new ArrayList<>();
        CategoriaModel categoriaModel;
        for (Categoria categoria: categoriaRepository.findAllByOrderByPositionAsc()) {
            categoriaModel = mapperCategoria.entity2Model(categoria);
            categoriaModel.setNombre(categoria.getNombre() + (categoria.isInfantil() ? " INFANTIL" : "")
                    + ", edad de " + categoria.getEdadInicio() + " a " + categoria.getEdadFin()
                    + ", cinturón de " + categoriaModel.getCinturonInicio().getColor() + " a " + categoriaModel.getCinturonFin().getColor()
                    + ", poomsae " + categoriaModel.getPoomsae().getNombre());
            categoriaModelList.add(categoriaModel);
        }
        return categoriaModelList;
    }

    @Override
    public CategoriaModel findById(int id) {
        try {
            return mapperCategoria.entity2Model(categoriaRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new CategoriaModel();
        }
    }

    @Override
    public void add(CategoriaModel categoriaModel) {
        categoriaRepository.save(mapperCategoria.model2Entity(categoriaModel));
    }

    @Override
    public void update(CategoriaModel categoriaModel) {
        categoriaRepository.save(mapperCategoria.model2Entity(categoriaModel));
    }

    @Override
    public void delete(int idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        List<Categoria> categoriaList = categoriaRepository.findAllByOrderByPositionAsc();
        for (int i = 0; i < categoriaList.size(); i++) {
            if (categoriaList.get(i).getPosition() != i) {
                categoriaList.get(i).setPosition(i);
                categoriaRepository.save(categoriaList.get(i));
            }
        }
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

    @Override
    public void dragOfPosition(int initialPosition, int finalPosition) {
        Categoria categoria = categoriaRepository.findByPosition(initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(i, false);
            }
        }
        categoria.setPosition(finalPosition);
        categoriaRepository.save(categoria);
    }

    @Override
    public int findMaxPosition() {
        Categoria categoria = categoriaRepository.findTopByOrderByPositionDesc();
        if (categoria != null) {
            return categoria.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int position, boolean moveUp) {
        Categoria categoria = categoriaRepository.findByPosition(position);
        categoria.setPosition(position + (moveUp ? 1 : -1));
        categoriaRepository.save(categoria);
    }
}
