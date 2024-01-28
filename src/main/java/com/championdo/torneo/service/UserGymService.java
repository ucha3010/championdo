package com.championdo.torneo.service;


import com.championdo.torneo.entity.UserGym;
import com.championdo.torneo.model.UserGymModel;
import com.championdo.torneo.model.UserModel;

import java.util.List;

public interface UserGymService {

    List<UserGymModel> findByIdGym(int id);
    void deleteByIdGym(int id);
    List<UserGymModel> findByUsername(String username);
    void deleteByUsername(String username);
    UserGymModel add(UserGymModel userGymModel);
    void addList(List<UserGym> userGymList);
    UserGym findByUsernameAndIdGym(String username, int idGym);
    void deleteByUsernameAndIdGym(String username, int idGym);
    List<UserModel> deleteUsersAssignedToGym(List<UserModel> userModelList, int idGym);

}
