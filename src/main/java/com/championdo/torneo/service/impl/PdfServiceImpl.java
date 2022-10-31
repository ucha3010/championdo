package com.championdo.torneo.service.impl;

import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service()
public class PdfServiceImpl implements PdfService {

    @Autowired
    private InscripcionService inscripcionService;

    @Override
    public void generarPdf(PdfModel pdfModel) {


        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 80);
            if (pdfModel.isMayorEdad()) {
                contentStream.showText("AUTORIZACIÓN DE MAYORES DE 18 AÑOS");
            } else {
                contentStream.showText("AUTORIZACIÓN PARA MENORES DE 18 AÑOS");
                pdfModel.setMoverPorMenorEdad(99);
            }
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            if (pdfModel.isMayorEdad()) {
                parrafoList.add("Yo " + pdfModel.getNombre());
                parrafoList.add("con DNI " + pdfModel.getDni() + ", fecha de nacimiento " + pdfModel.getFechaNacimiento());
                parrafoList.add("y domicilio en " + pdfModel.getDomicilio());
                parrafoList.add("en la localidad de " + pdfModel.getLocalidad());

                generoParrafo(contentStream, page, parrafoList, 123, PDType1Font.TIMES_ROMAN, 16, 33);

            } else {
                parrafoList.add("Yo " + pdfModel.getNombre());
                parrafoList.add("con DNI " + pdfModel.getDni() + ", en calidad de " + pdfModel.getCalidadDe());
                parrafoList.add("y domicilio en " + pdfModel.getDomicilio());
                parrafoList.add("en la localidad de " + pdfModel.getLocalidad());

                generoParrafo(contentStream, page, parrafoList, 123, PDType1Font.TIMES_ROMAN, 16, 33);

                parrafoList = new ArrayList<>();
                parrafoList.add("AUTORIZO A:");

                generoParrafo(contentStream, page, parrafoList, 255, PDType1Font.TIMES_BOLD, 16, 33);

                parrafoList = new ArrayList<>();
                parrafoList.add(pdfModel.getNombreMenor());
                parrafoList.add("con DNI " + pdfModel.getDniMenor() + ", fecha de nacimiento " + pdfModel.getFechaNacimiento());

                generoParrafo(contentStream, page, parrafoList, 288, PDType1Font.TIMES_ROMAN, 16, 33);
            }

            parrafoList = new ArrayList<>();
            parrafoList.add("perteneciente al gimnasio " + pdfModel.getGimnasio());

            generoParrafo(contentStream, page, parrafoList, 255 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_ROMAN, 16, 33);

            parrafoList = new ArrayList<>();
            parrafoList.add("actualmente con cinturón " + pdfModel.getCinturonActual() + " y categoría " + pdfModel.getCategoria());

            generoParrafo(contentStream, page, parrafoList, 288 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_ROMAN, 16, 33);

            parrafoList = new ArrayList<>();
            if (pdfModel.isMayorEdad()) {
                parrafoList.add("INFORMO QUE VOY A PARTICIPAR EN EL CAMPEONATO");
            } else {
                parrafoList.add("INFORMO QUE AUTORIZO A PARTICIPAR EN EL CAMPEONATO");
            }
            parrafoList.add("DE TRES CANTOS A CELEBRAR EL PRÓXIMO: " + pdfModel.getFechaCampeonato());

            generoParrafo(contentStream, page, parrafoList, 323 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_BOLD, 14, 15);

            parrafoList = new ArrayList<>();
            parrafoList.add("DIRECCIÓN: " + pdfModel.getDireccionCampeonato());

            generoParrafo(contentStream, page, parrafoList, 353 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_BOLD, 14, 15);

            parrafoList = new ArrayList<>();
            parrafoList.add("Por medio del presente escrito autorizo a los miembros de organización del");
            parrafoList.add("campeonato, la utilización de mi imagen en el país o en el extranjero por");
            parrafoList.add("cualquier medio ya sea impreso, electrónico o cualquier otro.");
            parrafoList.add("De igual manera, es mi deseo establecer que esta autorización es voluntaria");
            parrafoList.add("y gratuita.");

            generoParrafo(contentStream, page, parrafoList, 398 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_ROMAN, 14, 15);

            parrafoList = new ArrayList<>();
            parrafoList.add("En Cumplimento de la Ley Orgánica de Protección de Datos 15/1999, de");
            parrafoList.add("13 de Diciembre, indico que la información que facilito voluntariamente es");
            parrafoList.add("para la creación de un fichero al objeto de poder gestionar adecuadamente");
            parrafoList.add("el campeonato de taekwondo. Al facilitar mis datos, autorizo al Comité");
            parrafoList.add("Organizador a utilizar mis datos para realizar listados, sorteos,");
            parrafoList.add("publicaciones en medios u otros asuntos relacionados con el campeonato.");

            generoParrafo(contentStream, page, parrafoList, 488 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_ROMAN, 14, 15);

            if (pdfModel.isCinturonBlanco()) {
                parrafoList = new ArrayList<>();
                parrafoList.add("Por otra parte, al no tener licencia federativa, eximo de toda");
                parrafoList.add("responsabilidad al comité organizador de cualquier lesión o daño que");
                parrafoList.add("se produjera el competidor o la competidora durante el evento o daños");
                parrafoList.add("que pudiera realizar a personas o material del pabellón.");

                generoParrafo(contentStream, page, parrafoList, 593 + pdfModel.getMoverPorMenorEdad(), PDType1Font.TIMES_BOLD, 14, 15);
            }




            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, true));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdf", e.getMessage(), PdfServiceImpl.class);
        }

    }

    @Override
    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response) {

        pdfModel.setIdInscripcion((inscripcionService.findByUsernameInscripto(pdfModel.getDni())).getId());
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + nombreArchivo(pdfModel, false, true);
        response.setHeader(headerKey, headerValue);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] file = Files.readAllBytes(Paths.get(nombreArchivo(pdfModel, true, true)));
            outputStream.write(file, 0, file.length);
            outputStream.close();
        } catch (IOException e) {
            LoggerMapper.log(Level.ERROR, "descargarPdf", e.getMessage(), PdfServiceImpl.class);
        }
    }

    @Override
    public String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, boolean extension) {
        String ruta = (rutaCompleta ? "src/main/resources/static/files/" : "");
        String ext = (extension ? ".pdf" : "");
        if (pdfModel.isMayorEdad()) {
            return ruta + pdfModel.getDni() + pdfModel.getFechaCampeonato() + "-" + pdfModel.getIdInscripcion() + ext;
        } else {
            return ruta + pdfModel.getDni() + pdfModel.getFechaCampeonato() + nullOrEmpty(pdfModel.getDniMenor()) + "-" + pdfModel.getIdInscripcion() + ext;
        }
    }

    private String nullOrEmpty(String entrada) {
        return (entrada != null ? entrada : "");
    }

    private void generoParrafo(PDPageContentStream contentStream, PDPage page, List<String> parrafoList,
                                  int alturaPagina, PDType1Font fuente, int fontSize, int salto) throws IOException {
        int i = 0;
        for (String parrafo1: parrafoList) {
            contentStream.beginText();
            contentStream.setFont(fuente, fontSize);
            contentStream.newLineAtOffset( 85, page.getMediaBox().getHeight() - (alturaPagina + i));
            contentStream.showText(parrafo1);
            contentStream.endText();
            i=i+salto;
        }
    }
}
