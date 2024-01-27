package com.championdo.torneo.repository;

import com.championdo.torneo.entity.UserGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userGymRepository")
public interface UserGymRepository extends JpaRepository<UserGym, Integer> {

    List<UserGym> findByUsername(String username);
    List<UserGym> findByIdGym(int idGym);
    UserGym findByUsernameAndIdGym(String username, int idGym);
    void deleteByUsername(String username);
    void deleteByIdGym(int idGym);

}