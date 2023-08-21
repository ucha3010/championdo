package com.championdo.torneo.service;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface PdfService {

    File generarPdfTorneo(PdfModel pdfModel);

    File generarPdfMandato(PdfModel pdfModel);

    File generarPdfAutorizacionMayor18(PdfModel pdfModel);

    File generarPdfAutorizacionMenor18(PdfModel pdfModel);

    File generarPdfNormativaSEPA(PdfModel pdfModel);

    void descargarPdf(PdfModel pdfModel, HttpServletResponse response, String seccion);

    String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, String seccion);

    PdfModel getImpresion(InscripcionModel inscripcionModel);

    PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel);

    File generarPdfAutorizaWhatsApp(PdfModel pdfModelGeneral);
}
