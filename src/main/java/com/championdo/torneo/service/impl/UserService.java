package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.GimnasioModel;
import com.championdo.torneo.model.UserGymModel;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.UserRepository;
import com.championdo.torneo.repository.UserRoleRepository;
import com.championdo.torneo.service.GimnasioService;
import com.championdo.torneo.service.TournamentRegistrationService;
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
	private TournamentRegistrationService tournamentRegistrationService;
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
		com.championdo.torneo.entity.User user = addOrUpdate(userModel);
		userRoleRepository.save(new UserRole(user, rol));
		return user;
	}

	public com.championdo.torneo.entity.User addOrUpdate(UserModel usuario) throws PersistenceException {
		try {
			usuario.setPassword(findModelByUsername(usuario.getUsername()).getPassword());
		} catch (NoResultException ignored) {
		}
		usuario.setFechaModificacion(new Date());
		try {
			return userRepository.save(mapperUser.model2Entity(usuario));
		} catch (Exception exception) {
			throw new PersistenceException();
		}
	}

	public void updatePass(UserModel usuario) throws PersistenceException {
		usuario.setFechaModificacion(new Date());
		try {
			userRepository.save(mapperUser.model2Entity(usuario));
		} catch (Exception exception) {
			throw new PersistenceException();
		}
	}

	public boolean delete(String username) {
		boolean respuesta = true;
		try {
			com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
			tournamentRegistrationService.deleteByIdCard(username);
			inscripcionTaekwondoService.deleteByDni(username);
			Set<UserRole> userRoles = userRoleRepository.findByUser(user);
			for (UserRole userRole : userRoles) {
				userRoleRepository.delete(userRole);
			}
			user.setUserRole(null);
			userRepository.delete(user);
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

	public UserModel cargarUserModelCompleto(ModelAndView modelAndView) {
		com.championdo.torneo.entity.User usuario = getLoggedUser();
		UserModel userModel = mapperUser.entity2Model(usuario);
		modelAndView.addObject("usuario", userModel);
		return userModel;
	}

	public void addFromRoot (GimnasioModel gimnasioModel) {
		UserModel userModel = new UserModel();
		userModel.setUsername(gimnasioModel.getCifNif().toUpperCase());
		userModel.setPassword(encodePassword(gimnasioModel.getCifNif().toUpperCase()));
		userModel.setCorreo(gimnasioModel.getCorreo());
		userModel.setTelefono(gimnasioModel.getTelefono());
		userModel.setEnabled(true);
		userModel.setFechaAlta(new Date());
		userModel.setCodigoGimnasio(gimnasioModel.getId());
		com.championdo.torneo.entity.User user = addOrUpdate(userModel);
		userRoleRepository.save(new UserRole(user, Constantes.ROLE_ADMIN));
	}

	public com.championdo.torneo.entity.User getLoggedUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return findByUsername(user.getUsername());
	}

	public Boolean isLoggedUser(String loggerUser, String foundUser) {
		return loggerUser.equals(foundUser);
	}

	public List<UserModel> findByRole(String role) {
		List<UserRole> userRoleList = userRoleRepository.findByRole(role);
		List<UserModel> userModelList = new ArrayList<>();
		for (UserRole userRole: userRoleList) {
			userModelList.add(findModelByUsername(userRole.getUser().getUsername()));
		}
		return userModelList;
	}

	public List<UserModel> getUserModelList(List<UserGymModel> userGymModelList) {
		List<UserModel> userModelList = new ArrayList<>();
		for (UserGymModel userGymModel: userGymModelList) {
			userModelList.add(findModelByUsername(userGymModel.getUsername()));
		}
		return userModelList;
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
