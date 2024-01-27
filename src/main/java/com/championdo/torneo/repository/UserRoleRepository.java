package com.championdo.torneo.repository;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Repository("userRoleRepository")
public interface UserRoleRepository extends JpaRepository<UserRole, Serializable> {
    Set<UserRole> findByUser(User user);

    List<UserRole> findByRole(String role);

    void deleteByUserRoleId(int userRoleId);
}