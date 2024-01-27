package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.UserGym;
import com.championdo.torneo.mapper.MapperUserGym;
import com.championdo.torneo.model.UserGymModel;
import com.championdo.torneo.repository.UserGymRepository;
import com.championdo.torneo.service.UserGymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UserGymServiceImpl implements UserGymService {

    @Autowired
    private UserGymRepository userGymRepository;

    @Autowired
    private MapperUserGym mapperUserGym;

    @Override
    public List<UserGymModel> findByIdGym(int id) {
        return fillList(userGymRepository.findByIdGym(id));
    }

    @Override
    public void deleteByIdGym(int id) {
        userGymRepository.deleteByIdGym(id);
    }

    @Override
    public List<UserGymModel> findByUsername(String username) {
        return fillList(userGymRepository.findByUsername(username));
    }

    @Override
    public void deleteByUsername(String username) {
        userGymRepository.deleteByUsername(username);
    }

    public void addList(List<UserGym> userGymList) {
        userGymRepository.saveAll(userGymList);
    }

    @Override
    public UserGym findByUsernameAndIdGym(String username, int idGym) {
        return userGymRepository.findByUsernameAndIdGym(username, idGym);
    }

    private List<UserGymModel> fillList(List<UserGym> userGymList) {
        List<UserGymModel> userGymModelList = new ArrayList<>();
        for (UserGym userGym: userGymList) {
            userGymModelList.add(mapperUserGym.entity2Model(userGym));
        }
        return userGymModelList;
    }
}