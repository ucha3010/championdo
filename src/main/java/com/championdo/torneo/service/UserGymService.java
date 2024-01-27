package com.championdo.torneo.service;


import com.championdo.torneo.entity.UserGym;
import com.championdo.torneo.model.UserGymModel;

import java.util.List;

public interface UserGymService {

    List<UserGymModel> findByIdGym(int id);

    void deleteByIdGym(int id);

    List<UserGymModel> findByUsername(String username);

    void deleteByUsername(String username);

    void addList(List<UserGym> userGymList);
    UserGym findByUsernameAndIdGym(String username, int idGym);

}
