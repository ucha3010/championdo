package com.championdo.torneo.service;

import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface PdfService {

    public File generarPdfTorneo(PdfModel pdfModel);
    public File generarPdfMandato(PdfModel pdfModel);

    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response);

    public String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, boolean extension);

    public PdfModel getImpresion(InscripcionModel inscripcionModel);

}
