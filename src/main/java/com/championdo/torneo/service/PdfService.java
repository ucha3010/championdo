package com.championdo.torneo.service;

import com.championdo.torneo.model.PdfModel;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {

    public void generarPdf(PdfModel pdfModel);

    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response);

    public String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, boolean extension);

}
