package com.championdo.torneo.service.impl;

import com.championdo.torneo.exception.EmptyException;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.InscripcionTaekwondoModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.mysql.cj.util.StringUtils;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service()
public class PdfServiceImpl implements PdfService {

    @Override
    public File generarPdfTorneo(PdfModel pdfModel) {

        LoggerMapper.methodIn(Level.INFO, "generarPdfTorneo", pdfModel, this.getClass());
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
            } else if (pdfModel.isInclusivo()) {
                contentStream.showText("AUTORIZACIÓN INCLUSIVA");
            } else {
                contentStream.showText("AUTORIZACIÓN PARA MENORES DE 18 AÑOS");
            }
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo = new StringBuilder();
            int alturaComienzoParrafo = 123;
            int salto = 33;
            int tamanioFuente;
            parrafo.append("Yo ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni());
            if (pdfModel.isMayorEdad()) {
                parrafo.append(", fecha de nacimiento ").append(pdfModel.getFechaNacimiento());
            } else {
                parrafo.append(", en calidad de ").append(pdfModel.getCalidadDe());
            }
            parrafo.append(" y domicilio en ").append(pdfModel.getDomicilio());
            parrafo.append(" en la localidad de ").append(pdfModel.getLocalidad());

            if (!pdfModel.isMayorEdad()) {

                parrafo.append(" AUTORIZO A ");
                parrafo.append(pdfModel.getNombreMenor()).append(!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ? " con DNI " + pdfModel.getDniMenor() + " y" : "");
                parrafo.append(" con fecha de nacimiento ").append(pdfModel.getFechaNacimientoMenor());
            }

            parrafo.append(" perteneciente al gimnasio ").append(pdfModel.getGimnasio());
            parrafo.append(" actualmente con cinturón ").append(pdfModel.getCinturonActual()).append(" y categoría ").append(pdfModel.getCategoria());

            if (!pdfModel.isInclusivo()) {
                parrafo.append(" (a dicha categoría le corresponde realizar: ").append(pdfModel.getPoomsae()).append(").");
            } else {
                parrafo.append(" (a dicha categoría le corresponde realizar el KICHO o POOMSAE que deseen).");
            }
            tamanioFuente = 16;
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto);
            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            if (pdfModel.isMayorEdad()) {
                parrafo.append("Informo que voy a participar en el ");
            } else {
                parrafo.append("Informo que AUTORIZO a participar en el ");
            }
            parrafo.append(pdfModel.getNombreCampeonato()).append(" a celebrar el próximo ").append(pdfModel.getFechaCampeonato());
            parrafo.append(", en la dirección ").append(pdfModel.getDireccionCampeonato()).append(".");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, true, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto + 20);
            parrafo = new StringBuilder();
            parrafo.append("Por medio del presente escrito autorizo a los miembros de organización del ");
            parrafo.append("campeonato, la utilización de mi imagen en el país o en el extranjero por ");
            parrafo.append("cualquier medio ya sea impreso, electrónico o cualquier otro. ");
            parrafo.append("De igual manera, es mi deseo establecer que esta autorización es voluntaria y gratuita.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, false, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto + 10);
            parrafo = new StringBuilder();
            parrafo.append("En Cumplimento de la Ley Orgánica de Protección de Datos 15/1999, de ");
            parrafo.append("13 de Diciembre, indico que la información que facilito voluntariamente es ");
            parrafo.append("para la creación de un fichero al objeto de poder gestionar adecuadamente ");
            parrafo.append("el campeonato de taekwondo. Al facilitar mis datos, autorizo al Comité ");
            parrafo.append("Organizador a utilizar mis datos para realizar listados, sorteos, ");
            parrafo.append("publicaciones en medios u otros asuntos relacionados con el campeonato.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, false, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            if (pdfModel.isCinturonBlanco()) {
                alturaComienzoParrafo += (parrafoList.size() * salto + 10);
                parrafo = new StringBuilder();
                parrafo.append("Por otra parte, al no tener licencia federativa, eximo de toda ");
                parrafo.append("responsabilidad al comité organizador de cualquier lesión o daño que ");
                parrafo.append("se produjera el competidor o la competidora durante el evento o daños ");
                parrafo.append("que pudiera realizar a personas o material del pabellón.");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, true, false);

                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            }




            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, "torneo"));
            return new File(nombreArchivo(pdfModel, true, "torneo"));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdfTorneo", e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public File generarPdfMandato(PdfModel pdfModel) {

        LoggerMapper.methodIn(Level.INFO, "generarPdfMandato", pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
            contentStream.showText("Mandato específico para la inscripción federativa");
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo;
            int alturaComienzoParrafo = 90;
            int salto;
            int tamanioFuente;
            int saltoParrafo = 20;
            String nombreMenor = "";
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
            String[] hoy = sdf.format(calendar.getTime()).split("/");

            if(!StringUtils.isNullOrEmpty(pdfModel.getCalidadDe())) {
                nombreMenor = " (" + pdfModel.getNombreMenor() + ")";
                salto = 15;
                tamanioFuente = 14;
                parrafo = new StringBuilder();
                parrafo.append("Para los menores, son los datos de").append(pdfModel.getCalidadDe().equalsIgnoreCase("Madre") ? " la " : "l ");
                parrafo.append(pdfModel.getCalidadDe()).append(" y entre paréntesis el nombre del deportista.");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
                alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);
            }

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("D./Dña. ").append(pdfModel.getNombre()).append(nombreMenor).append(" con DNI ").append(pdfModel.getDni()).append(", en su propio ");
            parrafo.append("nombre y representación, con domicilio a efectos de notificaciones en ").append(pdfModel.getDomicilio()).append(" ");
            parrafo.append(pdfModel.getLocalidad()).append(" en concepto de MANDANTE.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Dice y otorga");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que confiere MANDATO CON REPRESENTACIÓN a favor del representante del Club Championdo con domicilio ");
            parrafo.append("en Av Viñuelas 30, en concepto de MANDATARIO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que el presente MANDATO, que se rige por los arts. 1709 a 1739 CC español se confiere para que se pueda llevar ");
            parrafo.append("a cabo la inscripción federativa del MANDANTE en la temporada ").append(hoy[0]);
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que el presente MANDATO se confiere para su actuación ante las dependencias federativas, personalmente o ");
            parrafo.append("a través de recursos online, en relación exclusivamente del asunto citado como objeto del MANDATO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que este MANDATO tiene exclusiva vigencia para la inscripción federativa, finalizando la misma en el ");
            parrafo.append("momento en que se produzca la inscripción.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que conoce y acepta el tratamiento de datos que llevará a cabo la federación, la cual legitima el mismo con ");
            parrafo.append("los requisitos normativos que cumplimenta el MANDATARIO por efecto de este MANDATO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Y para que conste dejo firmado de forma electrónica este documento ");
            parrafo.append("con fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]);
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += 85;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset( 80, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("----------------------------------------------------------------------------");
            contentStream.endText();
            alturaComienzoParrafo += 30;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("A rellenar por el MANDATARIO");
            contentStream.endText();
            alturaComienzoParrafo += 30;

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Acepto el MANDATO conferido y me obligo a cumplirlo de conformidad a las instrucciones del MANDANTE, y declaro ");
            parrafo.append("bajo mi responsabilidad de la veracidad y actualización de los datos facilitados para la inscripción federativa.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, 180.0, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + 20);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("En                  , a      de                de");
            contentStream.endText();


            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, "mandato"));
            return new File(nombreArchivo(pdfModel, true, "mandato"));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdfMandato", e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public File generarPdfAutorizacionMayor18(PdfModel pdfModel) {

        LoggerMapper.methodIn(Level.INFO, "generarPdfAutorizacionMayor18", pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
            contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
            contentStream.showText("AUTORIZACIÓN DE MAYORES DE 18 AÑOS");
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo;
            int alturaComienzoParrafo = 90;
            int salto;
            int tamanioFuente;
            int saltoParrafo = 20;
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
            String[] hoy = sdf.format(calendar.getTime()).split("/");

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Yo D./Dña. ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni());
            parrafo.append(", fecha de nacimiento ").append(pdfModel.getFechaNacimiento()).append(" y domicilio en ").append(pdfModel.getDomicilio()).append(" ");
            parrafo.append(pdfModel.getLocalidad()).append(" perteneciente al gimnasio CHAMPIONDO");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("INFORMO QUE ENTRENO EN EL GIMNASIO CHAMPIONDO Y PARTICIPO VOLUNTARIAMENTE EN LOS CAMPEONATOS Y ");
            parrafo.append("ENTRENAMIENTOS QUE PARTICIPEN LOS ALUMNOS DEL GIMNASIO CHAMPIONDO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por medio del presente escrito autorizo a los miembros del gimnasio CHAMPIONDO, a la utilización ");
            parrafo.append("de mi imagen en el país o en el extranjero por cualquier medio ya sea impreso, electrónico ");
            parrafo.append("o cualquier otro. De igual manera, es mi deseo establecer que esta autorización es voluntaria ");
            parrafo.append("y gratuita.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("En cumplimiento con la Ley Orgánica de Protección de Datos 15/1999, de 13 de Diciembre, ");
            parrafo.append("indico que la información que facilito voluntariamente es para la creación de un fichero al ");
            parrafo.append("objeto de poder gestionar adecuadamente los datos. Al facilitar mis datos, autorizo al ");
            parrafo.append("gimnasio CHAMPIONDO a utilizar mis datos para realizar listados, sorteos, publicaciones ");
            parrafo.append("en medios y otros asuntos relacionados con el gimnasio CHAMPIONDO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por otra parte, eximo de toda responsabilidad al gimnasio CHAMPIONDO de cualquier lesión ");
            parrafo.append("o daño que se produjera el alumno o la alumna durante los entrenamientos y campeonatos ");
            parrafo.append("a los que acuda o daños que pudiera realizar a personas o materiales.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Y para que conste dejo firmado de forma electrónica este documento ");
            parrafo.append("con fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]);
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            //alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, "autorizacionMayor18"));
            return new File(nombreArchivo(pdfModel, true, "autorizacionMayor18"));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdfAutorizacionMayor18", e.getMessage(), PdfServiceImpl.class);
        }
        return null;
    }

    @Override
    public File generarPdfAutorizacionMenor18(PdfModel pdfModel) {

        LoggerMapper.methodIn(Level.INFO, "generarPdfAutorizacionMenor18", pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
            contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
            contentStream.showText("AUTORIZACIÓN DE MENORES DE 18 AÑOS");
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo;
            int alturaComienzoParrafo = 90;
            int salto;
            int tamanioFuente;
            int saltoParrafo = 20;
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
            String[] hoy = sdf.format(calendar.getTime()).split("/");

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Yo D./Dña. ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni()).append(" ");
            parrafo.append("en calidad de ").append(pdfModel.getCalidadDe()).append(" y domicilio en ").append(pdfModel.getDomicilio()).append(" ");
            parrafo.append(pdfModel.getLocalidad());
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("INFORMO QUE AUTORIZO A ENTRENAR EN EL GIMNASIO CHAMPIONDO Y A PARTICIPAR EN LOS CAMPEONATOS Y ");
            parrafo.append("ENTRENAMIENTOS QUE PARTICIPEN LOS ALUMNOS DEL GIMNASIO CHAMPIONDO A:");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("D./Dña. ").append(pdfModel.getNombreMenor());
            parrafo.append(!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ? " con DNI " + pdfModel.getDniMenor() + " y " : " ");
            parrafo.append("con fecha de nacimiento ").append(pdfModel.getFechaNacimientoMenor());
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por medio del presente escrito autorizo a los miembros del gimnasio CHAMPIONDO, a la utilización ");
            parrafo.append("de la imagen de ").append(pdfModel.getNombreMenor()).append(" en el país o en el extranjero ");
            parrafo.append("por cualquier medio ya sea impreso, electrónico o cualquier otro. De igual manera, es mi ");
            parrafo.append("deseo establecer que esta autorización es voluntaria y gratuita.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("En cumplimiento con la Ley Orgánica de Protección de Datos 15/1999, de 13 de Diciembre, ");
            parrafo.append("indico que la información que facilito voluntariamente es para la creación de un fichero al ");
            parrafo.append("objeto de poder gestionar adecuadamente los datos. Al facilitar mis datos, autorizo al ");
            parrafo.append("gimnasio CHAMPIONDO a utilizar mis datos para realizar listados, sorteos, publicaciones ");
            parrafo.append("en medios y otros asuntos relacionados con el gimnasio CHAMPIONDO.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por otra parte, eximo de toda responsabilidad al gimnasio CHAMPIONDO de cualquier lesión ");
            parrafo.append("o daño que se produjera el alumno o la alumna durante los entrenamientos y campeonatos ");
            parrafo.append("a los que acuda o daños que pudiera realizar a personas o materiales.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Y para que conste dejo firmado de forma electrónica este documento ");
            parrafo.append("con fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]);
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            //alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, "autorizacionMenor18"));
            return new File(nombreArchivo(pdfModel, true, "autorizacionMenor18"));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdfAutorizacionMenor18", e.getMessage(), PdfServiceImpl.class);
        }
        return null;
    }

    @Override
    public File generarPdfNormativaSEPA(PdfModel pdfModel) {

        LoggerMapper.methodIn(Level.INFO, "generarPdfNormativaSEPA", pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo;
            int alturaComienzoParrafo = 40;
            int salto;
            int tamanioFuente;
            int dejoDeMargenPosterior = 0;
            Calendar calendar = GregorianCalendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
            String[] hoy = sdf.format(calendar.getTime()).split("/");
            // Definir las coordenadas y dimensiones del cuadrado
            final float x = 45;
            final float width = 510;

            salto = 18;
            tamanioFuente = 20;
            parrafoList.add("GIMNASIO CHAMPIONDO");
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, 170, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 22;
            tamanioFuente = 16;
            parrafoList = new ArrayList<>();
            parrafoList.add("TRATAMIENTO DE DATOS DE CLIENTES");
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, 135, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 20;
            tamanioFuente = 20;
            parrafoList = new ArrayList<>();
            parrafoList.add("NORMATIVA SEPA");
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, 205, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            //salto = 20;tamanioFuente = 20;
            dejoDeMargenPosterior = 20;
            parrafoList = new ArrayList<>();
            parrafoList.add("MANDATO ADEUDO DIRECTO SEPA");
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, 120, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 10;
            parrafoList = new ArrayList<>();
            parrafoList.add("DATOS DEL DEPORTISTA (Quien entrena en el gimnasio)");
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            if (!StringUtils.isNullOrEmpty(pdfModel.getNombreMenor())) {

                //salto = 15;tamanioFuente = 14;
                dejoDeMargenPosterior = 20;
                parrafoList = new ArrayList<>();
                parrafoList.add("Nombre: " + pdfModel.getNombreMenor());
                if(!StringUtils.isNullOrEmpty(pdfModel.getDniMenor())) {
                    parrafoList.add("DNI: " + pdfModel.getDniMenor() + "              " + "Fecha de nacimiento: " + pdfModel.getFechaNacimientoMenor());
                } else {
                    parrafoList.add("Fecha de nacimiento: " + pdfModel.getFechaNacimientoMenor());
                }
                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
                alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

                encuadrarParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, salto, dejoDeMargenPosterior, x, 0, width, 0);

                //salto = 15;tamanioFuente = 14;
                dejoDeMargenPosterior = 10;
                parrafo = new StringBuilder();
                parrafo.append("PERSONA AUTORIZADORA EN CALIDAD DE ").append(pdfModel.getCalidadDe().toUpperCase()).append(" (Para deportistas menores de edad)");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
                alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            }

            alturaComienzoParrafo = rellenarAdulto(pdfModel, alturaComienzoParrafo, contentStream, page, x, width);

            salto = 20;
            tamanioFuente = 20;
            parrafo = new StringBuilder();
            parrafo.append("DATOS BANCARIOS");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, 205, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 20;
            parrafoList = new ArrayList<>();
            //TODO DAMIAN modificar esto para no usar objeto CuentaBancaria
            parrafoList.add("Titular: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getTitular() : Constantes.ERROR_DATOS_BANCARIOS));
            parrafoList.add("IBAN: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getIban() : Constantes.ERROR_DATOS_BANCARIOS));
            parrafoList.add("CÓDIGO SWIFT/BIC: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getSwift() : Constantes.ERROR_DATOS_BANCARIOS));
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            encuadrarParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, salto, dejoDeMargenPosterior, x, 0, width, 0);

            float heightAgregado = 0;
            salto = 12;
            tamanioFuente = 12;
            dejoDeMargenPosterior = 0;
            parrafo = new StringBuilder();
            parrafo.append("En nombre de la empresa tratamos la información que nos facilita con el fin de prestarles el servicio solicitado, ");
            parrafo.append("realizar la facturación del mismo. Los datos proporcionados se conservarán mientras se ");
            parrafo.append("mantenga la relación comercial o durante los años necesarios para cumplir con las obligaciones legales. ");
            parrafo.append("Los datos no se cederán a terceros salvo en los casos en que exista una obligación legal. Usted tiene ");
            parrafo.append("derecho a obtener confirmación sobre si en el gimnasio CHAMPIONDO estamos tratando sus datos ");
            parrafo.append("personales por tanto tiene derecho a acceder a sus datos personales, rectificar los datos inexactos o ");
            parrafo.append("solicitar su supresión cuando los datos ya no sean necesarios.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            //salto = 12;tamanioFuente = 12;
            dejoDeMargenPosterior = 15;
            parrafo = new StringBuilder();
            parrafo.append("Asimismo solicito su autorización para ofrecerle productos y servicios relacionados con los solicitados y ");
            parrafo.append("fidelizarle como cliente.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            cuadradoSeleccion(contentStream, page, alturaComienzoParrafo, x, 30, 10, 10);
            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 0;
            parrafo = new StringBuilder();
            parrafo.append("  SI");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            cuadradoSeleccion(contentStream, page, alturaComienzoParrafo, x, 30, 10, 10);
            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("NO");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            //salto = 25;tamanioFuente = 14;
            dejoDeMargenPosterior = 30;
            parrafo = new StringBuilder();
            parrafo.append("Por favor indique SI o NO");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            encuadrarParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, salto, dejoDeMargenPosterior, x, 0, width, heightAgregado);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por la presente autorizo a los titulares del gimnasio CHAMPIONDO a enviar instrucciones a la ");
            parrafo.append("entidad arriba indicada para efectuar los adeudos en su cuenta bancaria y me comprometo a ");
            parrafo.append("realizar los pagos en los plazos establecidos responsabilizándome de los costes generados en ");
            parrafo.append("caso de devolución.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

            //salto = 35;
            tamanioFuente = 18;
            parrafo = new StringBuilder();
            parrafo.append("Fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]).append("           FIRMA:");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            //alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, "normativaSEPA"));
            return new File(nombreArchivo(pdfModel, true, "normativaSEPA"));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdfNormativaSEPA", e.getMessage(), PdfServiceImpl.class);
        }
        return null;
    }

    /**
     *
     * @param seccion valores torneo, mandato, autorizacionMayor18, autorizacionMenor18, normativaSEPA
     */
    @Override
    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response, String seccion) {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + nombreArchivo(pdfModel, false, seccion);
        response.setHeader(headerKey, headerValue);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] file = Files.readAllBytes(Paths.get(nombreArchivo(pdfModel, true, seccion)));
            outputStream.write(file, 0, file.length);
            outputStream.close();
        } catch (IOException e) {
            LoggerMapper.log(Level.ERROR, "descargarPdf", e.getMessage(), PdfServiceImpl.class);
        }
    }

    @Override
    public String nombreArchivo(PdfModel pdfModel, boolean rutaCompleta, @NotNull String section) {

        String ruta = (rutaCompleta ? "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "files" + File.separator + section + tounamentDate(pdfModel) : "");
        if(rutaCompleta) {
            File directorio = new File(getAbsolutePath() + ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
        }
        if (pdfModel.isMayorEdad()) {
            return ruta + pdfModel.getDni() + "-" + pdfModel.getIdInscripcion() + ".pdf";
        } else {
            return ruta + pdfModel.getDni()
                    + (!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ? "-" + pdfModel.getDniMenor().trim() : "")
                    + "-" + pdfModel.getIdInscripcion() + ".pdf";
        }
    }

    @Override
    public PdfModel getImpresion(InscripcionModel inscripcionModel) {

        PdfModel pdfModel = new PdfModel();
        if (inscripcionModel != null){
            if (inscripcionModel.getDniAutorizador() != null) {
                pdfModel.setMayorEdad(false);
                pdfModel.setDni(inscripcionModel.getDniAutorizador());
                pdfModel.setDniMenor(inscripcionModel.getDniInscripto());
            } else {
                pdfModel.setMayorEdad(true);
                pdfModel.setDni(inscripcionModel.getDniInscripto());
            }
            pdfModel.setIdInscripcion(inscripcionModel.getId());
            pdfModel.setFechaCampeonato(inscripcionModel.getFechaCampeonato());
        }
        return pdfModel;
    }

    public PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel) {
        //TODO DAMIAN hacer
        return null;
    }

    private int rellenarAdulto(PdfModel pdfModel, int alturaComienzoParrafo, PDPageContentStream contentStream, PDPage page,
                               float x, float width) throws IOException, EmptyException {

        int salto = 15;
        int tamanioFuente = 14;
        int dejoDeMargenPosterior = 20;
        List<String> parrafoList = new ArrayList<>();
        parrafoList.add("Nombre: " + pdfModel.getNombre());
        if(pdfModel.getFechaNacimiento() != null) {
            parrafoList.add("DNI: " + pdfModel.getDni() + "                 " + "Fecha de nacimiento: " + pdfModel.getFechaNacimiento());
        } else {
            parrafoList.add("DNI: " + pdfModel.getDni());
        }
        parrafoList = organizaRenglones(parrafoList, "Dirección: " + pdfModel.getDomicilio() + " - "
                + pdfModel.getLocalidad(), tamanioFuente, null, false, true);
        parrafoList = organizaRenglones(parrafoList, "Teléfono: " + pdfModel.getTelefono() + "          "
                + "Email: " + pdfModel.getCorreo(), tamanioFuente, null, false, true);

        generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

        alturaComienzoParrafo += (parrafoList.size() * salto + dejoDeMargenPosterior);

        encuadrarParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, salto, dejoDeMargenPosterior, x, 0, width, 0);

        return alturaComienzoParrafo;
    }

    private void generoParrafo(PDPageContentStream contentStream, PDPage page, List<String> parrafoList,
                                  int alturaPagina, PDType1Font fuente, int fontSize, Integer margenDerecho, int salto) throws IOException {
        int i = 0;
        for (String parrafo1: parrafoList) {
            contentStream.beginText();
            contentStream.setFont(fuente, fontSize);
            contentStream.newLineAtOffset( (margenDerecho == null ? 50 : margenDerecho), page.getMediaBox().getHeight() - (alturaPagina + i));
            contentStream.showText(parrafo1);
            contentStream.endText();
            i=i+salto;
        }
    }

    private String tounamentDate(PdfModel pdfModel) {
        StringBuilder dateFolder = new StringBuilder();
        if(pdfModel != null && !StringUtils.isNullOrEmpty(pdfModel.getFechaCampeonato())) {
            String[] folderNameArray = pdfModel.getFechaCampeonato().split("-");
            for(int i=folderNameArray.length; i>0; i--) {
                dateFolder.append(folderNameArray[i - 1]);
            }
        }
        dateFolder.append(File.separator);
        return dateFolder.toString();
    }

    private String getAbsolutePath() {
        String[] absolute = new String[1];
        try {
            File f = new File("program.txt");
            absolute = f.getAbsolutePath().split(f.getName());
        }
        catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "getAbsolutePath", e.getMessage(), PdfServiceImpl.class);
        }
        return absolute[0];
    }


    private List<String> organizaRenglones(List<String> parrafoList, String parrafo, int tamanioFuente, 
                                           Double anchoParrafoMlimetros, boolean negrita, boolean agrego) throws EmptyException {

        LoggerMapper.log(Level.INFO, "organizaRenglones", "parrafoList: " + parrafoList + " - parrafo: " + 
                parrafo + " - tamanioFuente: " + tamanioFuente + " - anchoParrafoMlimetros: " + anchoParrafoMlimetros + 
                " - agrego: " + agrego, PdfServiceImpl.class);
        if(!agrego) {
            parrafoList = new ArrayList<>();
        }
        StringBuilder renglon = new StringBuilder();
        final double puntoEnMilimetros = (negrita ? 0.33 : 0.30);
        List<String> letrasTamanio1 = Arrays.asList("i_í_I_Í_l_._-_:_\"_'_,_;_¡_!_º_ª_(_)".split("_"));
        List<String> letrasTamanio4 = Arrays.asList("m_w_M_W".split("_"));
        if(anchoParrafoMlimetros == null || anchoParrafoMlimetros <= 0.0 || anchoParrafoMlimetros > 180.0) {
            anchoParrafoMlimetros = 180.0;
        }
        if (parrafo == null || parrafo.isEmpty()) {
            throw new EmptyException("EMPTY", "organizaRenglones - Objeto parrafo sin datos");
        }
        String[] palabras = parrafo.split(" ");
        double parrafoUtilizado = 0.0;
        double tamanioNuevaPalabra;
        double sumoMayuscula;
        for(String palabra: palabras) {
            tamanioNuevaPalabra = 2.0; //sumo el espacio final que sería 2.0
            String letra;
            for (int i=0; i<palabra.length(); i++) {
                letra = palabra.substring(i, i + 1);
                sumoMayuscula = (letra.equals(letra.toUpperCase()) ? puntoEnMilimetros * 0.5 : 0.0);
                if (letrasTamanio1.contains(letra)) {
                    tamanioNuevaPalabra += (double) tamanioFuente /4 * (puntoEnMilimetros + sumoMayuscula);
                } else if (letrasTamanio4.contains(letra)) {
                    tamanioNuevaPalabra += tamanioFuente * (puntoEnMilimetros + sumoMayuscula);
                } else {
                    tamanioNuevaPalabra += (double) tamanioFuente /2 * (puntoEnMilimetros + sumoMayuscula);
                }
            }
            if (parrafoUtilizado + tamanioNuevaPalabra > anchoParrafoMlimetros) {
                parrafoList.add(renglon.toString());
                renglon = new StringBuilder();
                parrafoUtilizado = tamanioNuevaPalabra;
            } else {
                parrafoUtilizado += tamanioNuevaPalabra;
            }
            renglon.append(palabra.concat(" "));
        }
        parrafoList.add(renglon.toString());
        LoggerMapper.log(Level.INFO, "organizaRenglones", "parrafoList: " + parrafoList + " - anchoParrafoMlimetros: " + anchoParrafoMlimetros, PdfServiceImpl.class);
        return parrafoList;
    }

    private void encuadrarParrafo(PDPageContentStream contentStream, PDPage page, List<String> parrafoList, int alturaComienzoParrafo, int salto,
                                  int dejoDeMargenPosterior, float x, float yAgregado, float width, float heightAgregado) throws IOException {

        // Dibujar el cuadrado
        contentStream.addRect(x, page.getMediaBox().getHeight() - alturaComienzoParrafo + dejoDeMargenPosterior + 10 + yAgregado,
                width, parrafoList.size() * salto + 5 + heightAgregado);
        contentStream.setLineWidth(2); // Ancho del borde
        contentStream.setStrokingColor(0, 0, 0); // Color del borde (negro)
        contentStream.stroke();
    }

    private void cuadradoSeleccion(PDPageContentStream contentStream, PDPage page, int alturaComienzoParrafo, float x,
                                   float dejoDeMargenIzquierdo, float width, float height) throws IOException {

        // Dibujar el cuadrado
        contentStream.addRect(x + dejoDeMargenIzquierdo, page.getMediaBox().getHeight() - alturaComienzoParrafo, width, height);
        contentStream.setLineWidth(1); // Ancho del borde
        contentStream.setStrokingColor(0, 0, 0); // Color del borde (negro)
        contentStream.stroke();
    }

}
