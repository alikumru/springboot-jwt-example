package ServiceImplementation;


public class CustomUserDetailServiceImpl {

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		ApplicationUser applicationUser = loadApplicationUserByUsername(username);
//		User user = new User(applicationUser.getUsername(),encoder.encode(applicationUser.getPassword()),AuthorityUtils.createAuthorityList("ROLE_USER"));
//		return user;
//	}
//
//	public ApplicationUser loadApplicationUserByUsername(String username) {
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		
//		return new ApplicationUser("ali1","12345","USER_ROLE");
//	}
}
