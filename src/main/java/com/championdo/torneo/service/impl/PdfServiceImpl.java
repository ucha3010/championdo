package com.championdo.torneo.service.impl;

import com.championdo.torneo.exception.EmptyException;
import com.championdo.torneo.model.InscripcionModel;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.LoggerMapper;
import com.mysql.cj.util.StringUtils;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service()
public class PdfServiceImpl implements PdfService {

    @Override
    public File generarPdfTorneo(PdfModel pdfModel) {

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

            List<String> parrafoList;

            StringBuilder parrafo = new StringBuilder();
            int alturaComienzoParrafo = 123;
            int salto = 33;
            parrafo.append("Yo " + pdfModel.getNombre() + " con DNI " + pdfModel.getDni());
            if (pdfModel.isMayorEdad()) {
                parrafo.append(", fecha de nacimiento " + pdfModel.getFechaNacimiento());
            } else {
                parrafo.append(", en calidad de " + pdfModel.getCalidadDe());
            }
            parrafo.append(" y domicilio en " + pdfModel.getDomicilio());
            parrafo.append(" en la localidad de " + pdfModel.getLocalidad());

            if (!pdfModel.isMayorEdad()) {

                parrafo.append(" AUTORIZO A ");
                parrafo.append(pdfModel.getNombreMenor() + (!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ? " con DNI " + pdfModel.getDniMenor() + " y": ""));
                parrafo.append(" con fecha de nacimiento " + pdfModel.getFechaNacimiento());
            }

            parrafo.append(" perteneciente al gimnasio " + pdfModel.getGimnasio());
            parrafo.append(" actualmente con cinturón " + pdfModel.getCinturonActual() + " y categoría " + pdfModel.getCategoria());

            if (!pdfModel.isInclusivo()) {
                parrafo.append(" (a dicha categoría le corresponde realizar: " + pdfModel.getPoomsae() + ").");
            } else {
                parrafo.append(" (a dicha categoría le corresponde realizar el KICHO o POOMSAE que deseen).");
            }
            parrafoList = organizaRenglones(parrafo.toString(), 16, null, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, 16, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto);
            salto = 15;
            parrafo = new StringBuilder();
            if (pdfModel.isMayorEdad()) {
                parrafo.append("Informo que voy a participar en el ");
            } else {
                parrafo.append("Informo que AUTORIZO a participar en el ");
            }
            parrafo.append(pdfModel.getNombreCampeonato() + " a celebrar el próximo " + pdfModel.getFechaCampeonato());
            parrafo.append(", en la dirección " + pdfModel.getDireccionCampeonato() + ".");
            parrafoList = organizaRenglones(parrafo.toString(), 14, null, true);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, 14, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto + 20);
            parrafo = new StringBuilder();
            parrafo.append("Por medio del presente escrito autorizo a los miembros de organización del ");
            parrafo.append("campeonato, la utilización de mi imagen en el país o en el extranjero por ");
            parrafo.append("cualquier medio ya sea impreso, electrónico o cualquier otro. ");
            parrafo.append("De igual manera, es mi deseo establecer que esta autorización es voluntaria y gratuita.");
            parrafoList = organizaRenglones(parrafo.toString(), 14, null, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, 14, salto);

