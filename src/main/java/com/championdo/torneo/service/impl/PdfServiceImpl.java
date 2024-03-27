package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.exception.EmptyException;
import com.championdo.torneo.model.*;
import com.championdo.torneo.service.DocumentManagerService;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import com.mysql.cj.util.StringUtils;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.Level;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private GimnasioService gimnasioService;

    @Autowired
    private DocumentManagerService documentManagerService;

    @Override
    public DocumentManagerModel generarPdfTorneo(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {

        LoggerMapper.methodIn(Level.INFO, Utils.obtenerNombreMetodo(), pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Text
            contentStream.beginText();
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - 80);
            if (pdfModel.isMayorEdad()) {
                contentStream.showText("AUTORIZACIÓN DE MAYORES DE 18 AÑOS");
            } else if (pdfModel.isInclusivo()) {
                contentStream.showText("AUTORIZACIÓN DE INSCRIPCIÓN INCLUSIVA");
            } else {
                contentStream.showText("AUTORIZACIÓN PARA MENORES DE 18 AÑOS");
            }
            contentStream.endText();

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo = new StringBuilder();
            int heightStartParagraph = 123;
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

            parrafo.append(" perteneciente a ").append(pdfModel.getGimnasio());
            parrafo.append(" actualmente con cinturón ").append(pdfModel.getCinturonActual()).append(" y categoría ").append(pdfModel.getCategoria());

            if (!pdfModel.isInclusivo()) {
                parrafo.append(" (a dicha categoría le corresponde realizar: ").append(pdfModel.getPoomsae()).append(").");
            } else {
                parrafo.append(" (a dicha categoría le corresponde realizar el KICHO o POOMSAE que deseen).");
            }
            tamanioFuente = 16;
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);

            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            heightStartParagraph += (parrafoList.size() * salto);
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

            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);

            heightStartParagraph += (parrafoList.size() * salto + 20);
            parrafo = new StringBuilder();
            parrafo.append("Por medio del presente escrito autorizo a los miembros de organización del ");
            parrafo.append("campeonato, la utilización de mi imagen en el país o en el extranjero por ");
            parrafo.append("cualquier medio ya sea impreso, electrónico o cualquier otro. ");
            parrafo.append("De igual manera, es mi deseo establecer que esta autorización es voluntaria y gratuita.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, false, false);

            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            heightStartParagraph += (parrafoList.size() * salto + 10);
            parrafo = new StringBuilder();
            parrafo.append("En Cumplimento de la Ley Orgánica de Protección de Datos 15/1999, de ");
            parrafo.append("13 de Diciembre, indico que la información que facilito voluntariamente es ");
            parrafo.append("para la creación de un fichero al objeto de poder gestionar adecuadamente ");
            parrafo.append("el campeonato de taekwondo. Al facilitar mis datos, autorizo al Comité ");
            parrafo.append("Organizador a utilizar mis datos para realizar listados, sorteos, ");
            parrafo.append("publicaciones en medios u otros asuntos relacionados con el campeonato.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, false, false);

            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

            if (pdfModel.isCinturonBlanco()) {
                heightStartParagraph += (parrafoList.size() * salto + 10);
                parrafo = new StringBuilder();
                parrafo.append("Por otra parte, al no tener licencia federativa, eximo de toda ");
                parrafo.append("responsabilidad al comité organizador de cualquier lesión o daño que ");
                parrafo.append("se produjera el competidor o la competidora durante el evento o daños ");
                parrafo.append("que pudiera realizar a personas o material del pabellón.");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, true, false);

                generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            }




            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();
            DocumentManagerModel documentManagerModel = new DocumentManagerModel();
            nombreArchivo(documentManagerModel, pdfModel, true, Constantes.SECCION_TORNEO);
            return createFileAndSaveDM(document, documentManagerModel, createWithSignatureOrCreateFinalDocument);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public DocumentManagerModel createPdfFederativeLicenseMandate(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {

        LoggerMapper.methodIn(Level.INFO, Utils.obtenerNombreMetodo(), pdfModel, getClass());
        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            int heightStartParagraph = commonFederativeLicenseMandate(pdfModel, contentStream, page);

            if(createWithSignatureOrCreateFinalDocument) {
                commonSignature(contentStream, page, heightStartParagraph);
                heightStartParagraph += 85;

                List<String> parrafoList = new ArrayList<>();
                StringBuilder parrafo = new StringBuilder();

                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.newLineAtOffset( 80, page.getMediaBox().getHeight() - heightStartParagraph);
                contentStream.showText("----------------------------------------------------------------------------");
                contentStream.endText();
                heightStartParagraph += 30;

                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - heightStartParagraph);
                contentStream.showText("A rellenar por el MANDATARIO");
                contentStream.endText();
                heightStartParagraph += 30;

                //salto = 15;tamanioFuente = 14;
                parrafo.append("Acepto el MANDATO conferido y me obligo a cumplirlo de conformidad a las instrucciones del MANDANTE, y declaro ");
                parrafo.append("bajo mi responsabilidad de la veracidad y actualización de los datos facilitados para la inscripción federativa.");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, 180.0, false, false);
                generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, 14, null, 15);
                heightStartParagraph += (parrafoList.size() * 35);

                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.newLineAtOffset( 100, page.getMediaBox().getHeight() - heightStartParagraph);
                contentStream.showText("En                  , a      de                de");
                contentStream.endText();


            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            }

            contentStream.close();
            DocumentManagerModel documentManagerModel = new DocumentManagerModel();
            documentManagerModel.setNeedsSignature(Boolean.TRUE);
            nombreArchivo(documentManagerModel, pdfModel, true, Constantes.SECCION_MANDATO);
            return createFileAndSaveDM(document, documentManagerModel, createWithSignatureOrCreateFinalDocument);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
        return null;

    }

    @Override
    public DocumentManagerModel generarPdfAutorizacionMayor18(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {
        return commonCreateDocument(pdfModel, createWithSignatureOrCreateFinalDocument, Constantes.SECCION_AUTORIZACION_MAYOR_18);
    }

    @Override
    public DocumentManagerModel generarPdfAutorizacionMenor18(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {
        return commonCreateDocument(pdfModel, createWithSignatureOrCreateFinalDocument, Constantes.SECCION_AUTORIZACION_MENOR_18);
    }

    @Override
    public DocumentManagerModel generarPdfNormativaSEPA(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {

        LoggerMapper.methodIn(Level.INFO, Utils.obtenerNombreMetodo(), pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            List<String> parrafoList = new ArrayList<>();

            StringBuilder parrafo;
            int heightStartParagraph = 40;
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
            parrafoList.add(pdfModel.getGimnasio().toUpperCase());
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, 170, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 22;
            tamanioFuente = 16;
            parrafoList = new ArrayList<>();
            parrafoList.add("TRATAMIENTO DE DATOS DE CLIENTES");
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, 135, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 20;
            tamanioFuente = 20;
            parrafoList = new ArrayList<>();
            parrafoList.add("NORMATIVA SEPA");
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, 205, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            //salto = 20;tamanioFuente = 20;
            dejoDeMargenPosterior = 20;
            parrafoList = new ArrayList<>();
            parrafoList.add("MANDATO ADEUDO DIRECTO SEPA");
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, 120, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 10;
            parrafoList = new ArrayList<>();
            parrafoList.add("DATOS DEL DEPORTISTA (Quien entrena en el gimnasio)");
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

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
                generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
                heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

                encuadrarParrafo(contentStream, page, parrafoList, heightStartParagraph, salto, dejoDeMargenPosterior, x, width, 0);

                //salto = 15;tamanioFuente = 14;
                dejoDeMargenPosterior = 10;
                parrafo = new StringBuilder();
                parrafo.append("PERSONA AUTORIZADORA EN CALIDAD DE ").append(pdfModel.getCalidadDe().toUpperCase()).append(" (Para deportistas menores de edad)");
                parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
                generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
                heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            }

            heightStartParagraph = rellenarAdulto(pdfModel, heightStartParagraph, contentStream, page);

            salto = 20;
            tamanioFuente = 20;
            parrafo = new StringBuilder();
            parrafo.append("DATOS BANCARIOS");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, 205, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 20;
            parrafoList = new ArrayList<>();
            parrafoList.add("Titular: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getTitular() : Constantes.ERROR_DATOS_BANCARIOS));
            parrafoList.add("IBAN: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getIban() : Constantes.ERROR_DATOS_BANCARIOS));
            parrafoList.add("CÓDIGO SWIFT/BIC: " + (pdfModel.getCuentaBancaria() != null ? pdfModel.getCuentaBancaria().getSwift() : Constantes.ERROR_DATOS_BANCARIOS));
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            encuadrarParrafo(contentStream, page, parrafoList, heightStartParagraph, salto, dejoDeMargenPosterior, x, width, 0);

            float heightAgregado = 0;
            salto = 12;
            tamanioFuente = 12;
            dejoDeMargenPosterior = 0;
            parrafo = new StringBuilder();
            parrafo.append("En nombre de la empresa tratamos la información que nos facilita con el fin de prestarles el servicio solicitado, ");
            parrafo.append("realizar la facturación del mismo. Los datos proporcionados se conservarán mientras se ");
            parrafo.append("mantenga la relación comercial o durante los años necesarios para cumplir con las obligaciones legales. ");
            parrafo.append("Los datos no se cederán a terceros salvo en los casos en que exista una obligación legal. Usted tiene ");
            parrafo.append("derecho a obtener confirmación sobre si en ".concat(pdfModel.getGimnasio()).concat(" estamos tratando sus datos "));
            parrafo.append("personales por tanto tiene derecho a acceder a sus datos personales, rectificar los datos inexactos o ");
            parrafo.append("solicitar su supresión cuando los datos ya no sean necesarios.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            //salto = 12;tamanioFuente = 12;
            dejoDeMargenPosterior = 15;
            parrafo = new StringBuilder();
            parrafo.append("Asimismo solicito su autorización para ofrecerle productos y servicios relacionados con los solicitados y ");
            parrafo.append("fidelizarle como cliente.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            cuadradoSeleccion(contentStream, page, heightStartParagraph);
            salto = 15;
            tamanioFuente = 14;
            dejoDeMargenPosterior = 0;
            parrafo = new StringBuilder();
            parrafo.append("  SI");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            cuadradoSeleccion(contentStream, page, heightStartParagraph);
            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("NO");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);
            heightAgregado += (parrafoList.size() * salto) + dejoDeMargenPosterior;

            //salto = 25;tamanioFuente = 14;
            dejoDeMargenPosterior = 30;
            parrafo = new StringBuilder();
            parrafo.append("Por favor indique SI o NO");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            encuadrarParrafo(contentStream, page, parrafoList, heightStartParagraph, salto, dejoDeMargenPosterior, x, width, heightAgregado);

            //salto = 15;tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Por la presente autorizo a los titulares de ".concat(pdfModel.getGimnasio()).concat(" a enviar instrucciones a la "));
            parrafo.append("entidad arriba indicada para efectuar los adeudos en su cuenta bancaria y me comprometo a ");
            parrafo.append("realizar los pagos en los plazos establecidos responsabilizándome de los costes generados en ");
            parrafo.append("caso de devolución.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

            //salto = 35;
            tamanioFuente = 18;
            parrafo = new StringBuilder();
            parrafo.append("Fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]).append("           FIRMA:");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
            //heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

            /* Image
             PDImageXObject image = PDImageXObject.createFromFile("src/main/java/com/damian/objetivos/util/400.jpg", document);
             contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
             */

            contentStream.close();

            DocumentManagerModel documentManagerModel = new DocumentManagerModel();
            nombreArchivo(documentManagerModel, pdfModel, true, Constantes.SECCION_NORMATIVA_SEPA);
            return createFileAndSaveDM(document, documentManagerModel, createWithSignatureOrCreateFinalDocument);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
        return null;
    }

    @Override
    public DocumentManagerModel generarPdfAutorizaWhatsApp(PdfModel pdfModel, boolean createWithSignatureOrCreateFinalDocument) {
        return commonCreateDocument(pdfModel, createWithSignatureOrCreateFinalDocument, Constantes.SECCION_WHATSAPP);
    }

    /**
     *
     * @param seccion
     *
     * INFORMACIÓN valores:
     * Constantes.SECCION_TORNEO
     * Constantes.SECCION_MANDATO
     * Constantes.SECCION_AUTORIZACION_MAYOR_18
     * Constantes.SECCION_AUTORIZACION_MENOR_18
     * Constantes.SECCION_NORMATIVA_SEPA
     */
    @Override
    public void descargarArchivo(PdfModel pdfModel, HttpServletResponse response, String seccion) {

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename = " + nombreArchivo(null, pdfModel, false, seccion);
        response.setHeader(headerKey, headerValue);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] file = Files.readAllBytes(Paths.get(nombreArchivo(null, pdfModel, true, seccion)));
            outputStream.write(file, 0, file.length);
            outputStream.close();
        } catch (IOException e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
        }
    }
    @Override
    public boolean subirArchivo(PdfModel pdfModel, MultipartFile file, String seccion) {
        boolean answer = false;
        if (!file.isEmpty()) {
            try {
                pdfModel.setExtension(getFileExtension(file));
                DocumentManagerModel documentManagerModel = new DocumentManagerModel();
                nombreArchivo(documentManagerModel, pdfModel, true, seccion);
                file.transferTo(new File(documentManagerService.getAbsolutePath() + documentManagerModel.getFullPath()));
                documentManagerService.add(documentManagerModel);
                answer = true;
            } catch (IOException e) {
                LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo(), e.getMessage(), PdfServiceImpl.class);
            }
        }
        return answer;
    }

    @Override
    public PdfModel getImpresion(TournamentRegistrationModel tournamentRegistrationModel) {

        PdfModel pdfModel = new PdfModel();
        if (tournamentRegistrationModel != null){
            if (tournamentRegistrationModel.getAuthorizerIdCard() != null) {
                pdfModel.setMayorEdad(false);
                pdfModel.setDni(tournamentRegistrationModel.getAuthorizerIdCard());
                pdfModel.setDniMenor(tournamentRegistrationModel.getRegisteredIdCard());
            } else {
                pdfModel.setMayorEdad(true);
                pdfModel.setDni(tournamentRegistrationModel.getRegisteredIdCard());
            }
            pdfModel.setIdInscripcion(tournamentRegistrationModel.getId());
            pdfModel.setFechaCampeonato(tournamentRegistrationModel.getTournamentDate());
        }
        return pdfModel;
    }

    @Override
    public PdfModel getPdfInscripcionTaekwondo (InscripcionTaekwondoModel inscripcionTaekwondoModel) {

        PdfModel pdfModel = new PdfModel();
        GimnasioModel gimnasioModel = gimnasioService.findById(inscripcionTaekwondoModel.getCodigoGimnasio());
        pdfModel.setIdInscripcion(inscripcionTaekwondoModel.getId());
        pdfModel.setNombre(inscripcionTaekwondoModel.getMayorNombre() + " " + inscripcionTaekwondoModel.getMayorApellido1()
                + (inscripcionTaekwondoModel.getMayorApellido2() != null ? " " + inscripcionTaekwondoModel.getMayorApellido2() : ""));
        pdfModel.setDni(inscripcionTaekwondoModel.getMayorDni());
        pdfModel.setTelefono(inscripcionTaekwondoModel.getMayorTelefono());
        pdfModel.setCorreo(inscripcionTaekwondoModel.getMayorCorreo());
        pdfModel.setFechaNacimiento(Utils.date2String(inscripcionTaekwondoModel.getMayorFechaNacimiento()));
        pdfModel.setGimnasio(gimnasioModel.getNombreGimnasio());
        pdfModel.setDireccionGimnasio(gimnasioModel.getDomicilioCalle() + " "
                + (StringUtils.isNullOrEmpty(gimnasioModel.getDomicilioNumero()) ? "" : gimnasioModel.getDomicilioNumero() + " ")
                + (StringUtils.isNullOrEmpty(gimnasioModel.getDomicilioOtros()) ? "" : gimnasioModel.getDomicilioOtros() + " ")
                + gimnasioModel.getDomicilioLocalidad() + " (" + gimnasioModel.getDomicilioCp() + ")");
        if (!StringUtils.isNullOrEmpty(inscripcionTaekwondoModel.getMayorDomicilioCalle())) {
            pdfModel.setDomicilio(inscripcionTaekwondoModel.getMayorDomicilioCalle() + " " + inscripcionTaekwondoModel.getMayorDomicilioNumero()
                    + " " + inscripcionTaekwondoModel.getMayorDomicilioOtros());
            pdfModel.setLocalidad(inscripcionTaekwondoModel.getMayorDomicilioLocalidad() + " (" + inscripcionTaekwondoModel.getMayorDomicilioCp()
                    + ")" + (inscripcionTaekwondoModel.getMayorPais() != null ? " - " + inscripcionTaekwondoModel.getMayorPais() : ""));
        }
        if (!StringUtils.isNullOrEmpty(inscripcionTaekwondoModel.getMayorCalidad())) {
            pdfModel.setCalidadDe(inscripcionTaekwondoModel.getMayorCalidad());
            pdfModel.setNombreMenor(inscripcionTaekwondoModel.getAutorizadoNombre() + " " + inscripcionTaekwondoModel.getAutorizadoApellido1()
                    + (inscripcionTaekwondoModel.getAutorizadoApellido2() != null ? " " + inscripcionTaekwondoModel.getAutorizadoApellido2() : ""));
            pdfModel.setDniMenor(inscripcionTaekwondoModel.getAutorizadoDni());
            pdfModel.setFechaNacimientoMenor(Utils.date2String(inscripcionTaekwondoModel.getAutorizadoFechaNacimiento()));
        }
        if (!StringUtils.isNullOrEmpty(inscripcionTaekwondoModel.getIban())) {
            CuentaBancariaModel cuentaBancaria = new CuentaBancariaModel();
            cuentaBancaria.setTitular(inscripcionTaekwondoModel.getTitularCuenta());
            cuentaBancaria.setIban(inscripcionTaekwondoModel.getIban());
            cuentaBancaria.setSwift(inscripcionTaekwondoModel.getSwift());
            pdfModel.setCuentaBancaria(cuentaBancaria);
        }

        return pdfModel;
    }

    @Override
    public PdfModel getPdfMandato(MandatoModel mandatoModel) {
        PdfModel pdfModel = new PdfModel();
        GimnasioModel gimnasioModel = gimnasioService.findById(mandatoModel.getCodigoGimnasio());
        pdfModel.setIdInscripcion(mandatoModel.getId());
        pdfModel.setNombre(mandatoModel.getNombreMandante() + " " + mandatoModel.getApellido1Mandante()
                + (mandatoModel.getApellido2Mandante() != null ? " " + mandatoModel.getApellido2Mandante() : ""));
        pdfModel.setDni(mandatoModel.getDniMandante());
        pdfModel.setCorreo(mandatoModel.getCorreoMandante());
        pdfModel.setGimnasio(gimnasioModel.getNombreGimnasio());
        pdfModel.setDireccionGimnasio(gimnasioModel.getDomicilioCalle() + " "
                + (StringUtils.isNullOrEmpty(gimnasioModel.getDomicilioNumero()) ? "" : gimnasioModel.getDomicilioNumero() + " ")
                + (StringUtils.isNullOrEmpty(gimnasioModel.getDomicilioOtros()) ? "" : gimnasioModel.getDomicilioOtros() + " ")
                + gimnasioModel.getDomicilioLocalidad() + " (" + gimnasioModel.getDomicilioCp() + ")");
        pdfModel.setMayorEdad(mandatoModel.isAdulto());
        if (!StringUtils.isNullOrEmpty(mandatoModel.getDomicilioCalle())) {
            pdfModel.setDomicilio(mandatoModel.getDomicilioCalle() + " " + mandatoModel.getDomicilioNumero()
                    + " " + mandatoModel.getDomicilioOtros());
            pdfModel.setLocalidad(mandatoModel.getDomicilioLocalidad() + " (" + mandatoModel.getDomicilioCp()
                    + ")" + (mandatoModel.getPais() != null ? " - " + mandatoModel.getPais() : ""));
        }
        if (!StringUtils.isNullOrEmpty(mandatoModel.getCalidad()) || !StringUtils.isNullOrEmpty(mandatoModel.getCalidadOtro())) {
            pdfModel.setCalidadDe(!StringUtils.isNullOrEmpty(mandatoModel.getCalidad()) ? mandatoModel.getCalidad() : mandatoModel.getCalidadOtro());
            pdfModel.setNombreMenor(mandatoModel.getNombreAutorizado() + " " + mandatoModel.getApellido1Autorizado()
                    + (mandatoModel.getApellido2Autorizado() != null ? " " + mandatoModel.getApellido2Autorizado() : ""));
            pdfModel.setDniMenor(mandatoModel.getDniAutorizado());
        }
        return pdfModel;
    }

    @Override
    public String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(dotIndex);
            }
        }
        return "";
    }

    @Override
    public String getTempFolder() {
        String tempFolder = "src".concat(File.separator).concat("main").concat(File.separator)
                .concat("resources").concat(File.separator).concat("static").concat(File.separator)
                .concat("files").concat(File.separator).concat("temp");
        File tempDirectory = new File(tempFolder);
        if (!tempDirectory.exists()) {
            if(!tempDirectory.mkdirs()) {
                LoggerMapper.methodIn(Level.ERROR, Utils.obtenerNombreMetodo(), "Problemas creando carpeta ".concat(tempDirectory.getName()), this.getClass());
            }
        }
        tempFolder+=File.separator;
        return tempFolder;
    }

    @Override
    public void deleteFilesTaekwondoRegistration(InscripcionTaekwondoModel inscripcionTaekwondoModel, User usuario) {
        if (inscripcionTaekwondoModel.isAutorizadoMenor()) {
            documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(inscripcionTaekwondoModel.getId(), Constantes.SECCION_AUTORIZACION_MENOR_18, usuario.getUsername());
        } else {
            documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(inscripcionTaekwondoModel.getId(), Constantes.SECCION_AUTORIZACION_MAYOR_18, usuario.getUsername());
        }
        if (inscripcionTaekwondoModel.isDomiciliacionSEPA()) {
            if (inscripcionTaekwondoModel.isDomiciliacionSEPAFirmada()) {
                documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(inscripcionTaekwondoModel.getId(), Constantes.SECCION_NORMATIVA_SEPA_FIRMADO, usuario.getUsername());
            } else {
                documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(inscripcionTaekwondoModel.getId(), Constantes.SECCION_NORMATIVA_SEPA, usuario.getUsername());
            }
        }
        if (inscripcionTaekwondoModel.isMayorAutorizaWhatsApp()) {
            documentManagerService.deleteByIdOriginalOperativeAndSectionAndIdCard(inscripcionTaekwondoModel.getId(), Constantes.SECCION_WHATSAPP, usuario.getUsername());
        }
    }

    @Override
    public void eraseByIdOriginalOperativeAndSectionAndIdCard(Integer idOriginalOperative, String section, String idCard) {
        documentManagerService.eraseByIdOriginalOperativeAndSectionAndIdCard(idOriginalOperative, section, idCard);
    }

    private DocumentManagerModel commonCreateDocument (PdfModel pdfModel, boolean withSignature, String section) {

        LoggerMapper.methodIn(Level.INFO, Utils.obtenerNombreMetodo().concat(" ").concat(section), pdfModel, this.getClass());
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            int heightStartParagraph;
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            if (Constantes.SECCION_AUTORIZACION_MAYOR_18.equals(section)) {
                heightStartParagraph = commonAuthorizationOver18(pdfModel, contentStream, page);
            } else if (Constantes.SECCION_AUTORIZACION_MENOR_18.equals(section)) {
                heightStartParagraph = commonAuthorizationUnder18(pdfModel, contentStream, page);
            } else if (Constantes.SECCION_WHATSAPP.equals(section)) {
                heightStartParagraph = commonAuthorizationWhatsApp(pdfModel, contentStream, page);
            } else {
                return null;
            }

            if(withSignature) {
                commonSignature(contentStream, page, heightStartParagraph);
            }

            contentStream.close();

            DocumentManagerModel documentManagerModel = new DocumentManagerModel();
            documentManagerModel.setNeedsSignature(Boolean.TRUE);
            nombreArchivo(documentManagerModel, pdfModel, true, section);
            return createFileAndSaveDM(document, documentManagerModel, withSignature);
        } catch (Exception e) {
            LoggerMapper.log(Level.ERROR, Utils.obtenerNombreMetodo().concat(" ").concat(section), e.getMessage(), PdfServiceImpl.class);
        }
        return null;
    }

    private String nombreArchivo(DocumentManagerModel documentManagerModel, PdfModel pdfModel, boolean rutaCompleta, @NotNull String section) {

        if (StringUtils.isNullOrEmpty(pdfModel.getExtension())) {
            pdfModel.setExtension(Constantes.EXTENSION_PDF);
        }
        String ruta = (rutaCompleta ? "src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "files" + File.separator + section + tounamentDate(pdfModel) : "");
        if(rutaCompleta) {
            File directorio = new File(documentManagerService.getAbsolutePath() + ruta);
            if (!directorio.exists()) {
                if(!directorio.mkdirs()) {
                    LoggerMapper.methodIn(Level.ERROR, Utils.obtenerNombreMetodo(), "Problemas creando carpeta ".concat(directorio.getName()), this.getClass());
                }
            }
        }
        if (documentManagerModel == null) {
            documentManagerModel = new DocumentManagerModel();
        }
        if (pdfModel.isMayorEdad()) {
            documentManagerModel.setFilename(section + pdfModel.getDni() + "-" + pdfModel.getIdInscripcion());
        } else {
            documentManagerModel.setFilename(section + pdfModel.getDni() + (!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ?
                    "-" + pdfModel.getDniMenor().trim() : "") + "-" + pdfModel.getIdInscripcion());
        }

        documentManagerModel.setExtension(pdfModel.getExtension());
        documentManagerModel.setSection(section);
        documentManagerModel.setPath(ruta);
        documentManagerModel.setIdCard(pdfModel.getDni());
        documentManagerModel.setIdOriginalOperative(pdfModel.getIdInscripcion());
        documentManagerModel.setCreationDate(new Date());

        return documentManagerModel.getFullPath();
    }

    private String tounamentDate(PdfModel pdfModel) {
        StringBuilder dateFolder = new StringBuilder();
        if(pdfModel != null && !StringUtils.isNullOrEmpty(pdfModel.getFechaCampeonato())) {
            String[] folderNameArray = pdfModel.getFechaCampeonato().split("-");
            dateFolder.append(File.separator);
            dateFolder.append(Constantes.SECCION_TORNEO);
            for(int i=folderNameArray.length; i>0; i--) {
                dateFolder.append(folderNameArray[i - 1]);
            }
        }
        dateFolder.append(File.separator);
        return dateFolder.toString();
    }

    private DocumentManagerModel createFileAndSaveDM(PDDocument document, DocumentManagerModel documentManagerModel, boolean createWithSignatureOrCreateFinalDocument) throws Exception {
        if (!createWithSignatureOrCreateFinalDocument) {
            documentManagerModel.setPath(getTempFolder());
        }
        document.save(documentManagerModel.getFullPath());
        new File(documentManagerModel.getFullPath());
        if (createWithSignatureOrCreateFinalDocument) {
            documentManagerModel = documentManagerService.add(documentManagerModel);
        }
        return documentManagerModel;
    }

    private int rellenarAdulto(PdfModel pdfModel, int heightStartParagraph, PDPageContentStream contentStream, PDPage page) throws IOException, EmptyException {

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

        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);

        heightStartParagraph += (parrafoList.size() * salto + dejoDeMargenPosterior);

        encuadrarParrafo(contentStream, page, parrafoList, heightStartParagraph, salto, dejoDeMargenPosterior, 45, 510, 0);

        return heightStartParagraph;
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


    private List<String> organizaRenglones(List<String> parrafoList, String parrafo, int tamanioFuente, 
                                           Double anchoParrafoMlimetros, boolean negrita, boolean agrego) throws EmptyException {

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
        return parrafoList;
    }

    private void encuadrarParrafo(PDPageContentStream contentStream, PDPage page, List<String> parrafoList, int heightStartParagraph, int salto,
                                  int dejoDeMargenPosterior, float x, float width, float heightAgregado) throws IOException {

        // Dibujar el cuadrado
        contentStream.addRect(x, page.getMediaBox().getHeight() - heightStartParagraph + dejoDeMargenPosterior + 10,
                width, parrafoList.size() * salto + 5 + heightAgregado);
        contentStream.setLineWidth(2); // Ancho del borde
        contentStream.setStrokingColor(0, 0, 0); // Color del borde (negro)
        contentStream.stroke();
    }

    private void cuadradoSeleccion(PDPageContentStream contentStream, PDPage page, int heightStartParagraph) throws IOException {

        // Dibujar el cuadrado x=45f + 30f, width=10f, height=10f
        contentStream.addRect(75f, page.getMediaBox().getHeight() - heightStartParagraph, 10f, 10f);
        contentStream.setLineWidth(1); // Ancho del borde
        contentStream.setStrokingColor(0, 0, 0); // Color del borde (negro)
        contentStream.stroke();
    }

    private int commonFederativeLicenseMandate(PdfModel pdfModel, PDPageContentStream contentStream, PDPage page) throws IOException, EmptyException {

        // Text
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
        contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
        contentStream.showText("Mandato específico para la inscripción federativa");
        contentStream.endText();

        List<String> parrafoList = new ArrayList<>();

        StringBuilder parrafo;
        int heightStartParagraph = 90;
        int salto;
        int tamanioFuente;
        String nombreMenor = "";
        Calendar calendar = GregorianCalendar.getInstance();

        if(!StringUtils.isNullOrEmpty(pdfModel.getCalidadDe())) {
            nombreMenor = " (" + pdfModel.getNombreMenor() + ")";
            salto = 15;
            tamanioFuente = 14;
            parrafo = new StringBuilder();
            parrafo.append("Para los menores o solicitud inclusiva, son los datos de");
            parrafo.append(pdfModel.getCalidadDe().equalsIgnoreCase("Madre") ? " la " : "l ");
            parrafo.append(pdfModel.getCalidadDe()).append(" y entre paréntesis el nombre del deportista.");
            parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
            generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
            heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);
        }

        salto = 15;
        tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("D./Dña. ").append(pdfModel.getNombre()).append(nombreMenor).append(" con DNI ").append(pdfModel.getDni()).append(", en su propio ");
        parrafo.append("nombre y representación, con domicilio a efectos de notificaciones en ").append(pdfModel.getDomicilio()).append(" ");
        parrafo.append(pdfModel.getLocalidad()).append(" en concepto de MANDANTE.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Dice y otorga");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Que confiere MANDATO CON REPRESENTACIÓN a favor del representante de ".concat(pdfModel.getGimnasio()).concat(" con domicilio "));
        parrafo.append("en ".concat(pdfModel.getDireccionGimnasio()).concat(", en concepto de MANDATARIO."));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Que el presente MANDATO, que se rige por los arts. 1709 a 1739 CC español se confiere para que se pueda llevar ");
        parrafo.append("a cabo la inscripción federativa del MANDANTE en la temporada ").append(Utils.calculateSeason(calendar.getTime()));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Que el presente MANDATO se confiere para su actuación ante las dependencias federativas, personalmente o ");
        parrafo.append("a través de recursos online, en relación exclusivamente del asunto citado como objeto del MANDATO.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Que este MANDATO tiene exclusiva vigencia para la inscripción federativa, finalizando la misma en el ");
        parrafo.append("momento en que se produzca la inscripción.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Que conoce y acepta el tratamiento de datos que llevará a cabo la federación, la cual legitima el mismo con ");
        parrafo.append("los requisitos normativos que cumplimenta el MANDATARIO por efecto de este MANDATO.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        return heightStartParagraph;
    }

    private int commonAuthorizationOver18(PdfModel pdfModel, PDPageContentStream contentStream, PDPage page) throws IOException, EmptyException {

        // Text
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
        contentStream.showText("AUTORIZACIÓN DE MAYORES DE 18 AÑOS");
        contentStream.endText();

        List<String> parrafoList = new ArrayList<>();

        StringBuilder parrafo;
        int heightStartParagraph = 90;
        int salto;
        int tamanioFuente;

        salto = 15;
        tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Yo D./Dña. ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni());
        parrafo.append(", fecha de nacimiento ").append(pdfModel.getFechaNacimiento()).append(" y domicilio en ").append(pdfModel.getDomicilio()).append(" ");
        parrafo.append(pdfModel.getLocalidad().concat(" perteneciente a ").concat(pdfModel.getGimnasio()));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("INFORMO QUE ENTRENO EN ".concat(pdfModel.getGimnasio()).concat(" Y PARTICIPO VOLUNTARIAMENTE EN LOS CAMPEONATOS Y "));
        parrafo.append("ENTRENAMIENTOS QUE PARTICIPEN LOS ALUMNOS DE ".concat(pdfModel.getGimnasio()).concat("."));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Por medio del presente escrito autorizo a los miembros de ".concat(pdfModel.getGimnasio()).concat(", a la utilización "));
        parrafo.append("de mi imagen en el país o en el extranjero por cualquier medio ya sea impreso, electrónico ");
        parrafo.append("o cualquier otro. De igual manera, es mi deseo establecer que esta autorización es voluntaria ");
        parrafo.append("y gratuita.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("En cumplimiento con la Ley Orgánica de Protección de Datos 15/1999, de 13 de Diciembre, ");
        parrafo.append("indico que la información que facilito voluntariamente es para la creación de un fichero al ");
        parrafo.append("objeto de poder gestionar adecuadamente los datos. Al facilitar mis datos, autorizo a ");
        parrafo.append(pdfModel.getGimnasio().concat(" a utilizar mis datos para realizar listados, sorteos, publicaciones "));
        parrafo.append("en medios y otros asuntos relacionados con ".concat(pdfModel.getGimnasio()).concat("."));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Por otra parte, eximo de toda responsabilidad a ".concat(pdfModel.getGimnasio()).concat(" de cualquier lesión "));
        parrafo.append("o daño que se produjera el alumno o la alumna durante los entrenamientos y campeonatos ");
        parrafo.append("a los que acuda o daños que pudiera realizar a personas o materiales.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        return heightStartParagraph;
    }

    private int commonAuthorizationUnder18(PdfModel pdfModel, PDPageContentStream contentStream, PDPage page) throws IOException, EmptyException {

        // Text
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 18);
        contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
        contentStream.showText("AUTORIZACIÓN DE MENORES DE 18 AÑOS");
        contentStream.endText();

        List<String> parrafoList = new ArrayList<>();

        StringBuilder parrafo;
        int heightStartParagraph = 90;
        int salto;
        int tamanioFuente;

        salto = 15;
        tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Yo D./Dña. ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni()).append(" ");
        parrafo.append("en calidad de ").append(pdfModel.getCalidadDe()).append(" y domicilio en ").append(pdfModel.getDomicilio()).append(" ");
        parrafo.append(pdfModel.getLocalidad());
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("INFORMO QUE AUTORIZO A ENTRENAR EN ".concat(pdfModel.getGimnasio()).concat(" Y A PARTICIPAR EN LOS CAMPEONATOS Y "));
        parrafo.append("ENTRENAMIENTOS QUE PARTICIPEN LOS ALUMNOS DE ".concat(pdfModel.getGimnasio()).concat(" A:"));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, true, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("D./Dña. ").append(pdfModel.getNombreMenor());
        parrafo.append(!StringUtils.isNullOrEmpty(pdfModel.getDniMenor()) ? " con DNI " + pdfModel.getDniMenor() + " y " : " ");
        parrafo.append("con fecha de nacimiento ").append(pdfModel.getFechaNacimientoMenor());
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Por medio del presente escrito autorizo a los miembros de ".concat(pdfModel.getGimnasio()).concat(", a la utilización "));
        parrafo.append("de la imagen de ").append(pdfModel.getNombreMenor()).append(" en el país o en el extranjero ");
        parrafo.append("por cualquier medio ya sea impreso, electrónico o cualquier otro. De igual manera, es mi ");
        parrafo.append("deseo establecer que esta autorización es voluntaria y gratuita.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("En cumplimiento con la Ley Orgánica de Protección de Datos 15/1999, de 13 de Diciembre, ");
        parrafo.append("indico que la información que facilito voluntariamente es para la creación de un fichero al ");
        parrafo.append("objeto de poder gestionar adecuadamente los datos. Al facilitar mis datos, autorizo a ");
        parrafo.append(pdfModel.getGimnasio().concat(" a utilizar mis datos para realizar listados, sorteos, publicaciones "));
        parrafo.append("en medios y otros asuntos relacionados con ".concat(pdfModel.getGimnasio()).concat("."));
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Por otra parte, eximo de toda responsabilidad a ".concat(pdfModel.getGimnasio()).concat(" de cualquier lesión "));
        parrafo.append("o daño que se produjera el alumno o la alumna durante los entrenamientos y campeonatos ");
        parrafo.append("a los que acuda o daños que pudiera realizar a personas o materiales.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_BOLD, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        return heightStartParagraph;
    }

    private int commonAuthorizationWhatsApp(PdfModel pdfModel, PDPageContentStream contentStream, PDPage page) throws IOException, EmptyException {


        // Text
        contentStream.beginText();
        contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
        contentStream.newLineAtOffset( 130, page.getMediaBox().getHeight() - 50);
        contentStream.showText("AUTORIZACIÓN GRUPO DE WHATSAPP");
        contentStream.endText();

        List<String> parrafoList = new ArrayList<>();

        StringBuilder parrafo;
        int heightStartParagraph = 90;
        int salto;
        int tamanioFuente;

        salto = 15;
        tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Yo D./Dña. ").append(pdfModel.getNombre()).append(" con DNI ").append(pdfModel.getDni()).append(" y domicilio en ");
        parrafo.append(pdfModel.getDomicilio()).append(" ").append(pdfModel.getLocalidad());
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("Por medio del presente escrito autorizo a los miembros de ".concat(pdfModel.getGimnasio()).concat(", a incluirme y "));
        parrafo.append("pertenecer al GRUPO DE WHATSAPP del gimnasio, para recibir las informaciones y publicaciones que envíen.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        //salto = 15;tamanioFuente = 14;
        parrafo = new StringBuilder();
        parrafo.append("De igual manera, es mi deseo establecer que esta autorización es voluntaria y gratuita.");
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), tamanioFuente, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, tamanioFuente, null, salto);
        heightStartParagraph += (parrafoList.size() * salto + Constantes.SALTO_PARRAFO);

        return heightStartParagraph;
    }
    
    private void commonSignature(PDPageContentStream contentStream, PDPage page, int heightStartParagraph) throws IOException, EmptyException {

        List<String> parrafoList = new ArrayList<>();
        StringBuilder parrafo = new StringBuilder();
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
        String[] hoy = sdf.format(calendar.getTime()).split("/");

        parrafo.append("Y para que conste dejo firmado de forma electrónica este documento ");
        parrafo.append("con fecha ").append(hoy[0]).append(" de ").append(hoy[1]).append(" de ").append(hoy[2]);
        parrafoList = organizaRenglones(parrafoList, parrafo.toString(), 14, null, false, false);
        generoParrafo(contentStream, page, parrafoList, heightStartParagraph, PDType1Font.TIMES_ROMAN, 14, null, 15);
    }

}
