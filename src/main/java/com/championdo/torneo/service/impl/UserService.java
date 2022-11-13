package com.championdo.torneo.service.impl;

import com.championdo.torneo.entity.UserRole;
import com.championdo.torneo.mapper.MapperUser;
import com.championdo.torneo.model.UserModel;
import com.championdo.torneo.repository.UserRepository;
import com.championdo.torneo.repository.UserRoleRepository;
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

import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private MapperUser mapperUser;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	public com.championdo.torneo.entity.User addOrUpdate(UserModel usuario) {
		usuario.setFechaModificacion(new Date());
		return userRepository.save(mapperUser.model2Entity(usuario));
	}

	public boolean delete(String username) {
		try {
			com.championdo.torneo.entity.User user = userRepository.findByUsername(username);
			Set<UserRole> userRoles = userRoleRepository.findByUser(user);
			for(UserRole userRole: userRoles) {
				userRoleRepository.delete(userRole);
			}
			user.setUserRole(null);
			userRepository.delete(user);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean comparePassword(String newPass, String oldPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(newPass, oldPass);
	}

	public String generatePassword(String newPass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(newPass);
	}

	public List<com.championdo.torneo.entity.User> findAll() {
		return userRepository.findAll();
	}

	public com.championdo.torneo.entity.User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public UserModel findModelByUsername(String username) {
		return mapperUser.entity2Model(userRepository.findByUsername(username));
	}

	public com.championdo.torneo.entity.User cargarUsuarioCompleto(ModelAndView modelAndView) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.championdo.torneo.entity.User usuario = findByUsername(user.getUsername());
		modelAndView.addObject("usuario", usuario);
		return usuario;
	}

	public UserModel cargarUserModelCompleto(ModelAndView modelAndView) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.championdo.torneo.entity.User usuario = findByUsername(user.getUsername());
		UserModel userModel = mapperUser.entity2Model(usuario);
		modelAndView.addObject("usuario", userModel);
		return userModel;
	}

	public com.championdo.torneo.entity.User convertUser(UserModel userModel) {
		return mapperUser.model2Entity(userModel);
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
}
