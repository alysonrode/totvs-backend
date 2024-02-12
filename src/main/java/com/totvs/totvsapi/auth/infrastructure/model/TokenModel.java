package com.totvs.totvsapi.auth.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(name="token")
public class TokenModel
{
	@GeneratedValue
	@Id
	private UUID id;
	private String email;
	private GregorianCalendar dueDate;

	public TokenModel(String email, GregorianCalendar dueDate)
	{
		this.email = email;
		this.dueDate = dueDate;
	}

	public TokenModel()
	{

	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
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
