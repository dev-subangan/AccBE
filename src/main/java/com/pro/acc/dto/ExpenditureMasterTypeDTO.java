package com.pro.acc.dto;

public class ExpenditureMasterTypeDTO {

	private long id;
	
	private String name;

	public ExpenditureMasterTypeDTO(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
