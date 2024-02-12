package com.totvs.totvsapi.auth.domain.service;

import com.totvs.totvsapi.auth.infrastructure.model.TokenModel;
import com.totvs.totvsapi.auth.infrastructure.repository.TokenRepository;
import java.util.GregorianCalendar;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService
{
	@Autowired
	TokenRepository repository;
	public boolean validateToken(UUID token, String email)
	{
		var tokenModel = repository.findById(token);
		if (tokenModel.isEmpty())
		{
			return false;
		}
		if (!tokenModel.get().getEmail().equals(email))
		{
			return false;
		}
		if (tokenModel.get().getDueDate().compareTo(new GregorianCalendar()) < 0)
		{
			return false;
		}
		return true;
	}
	public UUID generateToken(String email)
	{
		var dueDate = new GregorianCalendar();
		dueDate.add(GregorianCalendar.DATE, 1);
		var token = repository.save(new TokenModel(email, dueDate)).getId();
		return token;
	}
}
