package com.buffalo.gateway.authentication.service;

import com.buffalo.gateway.authentication.mapper.AuthUserMapper;
import com.buffalo.gateway.authentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthUserMapper authUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = authUserMapper.getByLoginName(username);

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return user;
	}
}
