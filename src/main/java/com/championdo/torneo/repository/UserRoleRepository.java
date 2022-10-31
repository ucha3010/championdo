package com.championdo.torneo.repository;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Serializable>{
	
	public abstract Set<UserRole> findByUser(User user);

	public abstract void deleteByUserRoleId(int userRoleId);

}
