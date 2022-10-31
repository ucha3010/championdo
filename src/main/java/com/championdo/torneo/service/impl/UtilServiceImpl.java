package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.Util;
import com.championdo.torneo.repository.UtilRepository;
import com.championdo.torneo.service.UtilService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class UtilServiceImpl implements UtilService {

    @Autowired
    private UtilRepository utilRepository;

    @Override
    public String findByClave(String clave) {
        if (!StringUtils.isNullOrEmpty(clave)) {
            Util util = utilRepository.findByClave(clave);
            return util.getValor();
        } else {
            return "";
        }
    }

    @Override
    public void add(Util util) {

    }

    @Override
    public void update(Util util) {

    }

    @Override
    public void delete(String clave) {

    }
}
