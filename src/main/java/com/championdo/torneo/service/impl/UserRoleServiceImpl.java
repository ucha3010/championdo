package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.model.UserRoleModel;
import com.championdo.torneo.repository.UserRepository;
import com.championdo.torneo.repository.UserRoleRepository;
import com.championdo.torneo.service.UserRoleService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service()
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> findByUsername(String username) {
        com.championdo.torneo.entity.User user = findUserByUsername(username);
        Set<UserRole> userRoleList = userRoleRepository.findByUser(user);
        List<String> roles = new ArrayList<>();
        for (UserRole userRole: userRoleList) {
            roles.add(userRole.getRole());
        }
        return roles;
    }

    @Override
    public UserRoleModel findByUser(User user) {
        Set<UserRole> roles = findRolesByUser(user);
        UserRoleModel userRoleModel = new UserRoleModel();
        userRoleModel.setUsername(user.getUsername());
        List<String> rolesString = new ArrayList<>();
        for (UserRole userRole : roles) {
            rolesString.add(userRole.getRole());
        }
        userRoleModel.setRoles(rolesString);
        return userRoleModel;
    }

    @Override
    public Set<UserRole> findRolesByUser(User user) {
        return userRoleRepository.findByUser(user);
    }

    @Override
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public void delete(int userRoleId) {
        userRoleRepository.deleteByUserRoleId(userRoleId);
    }

    @Override
    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username);
        for (UserRole userRole : user.getUserRole()) {
            userRoleRepository.delete(userRole);
        }
    }

    @Override
    public void actualizarRoles(UserRoleModel userRoleModel) {
        int elimino = 0;
        int mantengo = 0;
        int inserto = 0;
        User user = findUserByUsername(userRoleModel.getUsername());
        Set<UserRole> rolesViejos = findRolesByUser(user);
        List<String> rolesNuevos = userRoleModel.getRoles();
        for (UserRole rolViejo : rolesViejos) {
            if (!rolesNuevos.contains(rolViejo.getRole())) {
                userRoleRepository.delete(rolViejo);
                elimino++;
            } else {
                rolesNuevos.remove(rolViejo.getRole());
                mantengo++;
            }
        }
        UserRole userRole;
        for (String rolNuevo : rolesNuevos) {
            userRole = new UserRole();
            userRole.setRole(rolNuevo);
            userRole.setUser(user);
            userRoleRepository.save(userRole);
            inserto++;
        }
        LoggerMapper.log(Level.INFO, "actualizarRoles", "Inserto: " + inserto + ", mantengo: " + mantengo + ", elimino: " + elimino, getClass());
    }

    @Override
    public List<UserRole> findDistinctByRole() {
        List<UserRole> userRoleList = userRoleRepository.findAll();
        List<String> roles = new ArrayList<>();
        List<UserRole> userRoleListExit = new ArrayList<>();
        for(UserRole userRole : userRoleList) {
            if(!roles.contains(userRole.getRole())) {
                roles.add(userRole.getRole());
                userRoleListExit.add(userRole);
            }
        }
        return userRoleListExit;
    }

    @Override
    public List<UserRole> adminAvailableRoles() {
         return Arrays.asList(new UserRole(null, Constantes.ROLE_USER), new UserRole(null, Constantes.ROLE_ADMIN));
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
