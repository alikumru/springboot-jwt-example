package com.ali.doctors.Doktorlar.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ali.doctors.Doktorlar.Service.CustomUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)//Burası true olursa controllerdaki preauthorize tagi configure methodundaki konfigürasyonu 
//ezer,false olursa ya da hiç yazılmazsa configure methodu contollerdaki konfigürasyonu ezer.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CustomUserDetailService customUserDetailService;
	
	
	@Autowired
	public SecurityConfig(CustomUserDetailService customUserDetailService) {
		super();
		this.customUserDetailService = customUserDetailService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST , "/*/sign-up").permitAll()
			.antMatchers("/*/intro/**").hasAnyRole("USER","ADMIN") //Burası admin verilip method üzerinde de ezilebilir.
			.antMatchers("/*/intro2/**").hasRole("ADMIN")
			.and()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), customUserDetailService))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	

}
