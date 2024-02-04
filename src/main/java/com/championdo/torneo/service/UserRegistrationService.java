package com.championdo.torneo.service;

import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.model.UtilModel;

import java.util.List;

public interface UserRegistrationService {

    List<UserModel> findAll();
    List<UserModel> findByActivity(String activity);
    List<UserModel> findByGym(int idGym);
    List<UserModel> findByActivityAndGym(String activity, int idGym);
    List<UtilModel> getActivities();

}
