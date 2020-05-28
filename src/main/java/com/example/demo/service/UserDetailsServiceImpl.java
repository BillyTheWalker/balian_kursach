package com.example.demo.service;

import com.example.demo.persistense.models.Admin;
import com.example.demo.persistense.models.Professor;
import com.example.demo.persistense.repository.AdminRepository;
import com.example.demo.persistense.repository.ProfessorRepository;
import com.example.demo.persistense.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
	private final AdminRepository adminRepository;
	private final ProfessorRepository professorRepository;

	@Override
	public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException
	{
		Optional<? extends UserDetails> userDetails = adminRepository.findOne(Example.of(Admin.builder().username(s).build()));
		if(userDetails.isPresent())
			return userDetails.get();
		return professorRepository.findOne(Example.of(Professor.builder().username(s).build())).orElse(null);
	}
}
