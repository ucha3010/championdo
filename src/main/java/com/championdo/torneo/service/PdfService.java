package com.championdo.torneo.service;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface PdfService {

    public File generarPdfTorneo(PdfModel pdfModel);
    public File generarPdfMandato(PdfModel pdfModel);
    public File generarPdfAutorizacionMayor18(PdfModel pdfModel);
    public File generarPdfAutorizacionMenor18(PdfModel pdfModel);
    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response, String seccion);
    public String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, boolean extension, String seccion);
    public PdfModel getImpresion(InscripcionModel inscripcionModel);

}
