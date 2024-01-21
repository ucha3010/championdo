package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.UtilManager;
import com.championdo.torneo.mapper.MapperUtilManager;
import com.championdo.torneo.model.UtilManagerModel;
import com.championdo.torneo.repository.UtilManagerRepository;
import com.championdo.torneo.service.UtilManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class UtilManagerServiceImpl implements UtilManagerService {

    @Autowired
    private UtilManagerRepository utilManagerRepository;

    @Autowired
    private MapperUtilManager mapperUtilManager;

    @Override
    public UtilManagerModel get() {
        List<UtilManager> utilManagerList = utilManagerRepository.findAll();
        UtilManagerModel utilManagerModel;
        if (utilManagerList.isEmpty()) {
            utilManagerModel = new UtilManagerModel();
        } else {
            utilManagerModel = mapperUtilManager.entity2Model(utilManagerList.get(0));
        }
        return utilManagerModel;
    }

    @Override
    public UtilManagerModel update(UtilManagerModel utilManagerModel) {
        return mapperUtilManager.entity2Model(utilManagerRepository.save(mapperUtilManager.model2Entity(utilManagerModel)));
    }
}
