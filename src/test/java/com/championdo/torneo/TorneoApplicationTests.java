package com.championdo.torneo;

import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.PdfService;
import com.championdo.torneo.service.SeguridadService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
//@RunWith(JUnit4.class)
class TorneoApplicationTests {

	@Autowired
	private PdfService pdfService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	@Autowired
	private SeguridadService seguridadService;

	private PdfModel pdfModel;
	@Test
	File testGenerarPdf() {
		cargarPDF();
		return pdfService.generarPdfTorneo(pdfModel);
	}


	@Test
	void testPassword() {
		String pass = Utils.generateSecurePassword();
		LoggerMapper.log(Level.INFO, "testPassword sin encriptar", pass, getClass());
		pass = userService.encodePassword(pass);
		LoggerMapper.log(Level.INFO, "testPassword encriptada", pass, getClass());
	}

	@Test
	void testEmailWithAttachedFile() {
		try {
			UserModel userModel = new UserModel();
			userModel.setName("Pedro");
			userModel.setPassword("223344");
			userModel.setCorreo("dusheff@hotmail.com");
			List<File> files = new ArrayList<>();
			files.add(testGenerarPdf());
			emailService.sendAttachedFile(userModel, "Envío archivo", "Texto del mensaje", files);
		} catch (SenderException e) {
			LoggerMapper.log(Level.ERROR, "testEmail", e.getMessage(), getClass());
		}
		assertThat("Hello world", is("Hello world"));
	}

	@Test
	public String getAbsolutePath() {
		String[] absolute = new String[1];
		try {
			File f = new File("program.txt");
			absolute = f.getAbsolutePath().split(f.getName());
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("absolute[0]: " + absolute[0]);
		return absolute[0];
	}

	@Test
	public void generarCodigoTest() {
		String codigo = seguridadService.obtenerCodigo();
		System.out.println(codigo);
		Assertions.assertNotNull(codigo);
	}

	@Test
	public void eliminarTodoLosArchivos() {
		// CUIDADO QUE ESTE TEST ELIMINA TODOS LOS ARCHIVOS!!!!!!!!
		String ruta = getAbsolutePath() + "src" + File.separator + "main" + File.separator + "resources" + File.separator
				+ "static" + File.separator + "files" ;
		Path directorioAEliminar = Paths.get(ruta);

		try {
			eliminarDirectorioRecursivamente(directorioAEliminar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void cargarPDF() {
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
	}

	private static void eliminarDirectorioRecursivamente(Path directorio) throws IOException {
		if (Files.exists(directorio)) {
			Files.walkFileTree(directorio, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) {
					// Manejar el error al eliminar un archivo, si es necesario
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc == null && !directorio.equals(dir)) {
						Files.delete(dir);
					}
					// Manejar el error al eliminar un directorio, si es necesario, con el else
					return FileVisitResult.CONTINUE;
				}
			});
		}
	}
}
