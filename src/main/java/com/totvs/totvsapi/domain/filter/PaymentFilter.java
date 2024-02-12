package com.totvs.totvsapi.domain.filter;

import java.util.GregorianCalendar;

public class PaymentFilter
{
	GregorianCalendar initialDate;
	GregorianCalendar endDate;

	public PaymentFilter()
	{
	}

	public GregorianCalendar getInitialDate()
	{
		return initialDate;
	}

	public void setInitialDate(GregorianCalendar initialDate)
	{
		this.initialDate = initialDate;
	}

	public GregorianCalendar getEndDate()
	{
		return endDate;
	}

	public void setEndDate(GregorianCalendar endDate)
	{
		this.endDate = endDate;
	}
}