            alturaComienzoParrafo += (parrafoList.size() * salto + 10);
            parrafo = new StringBuilder();
            parrafo.append("En Cumplimento de la Ley Orgánica de Protección de Datos 15/1999, de ");
            parrafo.append("13 de Diciembre, indico que la información que facilito voluntariamente es ");
            parrafo.append("para la creación de un fichero al objeto de poder gestionar adecuadamente ");
            parrafo.append("el campeonato de taekwondo. Al facilitar mis datos, autorizo al Comité ");
            parrafo.append("Organizador a utilizar mis datos para realizar listados, sorteos, ");
            parrafo.append("publicaciones en medios u otros asuntos relacionados con el campeonato.");
            parrafoList = organizaRenglones(parrafo.toString(), 14, null, false);

            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, 14, salto);

            if (pdfModel.isCinturonBlanco()) {
                alturaComienzoParrafo += (parrafoList.size() * salto + 10);
                parrafo = new StringBuilder();
                parrafo.append("Por otra parte, al no tener licencia federativa, eximo de toda ");
                parrafo.append("responsabilidad al comité organizador de cualquier lesión o daño que ");
                parrafo.append("se produjera el competidor o la competidora durante el evento o daños ");
                parrafo.append("que pudiera realizar a personas o material del pabellón.");
                parrafoList = organizaRenglones(parrafo.toString(), 14, null, true);

                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, 14, salto);
            }




            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            document.save(nombreArchivo(pdfModel, true, true));
            return new File(nombreArchivo(pdfModel, true, true));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdf", e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public File generarPdfMandato(PdfModel pdfModel) {

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

            List<String> parrafoList;

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
                parrafo.append("Para los menores, son los datos de " + pdfModel.getCalidadDe() + " y entre paréntesis el nombre del deportista.");
                parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
                generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
                alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);
            }

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("D./Dña. " + pdfModel.getNombre() + nombreMenor + " con DNI " + pdfModel.getDni() + ", en su propio ");
            parrafo.append("nombre y representación, con domicilio a efectos de notificaciones en " + pdfModel.getDomicilio() + " ");
            parrafo.append(pdfModel.getLocalidad() + " en concepto de MANDANTE.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Dice y otorga");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, true);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_BOLD, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que confiere MANDATO CON REPRESENTACIÓN a favor del representante del Club Championdo con domicilio ");
            parrafo.append("en Av Viñuelas 30, en concepto de MANDATARIO.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que el presente MANDATO, que se rige por los arts. 1709 a 1739 CC español se confiere para que se pueda llevar ");
            parrafo.append("a cabo la inscripción federativa del MANDANTE en la temporada " + hoy[0]);
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que el presente MANDATO se confiere para su actuación ante las dependencias federativas, personalmente o ");
            parrafo.append("a través de recursos online, en relación exclusivamente del asunto citado como objeto del MANDATO.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que este MANDATO tiene exclusiva vigencia para la inscripción federativa, finalizando la misma en el ");
            parrafo.append("momento en que se produzca la inscripción.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Que conoce y acepta el tratamiento de datos que llevará a cabo la federación, la cual legitima el mismo con ");
            parrafo.append("los requisitos normativos que cumplimenta el MANDATARIO por efecto de este MANDATO.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
            alturaComienzoParrafo += (parrafoList.size() * salto + saltoParrafo);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("En Tres Cantos, a " + hoy[0] + " de " + hoy[1] + " de " + hoy[2]);
            contentStream.endText();
            alturaComienzoParrafo += 30;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 180, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("EL MANDANTE");
            contentStream.endText();
            alturaComienzoParrafo += 85;

            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.newLineAtOffset( 80, page.getMediaBox().getHeight() - alturaComienzoParrafo);
            contentStream.showText("----------------------------------------------------------------------------");
            contentStream.endText();
            alturaComienzoParrafo += 30;

            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Acepto el MANDATO conferido y me obligo a cumplirlo de conformidad a las instrucciones del MANDANTE, y declaro ");
            parrafo.append("bajo mi responsabilidad de la veracidad y actualización de los datos facilitados para la inscripción federativa.");
            parrafoList = organizaRenglones(parrafo.toString(), tamanioFuente, null, false);
            generoParrafo(contentStream, page, parrafoList, alturaComienzoParrafo, PDType1Font.TIMES_ROMAN, tamanioFuente, salto);
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

            document.save(nombreArchivo(pdfModel, true, true));
            return new File(nombreArchivo(pdfModel, true, true));
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, "generarPdf", e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public void descargarPdf(PdfModel pdfModel, HttpServletResponse response) {

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
        String ruta = (rutaCompleta ? "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "files" + File.separator + getDateFolderName(pdfModel) : "");
        String ext = (extension ? ".pdf" : "");
        if(rutaCompleta) {
            File directorio = new File(getAbsolutePath() + ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
        }
        String fechaCampeonato = (StringUtils.isNullOrEmpty(pdfModel.getFechaCampeonato()) ? "" : pdfModel.getFechaCampeonato());
        if (pdfModel.isMayorEdad()) {
            return ruta + pdfModel.getDni() + fechaCampeonato + "-" + pdfModel.getIdInscripcion() + ext;
        } else {
            return ruta + pdfModel.getDni() + fechaCampeonato + clearData(pdfModel.getDniMenor()) + "-" + pdfModel.getIdInscripcion() + ext;
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

    private String clearData(String entrada) {
        return (entrada != null ? entrada.trim() : "");
    }

    private void generoParrafo(PDPageContentStream contentStream, PDPage page, List<String> parrafoList,
                                  int alturaPagina, PDType1Font fuente, int fontSize, int salto) throws IOException {
        int i = 0;
        for (String parrafo1: parrafoList) {
            contentStream.beginText();
            contentStream.setFont(fuente, fontSize);
            contentStream.newLineAtOffset( 50, page.getMediaBox().getHeight() - (alturaPagina + i));
            contentStream.showText(parrafo1);
            contentStream.endText();
            i=i+salto;
        }
    }

    private String getDateFolderName(PdfModel pdfModel) {
        String dateFolder = "";
        if(!StringUtils.isNullOrEmpty(pdfModel.getFechaCampeonato())) {
            String[] folderNameArray = pdfModel.getFechaCampeonato().split("-");
            for(int i=folderNameArray.length; i>0; i--) {
                dateFolder += folderNameArray[i - 1];
            }
            dateFolder += File.separator;
        } else {
            dateFolder = "generic" + File.separator;
        }
        return dateFolder;
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


    private List<String> organizaRenglones(String parrafo, int tamanioFuente, Double anchoParrafoMlimetros, boolean negrita) throws EmptyException {

        LoggerMapper.log(Level.INFO, "organizaRenglones", "parrafo: " + parrafo + " - tamanioFuente: " + tamanioFuente + " - anchoParrafoMlimetros: " + anchoParrafoMlimetros, PdfServiceImpl.class);
        List<String> renglones = new ArrayList<>();
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
                    tamanioNuevaPalabra += tamanioFuente/4 * (puntoEnMilimetros + sumoMayuscula);
                } else if (letrasTamanio4.contains(letra)) {
                    tamanioNuevaPalabra += tamanioFuente * (puntoEnMilimetros + sumoMayuscula);
                } else {
                    tamanioNuevaPalabra += tamanioFuente/2 * (puntoEnMilimetros + sumoMayuscula);
                }
            }
            if (parrafoUtilizado + tamanioNuevaPalabra > anchoParrafoMlimetros) {
                renglones.add(renglon.toString());
                renglon = new StringBuilder();
                parrafoUtilizado = tamanioNuevaPalabra;
            } else {
                parrafoUtilizado += tamanioNuevaPalabra;
            }
            renglon.append(palabra.concat(" "));
        }
        renglones.add(renglon.toString());
        LoggerMapper.log(Level.INFO, "organizaRenglones", "renglones: " + renglones + " - anchoParrafoMlimetros: " + anchoParrafoMlimetros, PdfServiceImpl.class);
        return renglones;
    }

}
