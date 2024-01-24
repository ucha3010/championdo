package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Util;
import com.championdo.torneo.mapper.MapperUtil;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.repository.UtilRepository;
import com.championdo.torneo.service.UtilService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.EmailEnum;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UtilServiceImpl implements UtilService {

    @Autowired
    private UtilRepository utilRepository;
    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<UtilModel> findAllStarsWith(String startWord, int codigoGimnasio) {
        List<UtilModel> utilModelList = new ArrayList<>();
        for (Util util: utilRepository.findByCodigoGimnasio(codigoGimnasio)) {
            if(util.getClave().startsWith(startWord)) {
                utilModelList.add(mapperUtil.entity2Model(util));
            }
        }
        return utilModelList;
    }

    @Override
    public List<UtilModel> findAllEndWith(String endWord, int codigoGimnasio) {
        List<UtilModel> utilModelList = new ArrayList<>();
        for (Util util: utilRepository.findByCodigoGimnasio(codigoGimnasio)) {
            if(util.getClave().endsWith(endWord)) {
                if (util.getClave().startsWith("clave") && !StringUtils.isNullOrEmpty(util.getValor())) {
                    util.setCodigoGimnasio(-1);
                    util.setValor("");
                }
                utilModelList.add(mapperUtil.entity2Model(util));
            }
        }
        return utilModelList;
    }

    @Override
    public UtilModel findByClave(String clave, int codigoGimnasio) {
        if (!StringUtils.isNullOrEmpty(clave)) {
            return mapperUtil.entity2Model(utilRepository.findByClaveAndCodigoGimnasio(clave, codigoGimnasio));
        } else {
            return new UtilModel();
        }
    }

    @Override
    public void update(UtilModel utilModel) {
        utilRepository.save(mapperUtil.model2Entity(utilModel));
    }

    @Override
    public void addFromRoot(GimnasioModel customer) {
        List<Util> utilList = new ArrayList<>();
        utilList.add(new Util(Constantes.HABILITAR_BORRAR_INSCRIPCIONES_CAMPEONATO,Constantes.TRUE,customer.getId()));
        utilList.add(new Util(Constantes.HABILITAR_CUENTA_BANCARIA,Constantes.TRUE,customer.getId()));
        utilList.add(new Util(Constantes.HABILITAR_BORRAR_INSCRIPCIONES_TAEKWONDO,Constantes.TRUE,customer.getId()));
        for (Util util: utilList) {
            utilRepository.save(util);
        }
    }

    @Override
    public void deleteFromRoot(int idGimnasioModel) {
        List<Util> utilList = utilRepository.findByCodigoGimnasio(idGimnasioModel);
        utilRepository.deleteAll(utilList);
    }
}
