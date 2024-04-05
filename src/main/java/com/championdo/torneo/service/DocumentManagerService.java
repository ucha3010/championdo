package com.championdo.torneo.service;


import com.championdo.torneo.entity.DocumentManager;
import com.championdo.torneo.exception.EmptyException;
import com.championdo.torneo.model.DocumentManagerModel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DocumentManagerService {

    List<DocumentManagerModel> findAll();
    List<DocumentManagerModel> findByIdGym(int idGym);
    List<DocumentManagerModel> findByIdGymAndSection(int idGym, String section);
    List<DocumentManagerModel> findByIdCard(String idCard);
    DocumentManagerModel findById(int id);
    DocumentManagerModel findByIdAndIdGym(int id, int idGym);
    List<DocumentManagerModel> findByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    DocumentManagerModel add(DocumentManagerModel documentManagerModel);
    void update(DocumentManagerModel documentManagerModel);
    void delete(int idDocumentManager);
    void deleteByIdCard(String idCard);
    void deleteByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    void eraseByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard);
    String getAbsolutePath();
    String getTempFolder();
    List<DocumentManagerModel> findByIdGymAndIdCardAndSections(int idGym, String idCard, List<String> sections);
    void downloadFile(int id, HttpServletResponse response);
    void downloadFile(String localFullPathWithFilenameWithExt, String filenameWithExtention, HttpServletResponse response);
    void downloadZipFile(List<Integer> idList, HttpServletResponse response) throws EmptyException;
}
