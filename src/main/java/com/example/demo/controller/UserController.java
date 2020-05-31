package com.example.demo.controller;

import com.example.demo.persistense.models.User;
import com.example.demo.persistense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController
{
	private final UserRepository userRepository;

	@GetMapping
	@ResponseBody
	public User getByPrincipal(Principal principal)
	{
		return Optional.ofNullable(principal).map(Principal::getName).flatMap(userRepository::findByUsername).map(user -> {
			user.setPassword(null);
			return user;
		}).orElseThrow(RuntimeException::new);
	}
}
