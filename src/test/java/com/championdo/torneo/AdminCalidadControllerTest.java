package com.championdo.torneo;

import com.championdo.torneo.controller.AdminCalidadController;
import com.championdo.torneo.model.CalidadModel;
import com.championdo.torneo.service.CalidadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCalidadController.class)
public class AdminCalidadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalidadService calidadService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {

        //simulo respuesta de llamada el servicio que hay en el método /calidadList del controlador
        when(calidadService.findAll()).thenReturn(Arrays.asList(new CalidadModel(1,"Padre",null,0),
                new CalidadModel(2,"Madre",null,1),
                new CalidadModel(3,"Tutor",null,2)));

        //llamo al método /calidadList del controlador
        this.mockMvc.perform(get("/calidadList")).andDo(print()).andExpect(status().isOk());
    }

}
