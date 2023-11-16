package com.championdo.torneo.controller;

import com.championdo.torneo.exception.SenderException;
import com.championdo.torneo.model.ClaveUsuarioModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.EmailService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.Constantes;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PersistenceException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;

	@GetMapping("/")
	@PreAuthorize("permitAll()")
	public String showLoginForm() {
		return Constantes.LOGIN;
	}

	@GetMapping("/loginTorneo")
	@PreAuthorize("permitAll()")
	public String showLoginTorneoForm(Model model,
								@RequestParam(name = "error", required = false) String error,
								@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return Constantes.LOGIN;
	}

	@GetMapping("/olvidoClave")
	@PreAuthorize("permitAll()")
	public ModelAndView olvidoClave(ModelAndView modelAndView) {
		modelAndView.setViewName("formularioOlvidoClave");
		if (modelAndView.isEmpty() || !modelAndView.getModel().containsKey("claveUsuarioModel")) {
			modelAndView.addObject("claveUsuarioModel", new ClaveUsuarioModel());
		}
		return modelAndView;
	}

	@PostMapping("/nuevaClave")
	@PreAuthorize("permitAll()")
	public ModelAndView nuevaClave(@ModelAttribute("claveUsuarioModel") ClaveUsuarioModel claveUsuarioModel, ModelAndView modelAndView) {
		try {
			//TODO DAMIAN evaluar este método. No me gusta así, ya que cualquiera puede pedir el cambio de clave de alguien y la clave directamente se cambia
			//por ahí sería mejor enviar por email el acceso a una página de cambio de contraseña
			//En el enlace que se envía iría un campo con una clave, esa clave la genero acá y la guardo en algún lado con un tiempo de validez
			//Cuando se le abre la nueva página (que tengo que crear) al usuario para cambiar su contraseña, guardo de forma oculta la clave
			//Al darle el usuario clic en enviar viajaría la clave, el username (DNI) y la nueva contraseña
			//En backend verifico que la clave existe, que no está caducada y que pertenece al DNI que me envían y si cumple, cambio la contraseña
			UserModel usuario = userService.findModelByUsername(claveUsuarioModel.getUsername());
			String password = Utils.generateSecurePassword();
			usuario.setPassword(userService.encodePassword(password));
			userService.addOrUpdate(usuario);
			usuario.setPassword(password);
			emailService.sendNewPassword(usuario);
			modelAndView.addObject("emailEnvio", Utils.ofuscar(usuario.getCorreo()));
		} catch (PersistenceException | SenderException e) {
			mostrarExcepcion(e, claveUsuarioModel, modelAndView);
		}
		modelAndView.addObject("claveUsuarioModel", claveUsuarioModel);
		LoggerMapper.log(Level.INFO, "nuevaClave", modelAndView, getClass());
		return olvidoClave(modelAndView);
	}

	private void mostrarExcepcion (Exception e, ClaveUsuarioModel claveUsuarioModel, ModelAndView modelAndView) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		LoggerMapper.log(Level.ERROR, "nuevaClave", sw.toString(), getClass());
		modelAndView.addObject("errorEnvio", claveUsuarioModel.getUsername());
	}

}
