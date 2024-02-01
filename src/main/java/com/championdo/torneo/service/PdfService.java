package com.championdo.torneo.service;

import com.championdo.torneo.model.TournamentRegistrationModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.MandatoModel;
import com.championdo.torneo.model.PdfModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface PdfService {

    File generarPdfTorneo(PdfModel pdfModel);

    File generarPdfMandato(PdfModel pdfModel);

    File generarPdfAutorizacionMayor18(PdfModel pdfModel);

    File generarPdfAutorizacionMenor18(PdfModel pdfModel);

    File generarPdfNormativaSEPA(PdfModel pdfModel);

    void descargarArchivo(PdfModel pdfModel, HttpServletResponse response, String seccion);

    boolean subirArchivo(PdfModel pdfModel, MultipartFile file, String seccion);

    PdfModel getImpresion(TournamentRegistrationModel tournamentRegistrationModel);

    PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel);

    PdfModel getPdfMandato(MandatoModel mandatoModel);

    File generarPdfAutorizaWhatsApp(PdfModel pdfModelGeneral);

    String getFileExtension(MultipartFile file);
}
