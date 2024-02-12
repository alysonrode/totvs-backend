package com.totvs.totvsapi.auth.domain.entity;

import java.util.GregorianCalendar;
import java.util.UUID;

public class Token
{
	UUID token;
	String email;
	GregorianCalendar dueDate;

	public Token()
	{
	}

	public UUID getToken()
	{
		return token;
	}

	public void setToken(UUID token)
	{
		this.token = token;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(GregorianCalendar dueDate)
	{
		this.dueDate = dueDate;
	}
}
