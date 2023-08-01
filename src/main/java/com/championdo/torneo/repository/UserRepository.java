package com.championdo.torneo.repository;

import com.championdo.torneo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Serializable>{
	User findByUsername(String username);

    List<User> findAllByOrderByLastnameDesc();
}