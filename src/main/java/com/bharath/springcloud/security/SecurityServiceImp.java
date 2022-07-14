package com.bharath.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImp implements SecurityService {

	@Autowired
	UserDetailsService userDetailsService;// or you can use UserDetailsServiceImp userDetailsServiceImp
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	public boolean login(String userName, String password) {
		System.out.println("SecurityServiceImp class called 3");
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		System.out.println("1");
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		System.out.println("2");
		authenticationManager.authenticate(token);
		System.out.println("3");
		boolean result = token.isAuthenticated();
		System.out.println("4");
		if(result) {
			System.out.println("5");
			SecurityContextHolder.getContext().setAuthentication(token);
			System.out.println("6");
		}
		System.out.println("7");
		return result;
	}

}
