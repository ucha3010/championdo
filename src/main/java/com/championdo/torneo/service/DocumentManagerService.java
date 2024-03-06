package com.championdo.torneo.service;


import com.championdo.torneo.model.DocumentManagerModel;

import java.util.List;

public interface DocumentManagerService {

    List<DocumentManagerModel> findAll();
    List<DocumentManagerModel> findByIdGym(int idGym);
    List<DocumentManagerModel> findByIdGymAndSection(int idGym, String section);
    List<DocumentManagerModel> findByIdCard(String idCard);
    DocumentManagerModel findById(int id);
    DocumentManagerModel findByIdOriginalOperativeAndSection(int idOriginalOperative, String section);
    DocumentManagerModel add(DocumentManagerModel documentManagerModel);
    void update(DocumentManagerModel documentManagerModel);
    void delete(int idDocumentManager);
    void deleteByIdCard(String idCard);
}
