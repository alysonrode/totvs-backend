package com.totvs.totvsapi.infrastructure.model;


import com.totvs.totvsapi.domain.entity.SituationEnum;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(name = "bill")
public class BillModel
{

	@Id
	@GeneratedValue
	private UUID id;
	@Column(name="dueDate")
	private GregorianCalendar dueDate;
	@Column(name="payday")
	private GregorianCalendar payday;
	@Column(name="value")
	private BigDecimal value;
	@Column(name="description")
	private String description;
	@Column(name="situation")
	private SituationEnum situation;

	public BillModel()
	{
	}

	public BillModel(GregorianCalendar dueDate, GregorianCalendar payday, BigDecimal value,
		String description, SituationEnum situation)
	{
		this.dueDate = dueDate;
		this.payday = payday;
		this.value = value;
		this.description = description;
		this.situation = situation;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public UUID getId()
	{
		return id;
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
}
