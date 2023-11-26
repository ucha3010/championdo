package com.championdo.torneo.repository;

import com.championdo.torneo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tokenRepository")
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Token findById(String id);
    List<Token> findAllByOrderByExpirationAsc();

}