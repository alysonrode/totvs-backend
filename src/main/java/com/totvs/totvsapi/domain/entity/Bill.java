package com.totvs.totvsapi.domain.entity;

import com.totvs.totvsapi.infrastructure.model.BillModel;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Bill
{
	UUID id;
	GregorianCalendar dueDate;
	GregorianCalendar payday;
	BigDecimal value;
	String description;
	SituationEnum situation;

	public Bill()
	{
	}

	public Bill(GregorianCalendar dueDate, GregorianCalendar payday, BigDecimal value,
		String description, SituationEnum situation)
	{
		this.dueDate = dueDate;
		this.payday = payday;
		this.value = value;
		this.description = description;
		this.situation = situation;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}

	public void setDueDate(GregorianCalendar dueDate)
	{
		this.dueDate = dueDate;
	}

	public GregorianCalendar getPayday()
	{
		return payday;
	}

	public void setPayday(GregorianCalendar payday)
	{
		this.payday = payday;
	}

	public BigDecimal getValue()
	{
		return value;
	}

	public void setValue(BigDecimal value)
	{
		this.value = value;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public SituationEnum getSituation()
	{
		return situation;
	}

	public void setSituation(SituationEnum situation)
	{
		this.situation = situation;
	}

	public Bill build(BillModel model)
	{
		this.id = model.getId();
		this.dueDate = model.getDueDate();
		this.payday = model.getPayday();
		this.value = model.getValue();
		this.description = model.getDescription();
		this.situation = model.getSituation();
		return this;
	}

	public Bill build(String[] bill)
	{
		dueDate = parseDate(bill[0]);
		payday = parseDate(bill[1]);
		value = new BigDecimal(bill[2]);
		description = bill[3];
		situation = SituationEnum.valueOf(bill[4].toUpperCase());

		return this;
	}

	private GregorianCalendar parseDate(String date)
	{
		GregorianCalendar cal;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Date d;
		try
		{
			d = df.parse(date);
			cal = new GregorianCalendar();
			cal.setTime(d);
		}
		catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
		return cal;
	}

}
