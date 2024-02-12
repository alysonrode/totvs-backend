package com.totvs.totvsapi.auth.domain.controller;

import com.totvs.totvsapi.auth.domain.service.AuthService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")

public class AuthController
{

	@Autowired
	AuthService service;

	@PostMapping("")
	public UUID generateKey(@RequestBody String email)
	{
		return service.generateToken(email);
	}
}
