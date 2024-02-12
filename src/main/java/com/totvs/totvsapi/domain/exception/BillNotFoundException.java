package com.totvs.totvsapi.domain.exception;

import java.util.UUID;

public class BillNotFoundException extends Exception
{
	public BillNotFoundException(UUID id)
	{
		super("Bill with id {#" + id + "} not found");
	}
}
