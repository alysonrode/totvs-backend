package com.totvs.totvsapi.bill.domain.filter;

import java.util.GregorianCalendar;

public class BillFilter
{
	GregorianCalendar dueDate;
	String description;

	public BillFilter()
	{
	}

	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(GregorianCalendar dueDate)
	{
		this.dueDate = dueDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
