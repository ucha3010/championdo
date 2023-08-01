package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.model.UserRoleModel;

import java.util.List;
import java.util.Set;

public interface UserRoleService {

    List<String> findByUsername(String username);

    UserRoleModel findByUser(User user);

    Set<UserRole> findRolesByUser(User user);

    void save(UserRole userRole);

    void delete(int userRoleId);

    void deleteByUsername(String username);

    void actualizarRoles(UserRoleModel userRoleModel);

    List<UserRole> findDistinctByRole();

}
