package com.ali.doctors.Doktorlar.Service;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.ali.doctors.Doktorlar.Model.ApplicationUser;


@Component
public class CustomUserDetailService implements UserDetailsService{

	//Test
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		ApplicationUser applicationUser = loadApplicationUserByUsername(username);
		User user = new User(applicationUser.getUsername(),encoder.encode(applicationUser.getPassword()),AuthorityUtils.createAuthorityList("ROLE_USER"));
		return user;
	}

	public ApplicationUser loadApplicationUserByUsername(String username) {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new ApplicationUser("ali","1234");
	}
}
