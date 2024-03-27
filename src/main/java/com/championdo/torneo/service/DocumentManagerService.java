package com.championdo.torneo.service;


import com.championdo.torneo.model.DocumentManagerModel;

import java.util.List;

public interface DocumentManagerService {

    List<DocumentManagerModel> findAll();
    List<DocumentManagerModel> findByIdGym(int idGym);
    List<DocumentManagerModel> findByIdGymAndSection(int idGym, String section);
    List<DocumentManagerModel> findByIdCard(String idCard);
    DocumentManagerModel findById(int id);
    List<DocumentManagerModel> findByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    DocumentManagerModel add(DocumentManagerModel documentManagerModel);
    void update(DocumentManagerModel documentManagerModel);
    void delete(int idDocumentManager);
    void deleteByIdCard(String idCard);
    void deleteByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    void eraseByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    String getAbsolutePath();
}
