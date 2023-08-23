package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Util;
import com.championdo.torneo.mapper.MapperUtil;
import com.championdo.torneo.model.UtilModel;
import com.championdo.torneo.repository.UtilRepository;
import com.championdo.torneo.service.UtilService;
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
    public List<UtilModel> findAllStarsWith(String startWord) {
        List<UtilModel> utilModelList = new ArrayList<>();
        for (Util util: utilRepository.findAll()) {
            if(util.getClave().startsWith(startWord)) {
                utilModelList.add(mapperUtil.entity2Model(util));
            }
        }
        return utilModelList;
    }

    @Override
    public UtilModel findByClave(String clave) {
        if (!StringUtils.isNullOrEmpty(clave)) {
            return mapperUtil.entity2Model(utilRepository.findByClave(clave));
        } else {
            return new UtilModel();
        }
    }

    @Override
    public void update(UtilModel utilModel) {
        utilRepository.save(mapperUtil.model2Entity(utilModel));
    }
}
