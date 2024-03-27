package com.championdo.torneo.service;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.model.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    DocumentManagerModel generarPdfTorneo(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument);
    DocumentManagerModel createPdfFederativeLicenseMandate(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument);
    DocumentManagerModel generarPdfAutorizacionMayor18(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument);
    DocumentManagerModel generarPdfAutorizacionMenor18(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument);
    DocumentManagerModel generarPdfNormativaSEPA(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument);
    DocumentManagerModel generarPdfAutorizaWhatsApp(PdfModel pdfModelGeneral, boolean createWithSignatureOrCreateFinalDocument);
    void descargarArchivo(PdfModel pdfModel, HttpServletResponse response, String section);
    boolean subirArchivo(PdfModel pdfModel, MultipartFile file, String section);
    PdfModel getImpresion(TournamentRegistrationModel tournamentRegistrationModel);
    PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel);
    PdfModel getPdfMandato(MandatoModel mandatoModel);
    String getFileExtension(MultipartFile file);
    String getTempFolder();
    void deleteFilesTaekwondoRegistration(InscripcionTaekwondoModel inscripcionTaekwondoModel, User usuario);
    void eraseByIdOriginalOperativeAndSectionAndIdCard(Integer idOriginalOperative, String section, String idCard);
}