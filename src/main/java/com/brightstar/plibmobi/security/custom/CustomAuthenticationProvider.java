package com.brightstar.plibmobi.security.custom;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.brightstar.plibmobi.security.model.Users;
import com.brightstar.plibmobi.security.repository.UsersRepository;

//@Configuration
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		String name = auth.getName();
		Optional<Users> userOpt = usersRepository.findById(name);

		if (!userOpt.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}

		Users user = userOpt.get();

		final Authentication result = super.authenticate(auth);
		return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
