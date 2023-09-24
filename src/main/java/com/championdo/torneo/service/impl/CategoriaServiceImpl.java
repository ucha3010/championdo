package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.mapper.MapperCategoria;
import com.championdo.torneo.model.CategoriaModel;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.service.CategoriaService;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.service.PoomsaeService;
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

    @Autowired
    private CinturonService cinturonService;

    @Autowired
    private PoomsaeService poomsaeService;

    @Override
    public List<CategoriaModel> findAll(int codigoGimnasio) {
        List<CategoriaModel> categoriaModelList = new ArrayList<>();
        for (Categoria categoria: categoriaRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio)) {
            categoriaModelList.add(mapperCategoria.entity2Model(categoria));
        }
        return categoriaModelList;
    }

    @Override
    public List<CategoriaModel> findAllNameExtended(int codigoGimnasio) {
        List<CategoriaModel> categoriaModelList = findAll(codigoGimnasio);
        for (CategoriaModel categoriaModel: categoriaModelList) {
            categoriaModel.setNombre(categoriaModel.getNombre() + (categoriaModel.isInfantil() ? " INFANTIL" : "")
                    + ", edad de " + categoriaModel.getEdadInicio() + " a " + categoriaModel.getEdadFin()
                    + ", cinturón de " + categoriaModel.getCinturonInicio().getColor() + " a " + categoriaModel.getCinturonFin().getColor()
                    + ", poomsae " + categoriaModel.getPoomsae().getNombre());
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
        CategoriaModel categoriaModel = findById(idCategoria);
        categoriaRepository.deleteById(idCategoria);
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasioOrderByPositionAsc(categoriaModel.getCodigoGimnasio());
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
            categoria = categoriaRepository.findByCodigoGimnasioAndNombre(usuarioInscripto.getCodigoGimnasio(), Constantes.INCLUSIVO);
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
                categoria = categoriaRepository.findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndInfantilFalse(usuarioInscripto.getCodigoGimnasio(), edad, edad);
            } else {
                int positionCinturon = usuarioInscripto.getCinturon().getPosition();
                categoria = categoriaRepository.findByCodigoGimnasioAndEdadInicioLessThanEqualAndEdadFinGreaterThanEqualAndPositionCinturonInicioLessThanEqualAndPositionCinturonFinGreaterThanEqualAndInfantil(usuarioInscripto.getCodigoGimnasio(), edad, edad, positionCinturon, positionCinturon, usuarioInscripto.isMenor());
            }
        }
        return mapperCategoria.entity2Model(categoria);
    }

    @Override
    public void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition) {
        Categoria categoria = categoriaRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, initialPosition);
        if (initialPosition > finalPosition) {
            for (int i = initialPosition - 1; i >= finalPosition; i--) {
                moveItem(codigoGimnasio, i, true);
            }
        }
        if (initialPosition < finalPosition) {
            for (int i = initialPosition + 1; i <= finalPosition; i++) {
                moveItem(codigoGimnasio, i, false);
            }
        }
        categoria.setPosition(finalPosition);
        categoriaRepository.save(categoria);
    }

    @Override
    public int findMaxPosition(int codigoGimnasio) {
        Categoria categoria = categoriaRepository.findTopByCodigoGimnasioOrderByPositionDesc(codigoGimnasio);
        if (categoria != null) {
            return categoria.getPosition();
        } else {
            return -1;
        }
    }

    @Override
    public void deleteFromRoot (int idGimnasioRootModel) {
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasio(idGimnasioRootModel);
        for (Categoria categoria: categoriaList) {
            delete(categoria.getId());
        }
    }

    @Override
    public void addFromRoot(GimnasioRootModel customer) {
        Categoria categoria = new Categoria();
        categoria.setEdadInicio(0);
        categoria.setEdadFin(0);
        categoria.setPositionCinturonInicio(0);
        categoria.setPositionCinturonFin(cinturonService.findMaxPosition(customer.getId()));
        categoria.setInclusivo(Boolean.TRUE);
        categoria.setIdPoomsae(poomsaeService.findByCodigoGimnasioAndNombre(customer.getId(), Constantes.INCLUSIVO).getId());
        categoria.setNombre(Constantes.INCLUSIVO);
        categoria.setCodigoGimnasio(customer.getId());
        categoriaRepository.save(categoria);
    }

    private void moveItem(int codigoGimnasio, int position, boolean moveUp) {
        Categoria categoria = categoriaRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position);
        categoria.setPosition(position + (moveUp ? 1 : -1));
        categoriaRepository.save(categoria);
    }
}
