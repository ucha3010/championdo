package com.championdo.torneo.service;

import com.championdo.torneo.model.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    DocumentManagerModel generarPdfTorneo(PdfModel pdfModel, boolean withSignatureOrFinalDocument);
    DocumentManagerModel createPdfFederativeLicenseMandate(PdfModel pdfModel, boolean withSignatureOrFinalDocument);
    DocumentManagerModel generarPdfAutorizacionMayor18(PdfModel pdfModel, boolean withSignatureOrFinalDocument);
    DocumentManagerModel generarPdfAutorizacionMenor18(PdfModel pdfModel, boolean withSignatureOrFinalDocument);
    DocumentManagerModel generarPdfNormativaSEPA(PdfModel pdfModel, boolean withSignatureOrFinalDocument);
    DocumentManagerModel generarPdfAutorizaWhatsApp(PdfModel pdfModelGeneral, boolean withSignatureOrFinalDocument);
    void descargarArchivo(PdfModel pdfModel, HttpServletResponse response, String section);
    boolean subirArchivo(PdfModel pdfModel, MultipartFile file, String section);
    PdfModel getImpresion(TournamentRegistrationModel tournamentRegistrationModel);
    PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel);
    PdfModel getPdfMandato(MandatoModel mandatoModel);
    String getFileExtension(MultipartFile file);
    String getTempFolder();
}