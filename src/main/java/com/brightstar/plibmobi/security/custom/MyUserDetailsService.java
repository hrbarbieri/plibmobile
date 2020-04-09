package com.brightstar.plibmobi.security.custom;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightstar.plibmobi.security.model.Role;
import com.brightstar.plibmobi.security.model.Users;
import com.brightstar.plibmobi.security.repository.UsersRepository;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    public MyUserDetailsService() {
        super();
    }

    // API

    @Override
    public UserDetails loadUserByUsername(final String name) throws UsernameNotFoundException {

        try {
            final Optional<Users> optUser = userRepository.findById(name);
            if (!optUser.isPresent()) {
                throw new UsernameNotFoundException("No user found with username: " + name);
            }
            
            Users user = optUser.get();
            
            return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(),true, true, true, true, getGrantedAuthority(user));
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Collection<GrantedAuthority> getGrantedAuthority(Users users) {
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

		for(Role role : users.getRoles()) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}	

}
