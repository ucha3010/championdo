package com.championdo.torneo.service;


import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.model.UserRoleModel;

import java.util.List;
import java.util.Set;

public interface UserRoleService {

	public abstract List<String> findByUsername(String username);
	
	public abstract UserRoleModel findByUser(User user);
	
	public abstract Set<UserRole> findRolesByUser(User user);
	
	public abstract void save(UserRole userRole);
	
	public abstract void delete(int userRoleId);
	public abstract void deleteByUsername(String username);

	public abstract void actualizarRoles(UserRoleModel userRoleModel);
	public abstract List<UserRole> findDistinctByRole();

}
