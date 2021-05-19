package com.pro.acc.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Expenditure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "expenditureId", updatable = false, nullable = false)
	private Long expenditureId;

	private Date expenditureDate;

	private double expenditureAmount;

	@ManyToOne
	private ExpenditureType expenditureType;

	private String expenditureNote;

	@CreationTimestamp
	private LocalDateTime expenditureCreated;

	public Long getId() {
		return expenditureId;
	}

	public void setId(Long expenditureId) {
		this.expenditureId = expenditureId;
	}

	public Date getDate() {
		return expenditureDate;
	}

	public void setDate(Date expenditureDate) {
		this.expenditureDate = expenditureDate;
	}

	public double getAmount() {
		return expenditureAmount;
	}

	public void setAmount(double expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}

	public ExpenditureType getType() {
		return expenditureType;
	}

	public void setType(ExpenditureType expenditureType) {
		this.expenditureType = expenditureType;
	}

	public String getNote() {
		return expenditureNote;
	}

	public void setNote(String expenditureNote) {
		this.expenditureNote = expenditureNote;
	}

}
