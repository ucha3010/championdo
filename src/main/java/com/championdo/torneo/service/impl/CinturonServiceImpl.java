package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Categoria;
import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.exception.PositionException;
import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.mapper.MapperCinturon;
import com.championdo.torneo.model.CinturonModel;
import com.championdo.torneo.repository.CategoriaRepository;
import com.championdo.torneo.repository.CinturonRepository;
import com.championdo.torneo.service.CinturonService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class CinturonServiceImpl implements CinturonService {

    @Autowired
    private CinturonRepository cinturonRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MapperCinturon mapperCinturon;

    @Override
    public List<CinturonModel> findAll(int codigoGimnasio) {
        List<CinturonModel> cinturonModelList = new ArrayList<>();
        for (Cinturon cinturon: cinturonRepository.findByCodigoGimnasioOrderByPositionAsc(codigoGimnasio)) {
            cinturonModelList.add(mapperCinturon.entity2Model(cinturon));
        }
        return cinturonModelList;
    }

    @Override
    public CinturonModel findById(int id) {
        try {
            return mapperCinturon.entity2Model(cinturonRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new CinturonModel();
        }
    }

    @Override
    public Cinturon findByIdEntity(int id) {
        return cinturonRepository.getById(id);
    }

    @Override
    public void add(CinturonModel cinturonModel) {
        cinturonRepository.save(mapperCinturon.model2Entity(cinturonModel));
    }

    @Override
    public void update(CinturonModel cinturonModel) {
        cinturonRepository.save(mapperCinturon.model2Entity(cinturonModel));
    }

    @Override
    public void delete(int idCinturon) throws RemoveException {
        CinturonModel cinturonModel = findById(idCinturon);
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasioAndPositionCinturonInicioOrPositionCinturonFin(cinturonModel.getCodigoGimnasio(), cinturonModel.getPosition(), cinturonModel.getPosition());
        if (categoriaList == null || categoriaList.isEmpty()) {
            cinturonRepository.deleteById(idCinturon);
            List<Cinturon> cinturonList = cinturonRepository.findByCodigoGimnasioOrderByPositionAsc(cinturonModel.getCodigoGimnasio());
            for (int i = 0; i < cinturonList.size(); i++) {
                if (cinturonList.get(i).getPosition() != i) {
                    cinturonList.get(i).setPosition(i);
                    cinturonRepository.save(cinturonList.get(i));
                }
            }
        } else {
            StringBuilder errorMessage = new StringBuilder("No se puede borrar cinturón por estar en categoría");
            LoggerMapper.log(Level.INFO, "delete", categoriaList, getClass());
            for (Categoria categoria : categoriaList) {
                errorMessage.append(" ").append(categoria.getNombre());
            }
            throw new RemoveException("100", errorMessage.toString());
        }
    }

    @Override
    public void verifyDragOfPositionAvailable(int codigoGimnasio, int posIni, int posFin) throws PositionException {
        List<Categoria> categoriaList;
        int posMenor;
        int posMayor;
        if(posIni < posFin) {
            posMenor = posIni;
            posMayor = posFin;
        } else {
            posMenor = posFin;
            posMayor = posIni;
        }
        categoriaList = categoriaRepository.findByCodigoGimnasioAndPositionCinturonFinGreaterThanEqualAndPositionCinturonInicioLessThanEqual(codigoGimnasio, posMenor, posMayor);
        for (Categoria categoria : categoriaList) {
            int catIni = categoria.getPositionCinturonInicio();
            int catFin = categoria.getPositionCinturonFin();
            /*
            * si el movimiento de posición del cinturón comienza en
            * una posición fuera del rango de la categoría y termina dentro del mismo
            * o bien comienza dentro del rango pero termina fuera, está mal
            */
            if ((dentro(catIni, catFin, posMenor) && fuera(catIni, catFin, posMayor)) ||
                    (fuera(catIni, catFin, posMenor) && dentro(catIni, catFin, posMayor))) {
                throw new PositionException("1003","No se puede cambiar la posición ya que afecta a la categoría ".concat(categoria.getNombre()));
            }
        }
    }

    @Override
    public void dragOfPosition(int codigoGimnasio, int initialPosition, int finalPosition) {
        Cinturon cinturon = cinturonRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, initialPosition);
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
        cinturon.setPosition(finalPosition);
        cinturonRepository.save(cinturon);
        shortCategorias(codigoGimnasio, cinturon);
    }

    @Override
    public int findMaxPosition(int codigoGimnasio) {
        Cinturon cinturon = cinturonRepository.findTopByCodigoGimnasioOrderByPositionDesc(codigoGimnasio);
        if (cinturon != null) {
            return cinturon.getPosition();
        } else {
            return -1;
        }
    }

    private void moveItem(int codigoGimnasio, int position, boolean moveUp) {
        Cinturon cinturon = cinturonRepository.findByCodigoGimnasioAndPosition(codigoGimnasio, position);
        cinturon.setPosition(position + (moveUp ? 1 : -1));
        cinturonRepository.save(cinturon);
        shortCategorias(codigoGimnasio, cinturon);
    }

    private void shortCategorias(int codigoGimnasio, Cinturon cinturon) {
        List<Categoria> categoriaList = categoriaRepository.findByCodigoGimnasioAndIdCinturonInicioOrIdCinturonFin(codigoGimnasio, cinturon.getId(), cinturon.getId());
        for (Categoria categoria: categoriaList) {
            if (categoria.getIdCinturonInicio() == cinturon.getId()) {
                categoria.setPositionCinturonInicio(cinturon.getPosition());
            } else if (categoria.getIdCinturonFin() == cinturon.getId()) {
                categoria.setPositionCinturonFin(cinturon.getPosition());
            }
            categoriaRepository.save(categoria);
        }
    }

    private boolean dentro(int catIni, int catFin, int pos) {
        return catIni <= pos && pos <= catFin;
    }

    private boolean fuera(int catIni, int catFin, int pos) {
        return pos < catIni || pos > catFin;
    }
}
