package com.pro.acc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ExpenditureType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;

	@ManyToOne
	private ExpenditureType type;

	@ManyToOne
	private ExpenditureMasterType masterType;

	public ExpenditureType() {
	}

	public ExpenditureType(ExpenditureType type, ExpenditureMasterType masterType) {
		this.type = type;
		this.masterType = masterType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ExpenditureType getType() {
		return type;
	}

	public void setType(ExpenditureType type) {
		this.type = type;
	}

	public ExpenditureMasterType getMasterType() {
		return masterType;
	}

	public void setMasterType(ExpenditureMasterType masterType) {
		this.masterType = masterType;
	}

}
