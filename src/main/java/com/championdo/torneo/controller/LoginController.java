package com.championdo.torneo.controller;

import com.championdo.torneo.util.Constantes;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {

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

}
