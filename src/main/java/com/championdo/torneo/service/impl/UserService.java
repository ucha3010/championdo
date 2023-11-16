package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.InscripcionTaekwondo;
import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.GimnasioRootModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.UserRepository;
import com.championdo.torneo.repository.UserRoleRepository;
import com.championdo.torneo.service.GimnasioRootService;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.InscripcionService;
import com.championdo.torneo.service.InscripcionTaekwondoService;
import com.championdo.torneo.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private MapperUser mapperUser;
	@Autowired
	private GimnasioService gimnasioService;
	@Autowired
	private GimnasioRootService gimnasioRootService;
	@Autowired
	private InscripcionService inscripcionService;
	@Autowired
	private InscripcionTaekwondoService inscripcionTaekwondoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	public com.championdo.torneo.entity.User altaNuevoUsuario(UserModel userModel, String rol) throws PersistenceException {
		userModel.setEnabled(true);
		userModel.setFechaAlta(new Date());
		userModel.setUsername(userModel.getUsername().toUpperCase());
		userModel.setPassword(encodePassword(userModel.getPassword()));
		GimnasioRootModel gimnasioRootModel = gimnasioRootService.findById(userModel.getCodigoGimnasio());
		userModel.setGimnasio(gimnasioService.findByCodigoGimnasio(gimnasioRootModel.getId()));
		com.championdo.torneo.entity.User user = addOrUpdate(userModel);
		userRoleRepository.save(new UserRole(user, rol));
		return user;
	}

	public com.championdo.torneo.entity.User addOrUpdate(UserModel usuario) throws PersistenceException {
		usuario.setFechaModificacion(new Date());
		try {
			return userRepository.save(mapperUser.model2Entity(usuario));
		} catch (Exception exception) {
			throw new PersistenceException();
		}
	}

	public boolean delete(String username, int codigoGimnasio) {
		boolean respuesta = true;
		try {
			com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
			if (codigoGimnasio == user.getCodigoGimnasio()) {
				inscripcionService.deleteByDni(username);
				inscripcionTaekwondoService.deleteByDni(username);
				Set<UserRole> userRoles = userRoleRepository.findByUser(user);
				for (UserRole userRole : userRoles) {
					userRoleRepository.delete(userRole);
				}
				user.setUserRole(null);
				userRepository.delete(user);
			} else {
				respuesta = false;
			}
		} catch (Exception e) {
			respuesta = false;
		}
		return respuesta;
	}

	public boolean comparePassword(String newPass, String oldPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(newPass, oldPass);
	}

	public String encodePassword(String newPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(newPass);
	}

	public List<UserModel> findAll() {
		List<com.championdo.torneo.entity.User> userList = userRepository.findAllByOrderByLastnameDesc();
		return getModelList(userList);
	}

	public List<UserModel> findAll(int codigoGimnasio) {
		List<com.championdo.torneo.entity.User> userList = userRepository.findByCodigoGimnasioOrderByLastnameDesc(codigoGimnasio);
		return getModelList(userList);
	}

	public com.championdo.torneo.entity.User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public UserModel findModelByUsername(String username) throws NoResultException {
		com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new NoResultException();
		}
		return mapperUser.entity2Model(user);
	}

	public com.championdo.torneo.entity.User cargarUsuarioCompleto(ModelAndView modelAndView) {
		com.championdo.torneo.entity.User usuario = getLoggedUser();
		modelAndView.addObject("usuario", usuario);
		return usuario;
	}

	public UserModel cargarUserModelCompleto(ModelAndView modelAndView) {
		com.championdo.torneo.entity.User usuario = getLoggedUser();
		UserModel userModel = mapperUser.entity2Model(usuario);
		modelAndView.addObject("usuario", userModel);
		return userModel;
	}

	public void addFromRoot (GimnasioRootModel gimnasioRootModel, GimnasioModel gimnasioModel) {
		UserModel userModel = new UserModel();
		userModel.setUsername(gimnasioRootModel.getCifNif().toUpperCase());
		userModel.setPassword(encodePassword(gimnasioRootModel.getCifNif().toUpperCase()));
		userModel.setName(gimnasioRootModel.getNombreResponsable());
		userModel.setLastname(gimnasioRootModel.getApellido1Responsable());
		userModel.setSecondLastname(gimnasioRootModel.getApellido2Responsable());
		userModel.setCorreo(gimnasioRootModel.getCorreo());
		userModel.setTelefono(gimnasioRootModel.getTelefono());
		userModel.setFechaNacimiento(gimnasioRootModel.getFechaNacimiento());
		userModel.setGimnasio(gimnasioModel);
		userModel.setEnabled(true);
		userModel.setFechaAlta(new Date());
		userModel.setCodigoGimnasio(gimnasioModel.getId());
		com.championdo.torneo.entity.User user = addOrUpdate(userModel);
		userRoleRepository.save(new UserRole(user, Constantes.ROLE_ADMIN));
	}

	public void deleteFromRoot (int idGimnasioRootModel) {
		List<com.championdo.torneo.entity.User> userList = userRepository.findByCodigoGimnasioOrderByLastnameDesc(idGimnasioRootModel);
		for (com.championdo.torneo.entity.User user : userList) {
			delete(user.getUsername(), user.getCodigoGimnasio());
		}
	}

	public com.championdo.torneo.entity.User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return findByUsername(user.getUsername());
	}
	
	private User buildUser(com.championdo.torneo.entity.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
				true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities (Set<UserRole> userRoles) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		
		for (UserRole userRole: userRoles) {
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(grantedAuthorities);
	}

	private List<UserModel> getModelList(List<com.championdo.torneo.entity.User> userList) {
		List<UserModel> userModelList = new ArrayList<>();
		for(com.championdo.torneo.entity.User user : userList) {
			userModelList.add(mapperUser.entity2Model(user));
		}
		return userModelList;
	}
}
