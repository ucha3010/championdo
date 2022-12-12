package com.championdo.torneo;

import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.service.impl.PdfServiceImpl;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import com.sun.xml.internal.ws.client.SenderException;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@RunWith(JUnit4.class)
class TorneoApplicationTests {

	@Autowired
	private PdfService pdfService;
	@Autowired
	private EmailService emailService;

	private PdfModel pdfModel;

	private PdfModel cargarPDF() {
		pdfModel = new PdfModel();
		pdfModel.setNombre("Nombre Autorizador Apellido1dor Apellido2dor");
		pdfModel.setDni("22222222A");
		pdfModel.setFechaNacimiento("30/10/1960");
		pdfModel.setDomicilio("calle Mayor 150");
		pdfModel.setLocalidad("Madrid (28003) - España");
		pdfModel.setGimnasio("Championdo");
		pdfModel.setFechaCampeonato("20-12-2022");
		pdfModel.setDireccionCampeonato("Polideportivo La Luz");
		pdfModel.setCalidadDe("padre");
		pdfModel.setNombreMenor("Nombre Autorizado Apellido1do Apellido2do");
		pdfModel.setDniMenor("01234567A");
		pdfModel.setCinturonBlanco(true);
		pdfModel.setMayorEdad(false);
		pdfModel.setInclusivo(true);
		pdfModel.setCinturonActual("Amarillo Naranja");
		pdfModel.setIdInscripcion(999999);
		pdfModel.setCategoria("C2");
		pdfModel.setPoomsae("5º POOMSAE");
		return pdfModel;
	}
	@Test
	File testGenerarPdf() {
		cargarPDF();
		File file = pdfService.generarPdf(pdfModel);
		return file;
	}


	@Test
	void testPassword() {
		String pass = Utils.generateSecurePassword();
		LoggerMapper.log(Level.INFO, "testPassword", pass, getClass());
	}

	@Test
	void testEmailPass() {
		try {
			UserModel userModel = new UserModel();
			userModel.setName("Julián");
			userModel.setPassword("123456");
			userModel.setCorreo("dusheff@hotmail.com");
			emailService.sendNewPassword(userModel);
		} catch (SenderException e) {
			LoggerMapper.log(Level.ERROR, "testEmail", e.getMessage(), getClass());

		}
	}

	@Test
	void testEmailWithAttachedFile() {
		try {
			UserModel userModel = new UserModel();
			userModel.setName("Pedro");
			userModel.setPassword("223344");
			userModel.setCorreo("dusheff@hotmail.com");
			emailService.sendAttachedFile(userModel, "Envío archivo", "Texto del mensaje", testGenerarPdf());
		} catch (SenderException e) {
			LoggerMapper.log(Level.ERROR, "testEmail", e.getMessage(), getClass());
		}
		assertThat("Hello world", is("Hello world"));
	}

	public File createAFile(String fileName) {
		File file = null;
		try {
			String content = "Un texto dentro del archivo";
			file = new File(fileName + ".txt");

			// If file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			// Write in file
			bw.write(content);

			// Close connection
			bw.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return file;
	}

	@Test
	public void getAbsolutePath() {
		String[] absolute = new String[1];
		try {
			File f = new File("program.txt");
			//absolute = f.getAbsolutePath().split(f.getName());
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("absolute[0]: " + absolute[0]);
	}

	}
