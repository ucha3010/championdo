package com.championdo.torneo.service;


import com.championdo.torneo.exception.RemoveException;
import com.championdo.torneo.model.Menu2Model;

import java.util.List;

public interface Menu2Service {

    List<Menu2Model> findAll(int idMenu1);
    Menu2Model findById(int id);
    void add(Menu2Model menu2Model);
    void update(Menu2Model menu2Model);
    void delete(int id) throws RemoveException;
    void deleteByIdMenu1(int idMenu1);
    void dragOfPosition(int idMenu1, int initialPosition, int finalPosition);
    int findMaxPosition(int idMenu1);
}
