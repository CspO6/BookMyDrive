package com.nuevo.springboot.reservas.app.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nuevo.springboot.reservas.app.models.service.UsuarioService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioService usuarioServicio;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	        .antMatchers(
	            "/registro",
	            "/js/**",
	            "/css/**",
	            "/images/**",
	            "/confirm-account",
	            "/accountVerified",
	            "/error",
	            "/forgot_password",      // Agregado para permitir el acceso sin autenticación
	            "/reset_password",
	            "/index",
	            "/Administrador/**",
	            "/"
	            // Agregado para permitir el acceso sin autenticación
	        ).permitAll()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/app", true)
	            .permitAll()
	        .and()
	        .logout()
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .permitAll()
	        .and()
	        .exceptionHandling().accessDeniedPage("/error");
	}

}







