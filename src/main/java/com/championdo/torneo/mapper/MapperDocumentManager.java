package com.championdo.torneo.mapper;

import com.championdo.torneo.entity.DocumentManager;
import com.championdo.torneo.model.DocumentManagerModel;
import org.springframework.stereotype.Component;

@Component
public class MapperDocumentManager {

    public DocumentManagerModel entity2Model(DocumentManager externObject) {
        DocumentManagerModel localObject = new DocumentManagerModel();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFilename(externObject.getFilename());
            localObject.setExtension(externObject.getExtension());
            localObject.setSection(externObject.getSection());
            localObject.setSectionDescription(externObject.getSectionDescription());
            localObject.setPath(externObject.getPath());
            localObject.setNeedsSignature(externObject.isNeedsSignature());
            localObject.setSignature(externObject.isSignature());
            localObject.setIdCard(externObject.getIdCard());
            localObject.setCreationDate(externObject.getCreationDate());
            localObject.setSignatureDate(externObject.getSignatureDate());
            localObject.setIdOriginalOperative(externObject.getIdOriginalOperative());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setNameGym(externObject.getNameGym());
            localObject.setPlatformDocument(externObject.isPlatformDocument());
            localObject.setDeleteDate(externObject.getDeleteDate());
        }
        return localObject;
    }
    public DocumentManager model2Entity(DocumentManagerModel externObject) {
        DocumentManager localObject = new DocumentManager();
        if (externObject != null) {
            localObject.setId(externObject.getId());
            localObject.setFilename(externObject.getFilename());
            localObject.setExtension(externObject.getExtension());
            localObject.setSection(externObject.getSection());
            localObject.setSectionDescription(externObject.getSectionDescription());
            localObject.setPath(externObject.getPath());
            localObject.setNeedsSignature(externObject.isNeedsSignature());
            localObject.setSignature(externObject.isSignature());
            localObject.setIdCard(externObject.getIdCard());
            localObject.setCreationDate(externObject.getCreationDate());
            localObject.setSignatureDate(externObject.getSignatureDate());
            localObject.setIdOriginalOperative(externObject.getIdOriginalOperative());
            localObject.setIdGym(externObject.getIdGym());
            localObject.setNameGym(externObject.getNameGym());
            localObject.setPlatformDocument(externObject.isPlatformDocument());
            localObject.setDeleteDate(externObject.getDeleteDate());
        }
        return localObject;
    }
}
