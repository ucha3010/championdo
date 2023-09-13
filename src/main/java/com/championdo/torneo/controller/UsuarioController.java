package com.championdo.torneo.controller;

import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.model.ClaveUsuarioModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.FormularioService;
import com.championdo.torneo.service.UserRoleService;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private FormularioService formularioService;
	
	@GetMapping("/formularioUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView formularioUsuario(ModelAndView modelAndView) {
		modelAndView.setViewName("formularioUsuario");
		UserModel userModel = userService.cargarUserModelCompleto(modelAndView);
		formularioService.cargarDesplegables(modelAndView);
		LoggerMapper.log(Level.INFO, "formularioUsuario " + userModel, modelAndView, getClass());
		return modelAndView;
	}
	
	@PostMapping("/actualizarUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView actualizarUsuario(@ModelAttribute("usuario") UserModel usuario) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("formularioUsuario");
		try {
			userService.addOrUpdate(usuario);
			modelAndView.addObject("actualizacionCorrecta", "actualizacionCorrecta");
		} catch (Exception e) {
			modelAndView.addObject("actualizacionError", "actualizacionError");
			LoggerMapper.log(Level.ERROR, "actualizarUsuario", e.getMessage(), getClass());
		}
		LoggerMapper.log(Level.INFO, "actualizarUsuario", usuario, getClass());
		return modelAndView;
	}

	@GetMapping("/formularioCambioClave")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView formularioCambioClave() {
		ModelAndView modelAndView = new ModelAndView("formularioCambioClave");
		com.championdo.torneo.entity.User usuario = userService.cargarUsuarioCompleto(modelAndView);
		ClaveUsuarioModel claveUsuarioModel = new ClaveUsuarioModel();
		claveUsuarioModel.setUsername(usuario.getUsername());
		modelAndView.addObject("claveUsuarioModel", claveUsuarioModel);
		LoggerMapper.log(Level.INFO, "formularioCambioClave", modelAndView, getClass());
		return modelAndView;
	}

	@PostMapping("/actualizarClaveUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView actualizarClaveUsuario(@ModelAttribute("claveUsuarioModel") ClaveUsuarioModel claveUsuarioModel ) {
		ModelAndView modelAndView = new ModelAndView("formularioCambioClave");
		UserModel usuario = userService.findModelByUsername(claveUsuarioModel.getUsername());
		if (userService.comparePassword(claveUsuarioModel.getAntiguaClave(), usuario.getPassword())) {
			usuario.setPassword(userService.encodePassword(claveUsuarioModel.getNuevaClave()));
			userService.addOrUpdate(usuario);
			modelAndView.addObject("claveModificada", "claveModificada");
			LoggerMapper.log(Level.INFO, "actualizarUsuario", "Contraseña actualizada", getClass());
		} else {
			modelAndView.addObject("antiguaDistinta", "antiguaDistinta");
			LoggerMapper.log(Level.INFO, "actualizarUsuario", "Contraseña antigua distinta", getClass());
		}
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("claveUsuarioModel", claveUsuarioModel);
		LoggerMapper.log(Level.INFO, "actualizarUsuario", usuario, getClass());
		return modelAndView;
	}

	/**
	 * TODO DAMIAN Este método debería habilitarlo para el rol root
	 */
	@GetMapping("/eliminarUsuario")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminarUsuario(@ModelAttribute("username") String username) {
		ModelAndView modelAndView = new ModelAndView();
		if (userService.delete(username)) {
			modelAndView.addObject("eliminacionCorrecta","actualizacionCorrecta");
		} else {
			modelAndView.addObject("eliminacionError","eliminacionError");
		}
		LoggerMapper.log(Level.INFO, "eliminarUsuario", modelAndView, getClass());
		return userList(modelAndView);
	}

	/**
	 * Este método sirve para que el administrador pueda "resetear" la clave del usuario cambiándola
	 * a fuego por el nombre de usuario del usuario.
	 * TODO DAMIAN Este método debería habilitarlo para el rol root
	 */
	@GetMapping("/resetearClaveUsuario")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView resetearClaveUsuario(@ModelAttribute("username") String username) {
		ModelAndView modelAndView = new ModelAndView();
		UserModel usuario = userService.findModelByUsername(username);
		usuario.setPassword(userService.encodePassword(username));
		userService.addOrUpdate(usuario);
		String mensaje = "Nueva clave de " + usuario.getName() + " " + usuario.getLastname() + ": " + username;
		modelAndView.addObject("reseteoClaveCorrecto",mensaje);
		LoggerMapper.log(Level.INFO, "resetearClaveUsuario", modelAndView, getClass());
		return userList(modelAndView);
	}

	@GetMapping("/userList")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView userList(ModelAndView modelAndView) {
		modelAndView.setViewName("adminUser");
		modelAndView.addObject("userList", userService.findAllModel());
		modelAndView.addObject("userRoleList", userRoleService.findDistinctByRole());
		LoggerMapper.log(Level.INFO, "userList", modelAndView, this.getClass());
		return modelAndView;
	}

	@GetMapping("/enabled/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView updatePay(ModelAndView modelAndView, @PathVariable String username) {
		UserModel usuario = userService.findModelByUsername(username);
		usuario.setEnabled(!usuario.isEnabled());
		com.championdo.torneo.entity.User user = userService.cargarUsuarioCompleto(modelAndView);
		usuario.setUsernameModificacione(user.getUsername());
		userService.addOrUpdate(usuario);
		modelAndView.addObject("updateOK", "Habilitación de " + usuario.getName()
				+ " " + usuario.getLastname()
				+ (!com.mysql.cj.util.StringUtils.isNullOrEmpty(usuario.getSecondLastname()) ? " " + usuario.getSecondLastname() : "")
				+ " actualizada correctamente");
		return userList(modelAndView);
	}

	@GetMapping("/rol/{username}/{rol}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView update(ModelAndView modelAndView, @PathVariable String username, @PathVariable String rol) {
		UserRole userRole = new UserRole();
		com.championdo.torneo.entity.User user = userService.findByUsername(username);
		userRole.setUser(user);
		userRole.setRole(rol);
		userRoleService.deleteByUsername(username);
		userRoleService.save(userRole);
		modelAndView.addObject("updateOK", "Rol de " + user.getName()
				+ " " + user.getLastname()
				+ (!com.mysql.cj.util.StringUtils.isNullOrEmpty(user.getSecondLastname()) ? " " + user.getSecondLastname() : "")
				+ " actualizado correctamente");
		return userList(modelAndView);
	}

}
