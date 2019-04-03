package com.ali.doctors.Doktorlar.Security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ali.doctors.Doktorlar.Model.ApplicationUser;
import com.ali.doctors.Doktorlar.Service.CustomUserDetailService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private final CustomUserDetailService customUserDetailService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailService customUserDetailService) {
		super(authenticationManager);
		this.customUserDetailService = customUserDetailService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(SecurityConstant.HEADER_STRING);
		
		if(header ==null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}	
		
		UsernamePasswordAuthenticationToken  usernamePasswordAuth = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstant.HEADER_STRING);
		
		if(token == null) return null;
		
		String username= JWT.require(Algorithm.HMAC512(SecurityConstant.SECRET.getBytes()))
                .build()
                .verify(token.replace(SecurityConstant.TOKEN_PREFIX, ""))
                .getSubject();
	
		UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
		ApplicationUser applicationUser = (ApplicationUser) customUserDetailService.loadApplicationUserByUsername(username);
        return username != null ? new UsernamePasswordAuthenticationToken(applicationUser, null,userDetails.getAuthorities()) : null;
	}
}
