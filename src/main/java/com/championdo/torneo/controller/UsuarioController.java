package com.championdo.torneo.controller;

import com.championdo.torneo.entity.User;
import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.model.ClaveUsuarioModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.service.*;
import com.championdo.torneo.service.impl.UserService;
import com.championdo.torneo.util.LoggerMapper;
import com.championdo.torneo.util.Utils;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	/**
	 * TODO INFORMACIÓN MUY IMPORTANTE
	 * Los usuario dueños de gimnasio (y clientes para mi) tendrán rol ROLE_ADMIN
	 * Los usuario clientes del gimnasio tendrán ROLE_USER
	 * Yo tendré ROLE_ROOT y tendré una consola especial con acceso a absolutamente toda la información
	*/
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private FormularioService formularioService;
	@Autowired
	private PrincipalService principalService;
	@Autowired
	private SeguridadService seguridadService;
	
	@GetMapping("/formularioUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView formularioUsuario(ModelAndView modelAndView) {
		modelAndView.setViewName("formularioUsuario");
		principalService.cargaBasicaCompleta(modelAndView);
		UserModel userModel = userService.cargarUserModelCompleto(modelAndView);
		formularioService.cargarDesplegables(modelAndView, userModel.getCodigoGimnasio());
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return modelAndView;
	}
	
	@PostMapping("/actualizarUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView actualizarUsuario(@ModelAttribute("usuario") UserModel usuario) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			userService.addOrUpdate(usuario);
			modelAndView.addObject("actualizacionCorrecta", "actualizacionCorrecta");
		} catch (Exception e) {
			modelAndView.addObject("actualizacionError", "actualizacionError");
			LoggerMapper.log(Level.ERROR, "actualizarUsuario", e.getMessage(), getClass());
		}
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return formularioUsuario(modelAndView);
	}

	@GetMapping("/formularioCambioClave")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView formularioCambioClave(ModelAndView modelAndView) {
		modelAndView.setViewName("formularioCambioClave");
		com.championdo.torneo.entity.User usuario = principalService.cargaBasicaCompleta(modelAndView);
		ClaveUsuarioModel claveUsuarioModel = new ClaveUsuarioModel();
		claveUsuarioModel.setUsername(usuario.getUsername());
		modelAndView.addObject("claveUsuarioModel", claveUsuarioModel);
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return modelAndView;
	}

	@PostMapping("/actualizarClaveUsuario")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView actualizarClaveUsuario(@ModelAttribute("claveUsuarioModel") ClaveUsuarioModel claveUsuarioModel ) {
		ModelAndView modelAndView = new ModelAndView();
		UserModel usuario = userService.findModelByUsername(claveUsuarioModel.getUsername());
		if (userService.comparePassword(claveUsuarioModel.getAntiguaClave(), usuario.getPassword())) {
			usuario.setPassword(userService.encodePassword(claveUsuarioModel.getNuevaClave()));
			userService.updatePass(usuario);
			modelAndView.addObject("claveModificada", "claveModificada");
			LoggerMapper.log(Level.INFO, "actualizarUsuario", "Contraseña actualizada", getClass());
		} else {
			modelAndView.addObject("antiguaDistinta", "antiguaDistinta");
			LoggerMapper.log(Level.INFO, "actualizarUsuario", "Contraseña antigua distinta", getClass());
		}
		modelAndView.addObject("usuario", usuario);
		modelAndView.addObject("claveUsuarioModel", claveUsuarioModel);
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return formularioCambioClave(modelAndView);
	}

	@GetMapping("/remove/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView eliminarUsuario(ModelAndView modelAndView, @PathVariable String username) {
		com.championdo.torneo.entity.User user = principalService.cargaBasicaCompleta(modelAndView);
		seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/usuario/remove/"+username);
		if (userService.delete(username, user.getCodigoGimnasio())) {
			modelAndView.addObject("eliminacionCorrecta","Elmiminación del usuario " + username + " realiazada correctamente");
		} else {
			modelAndView.addObject("eliminacionError","Hubo un error al eliminar el usuario " + username);
		}
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return users(modelAndView);
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView users(ModelAndView modelAndView) {
		User user = principalService.cargaBasicaCompleta(modelAndView);
		seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/usuario/users");
		modelAndView.setViewName("gimnasio/adminUsers");
		modelAndView.addObject("userList", userService.findAll(user.getCodigoGimnasio()));
		modelAndView.addObject("userRoleList", userRoleService.adminAvailableRoles());
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return modelAndView;
	}

	@GetMapping("/users/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView userDetail(ModelAndView modelAndView, @PathVariable String username) {
		LoggerMapper.methodIn(Level.INFO, "/users/"+username, username, this.getClass());
		User user = principalService.cargaBasicaCompleta(modelAndView);
		seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/usuario/users/"+username);
		modelAndView.setViewName("gimnasio/adminUser");
		UserModel userModel = userService.findModelByUsername(username);
		modelAndView.addObject("user", userModel);
		modelAndView.addObject("userRoleList", userRoleService.adminAvailableRoles());
		modelAndView.addObject("loggedUser", userService.isLoggedUser(user.getUsername(), userModel.getUsername()));
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return modelAndView;
	}

	@GetMapping("/enabled/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView updatePay(ModelAndView modelAndView, @PathVariable String username) {
		User user = principalService.cargaBasicaCompleta(modelAndView);
		seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/usuario/enabled/" + username);
		UserModel usuario = userService.findModelByUsername(username);
		usuario.setEnabled(!usuario.isEnabled());
		usuario.setUsernameModificacione(user.getUsername());
		userService.addOrUpdate(usuario);
		modelAndView.addObject("updateOK", "Habilitación de " + usuario.getName()
				+ " " + usuario.getLastname()
				+ (!com.mysql.cj.util.StringUtils.isNullOrEmpty(usuario.getSecondLastname()) ? " " + usuario.getSecondLastname() : "")
				+ " actualizada correctamente");
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return userDetail(modelAndView, username);
	}

	@GetMapping("/rol/{username}/{rol}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView update(ModelAndView modelAndView, @PathVariable String username, @PathVariable String rol) {
		User user = principalService.cargaBasicaCompleta(modelAndView);
		seguridadService.gimnasioHabilitadoAdministracion(user.getCodigoGimnasio(), "/usuario/rol/" + username + "/" + rol);
		UserRole userRole = new UserRole();
		com.championdo.torneo.entity.User usuario = userService.findByUsername(username);
		userRole.setUser(usuario);
		userRole.setRole(rol);
		userRoleService.deleteByUsername(username);
		userRoleService.save(userRole);
		modelAndView.addObject("updateOK", "Rol de " + usuario.getName()
				+ " " + usuario.getLastname()
				+ (!com.mysql.cj.util.StringUtils.isNullOrEmpty(usuario.getSecondLastname()) ? " " + usuario.getSecondLastname() : "")
				+ " actualizado correctamente");
		LoggerMapper.methodOut(Level.INFO, Utils.obtenerNombreMetodo(), modelAndView, getClass());
		return userDetail(modelAndView, username);
	}

}
