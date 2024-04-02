package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.DocumentManager;
import com.championdo.torneo.mapper.MapperDocumentManager;
import com.championdo.torneo.model.DocumentManagerModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.repository.DocumentManagerRepository;
import com.championdo.torneo.service.DocumentManagerService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
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
    public DocumentManagerModel findByIdAndIdGym(int id, int idGym) {
        try {
            return mapperDocumentManager.entity2Model(documentManagerRepository.findByIdAndIdGym(id, idGym));
        } catch (EntityNotFoundException e) {
            return new DocumentManagerModel();
        }
    }

    @Override
    public List<DocumentManagerModel> findByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard) {
       return getModelList(documentManagerRepository.findByIdOriginalOperativeAndSectionAndIdCardOrderByCreationDateDesc(idOriginalOperative, section, idCard));
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

    @Override
    public void deleteByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard) {
        List<DocumentManagerModel> documentManagerModelList = findByIdOriginalOperativeAndSectionAndIdCard(idOriginalOperative, section, idCard);
        for (DocumentManagerModel documentManagerModel: documentManagerModelList) {
            try {
                File file = new File(getAbsolutePath() + documentManagerModel.getFullPath());

                if (file.exists() && file.isFile()) {
                    String relativePath = "src" + File.separator + "main" + File.separator + "resources" + File.separator
                            + "static" + File.separator + "files" + File.separator + "tempDelete" + File.separator;
                    File deleteFolder = new File(getAbsolutePath() + relativePath);
                    if (!deleteFolder.exists()) {
                        if (!deleteFolder.mkdirs()) {
                            LoggerMapper.methodIn(Level.ERROR, Utils.obtenerNombreMetodo(), "Problems creating folder ".concat(deleteFolder.getName()), this.getClass());
                        }
                    }
                    Path deletePath = Paths.get(deleteFolder + File.separator + file.getName());
                    Files.move(file.toPath(), deletePath);

                    LoggerMapper.methodOut(Level.INFO,Utils.obtenerNombreMetodo(),"File " + file.getName() + " moved to delete folder", this.getClass());
                    documentManagerModel.setPath(relativePath);
                    documentManagerModel.setDeleteDate(new Date());
                    update(documentManagerModel);
                } else {
                    LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(),"File " + documentManagerModel.getFullPath() + " not exist or is not valid", this.getClass());
                }
            } catch (IOException ioException) {
                LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), ioException.getMessage(), this.getClass());
            }
        }
    }

    @Override
    public void eraseByIdOriginalOperativeAndSectionAndIdCard(int idOriginalOperative, String section, String idCard) {
        List<DocumentManagerModel> documentManagerModelList = findByIdOriginalOperativeAndSectionAndIdCard(idOriginalOperative, section, idCard);
        for (DocumentManagerModel documentManagerModel: documentManagerModelList) {
            try {
                Path fileToErase = Paths.get(getAbsolutePath() + documentManagerModel.getFullPath());

                if (Files.exists(fileToErase)) {
                    Files.delete(fileToErase);
                    delete(documentManagerModel.getId());
                    LoggerMapper.methodOut(Level.INFO,Utils.obtenerNombreMetodo(),fileToErase.getFileName() + " erased", this.getClass());
                } else {
                    LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(),"File " + documentManagerModel.getFullPath() + " not exist or is not valid", this.getClass());
                }
            } catch (IOException ioException) {
                LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), ioException.getMessage(), this.getClass());
            }
        }
    }

    @Override
    public String getAbsolutePath() {
        String[] absolute = new String[1];
        try {
            File f = new File("program.txt");
            absolute = f.getAbsolutePath().split(f.getName());
        }
        catch (Exception e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
        return absolute[0];
    }

    @Override
    public List<DocumentManagerModel> findByIdGymAndIdCardAndSections(int idGym, String idCard, List<String> sections) {
        List<DocumentManager> documentManagerList = new ArrayList<>();
        for (String section: sections) {
            documentManagerList.addAll(documentManagerRepository.findByIdGymAndIdCardAndSectionOrderByCreationDateDesc(idGym, idCard, section));
        }
        return getModelList(documentManagerList);
    }
    @Override
    public void downloadFile(int id, HttpServletResponse response) {

        DocumentManagerModel documentManagerModel = findById(id);
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + documentManagerModel.getFilename() + documentManagerModel.getExtension();
        response.setHeader(headerKey, headerValue);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] file = Files.readAllBytes(Paths.get(documentManagerModel.getFullPath()));
            outputStream.write(file, 0, file.length);
            outputStream.close();
        } catch (IOException e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
    }

    private List<DocumentManagerModel> getModelList(List<DocumentManager> documentManagerList) {
        List<DocumentManagerModel> documentManagerModelList = new ArrayList<>();
        for (DocumentManager documentManager: documentManagerList) {
            documentManagerModelList.add(mapperDocumentManager.entity2Model(documentManager));
        }
        return documentManagerModelList;
    }
}
