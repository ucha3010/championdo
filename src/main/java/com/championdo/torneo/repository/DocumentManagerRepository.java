package com.championdo.torneo.repository;

import com.championdo.torneo.entity.DocumentManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("documentManagerRepository")
public interface DocumentManagerRepository extends JpaRepository<DocumentManager, Integer> {
    List<DocumentManager> findAllByOrderByCreationDateDesc();
    List<DocumentManager> findByIdGymOrderByCreationDateDesc(int idGym);
    List<DocumentManager> findByIdGymAndSectionOrderByCreationDateDesc(int idGym, String section);
    List<DocumentManager> findByIdCardOrderByCreationDateDesc(String idCard);
    DocumentManager findByIdOriginalOperativeAndSectionOrderByCreationDateDesc(int idOriginalOperative, String section);
    void deleteByIdCard(String idCard);
}