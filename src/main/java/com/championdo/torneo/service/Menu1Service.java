package com.championdo.torneo.service;


import com.championdo.torneo.model.Menu1Model;

import java.util.List;

public interface Menu1Service {

    List<Menu1Model> findAll();
    Menu1Model findById(int id);
    void add(Menu1Model menu1Model);
    void update(Menu1Model menu1Model);
    void delete(int id);
    void dragOfPosition(int initialPosition, int finalPosition);
    int findMaxPosition();
}
