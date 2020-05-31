package com.example.demo.service;

import com.example.demo.persistense.repository.ProfessorRepository;
import com.example.demo.persistense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException
	{
		return userRepository.findByUsername(s).orElse(null);
	}
}
