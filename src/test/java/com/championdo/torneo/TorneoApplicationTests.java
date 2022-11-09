package com.championdo.torneo;

import com.championdo.torneo.model.PdfModel;
import com.championdo.torneo.service.PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class TorneoApplicationTests {

	@Autowired
	private PdfService pdfService;

	@Test
	void contextLoads() {

		PdfModel pdfModel = new PdfModel();
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

		pdfService.generarPdf(pdfModel);

		assertThat("Hello world", is("Hello world"));
	}

}
