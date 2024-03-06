package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.DocumentManager;
import com.championdo.torneo.mapper.MapperDocumentManager;
import com.championdo.torneo.model.DocumentManagerModel;
import com.championdo.torneo.repository.DocumentManagerRepository;
import com.championdo.torneo.service.DocumentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service()
public class DocumentManagerServiceImpl implements DocumentManagerService {

    @Autowired
    private DocumentManagerRepository documentManagerRepository;

    @Autowired
    private MapperDocumentManager mapperDocumentManager;

    @Override
    public List<DocumentManagerModel> findAll() {
        return getModelList(documentManagerRepository.findAllByOrderByCreationDateDesc());
    }

    @Override
    public List<DocumentManagerModel> findByIdGym(int id) {
        return getModelList(documentManagerRepository.findByIdGymOrderByCreationDateDesc(id));
    }

    @Override
    public List<DocumentManagerModel> findByIdGymAndSection(int idGym, String section) {
        return getModelList(documentManagerRepository.findByIdGymAndSectionOrderByCreationDateDesc(idGym, section));
    }

    @Override
    public List<DocumentManagerModel> findByIdCard(String idCard) {
        return getModelList(documentManagerRepository.findByIdCardOrderByCreationDateDesc(idCard));
    }

    @Override
    public DocumentManagerModel findById(int id) {
        try {
            return mapperDocumentManager.entity2Model(documentManagerRepository.getById(id));
        } catch (EntityNotFoundException e) {
            return new DocumentManagerModel();
        }
    }

    @Override
    public DocumentManagerModel findByIdOriginalOperativeAndSection(int idOriginalOperative, String section) {
        try {
            return mapperDocumentManager.entity2Model(documentManagerRepository.findByIdOriginalOperativeAndSectionOrderByCreationDateDesc(idOriginalOperative, section));
        } catch (EntityNotFoundException e) {
            return new DocumentManagerModel();
        }
    }

    @Override
    public DocumentManagerModel add(DocumentManagerModel documentManagerModel) {
        return mapperDocumentManager.entity2Model(documentManagerRepository.save(mapperDocumentManager.model2Entity(documentManagerModel)));
    }

    @Override
    public void update(DocumentManagerModel documentManagerModel) {
        documentManagerRepository.save(mapperDocumentManager.model2Entity(documentManagerModel));
    }

    @Override
    public void delete(int idDocumentManager) {
        documentManagerRepository.deleteById(idDocumentManager);
    }

    @Override
    public void deleteByIdCard(String idCard) {
        documentManagerRepository.deleteByIdCard(idCard);
    }

    private List<DocumentManagerModel> getModelList(List<DocumentManager> documentManagerList) {
        List<DocumentManagerModel> documentManagerModelList = new ArrayList<>();
        for (DocumentManager documentManager: documentManagerList) {
            documentManagerModelList.add(mapperDocumentManager.entity2Model(documentManager));
        }
        return documentManagerModelList;
    }
}
