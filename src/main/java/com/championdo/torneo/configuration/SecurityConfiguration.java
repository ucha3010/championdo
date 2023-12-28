package com.championdo.torneo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/css/*", "/imgs/*", "/imgs/icons/*", "/imgs/icons/fonts/*", "/js/*").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/olvidoClave").permitAll()
		.antMatchers("/nuevaClave").permitAll()
		.antMatchers("/pass-new").permitAll()
		.antMatchers("/change-pass").permitAll()
		.antMatchers("/formulario/alta").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/loginTorneo").loginProcessingUrl("/logincheck")
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/principal/").permitAll()
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/loginTorneo?logout").permitAll();
	}

}
