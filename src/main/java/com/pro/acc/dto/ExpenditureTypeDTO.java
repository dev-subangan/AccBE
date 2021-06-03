package com.pro.acc.dto;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureTypeDTO {

	private long id;

	private String name;

	private List<ExpenditureTypeDTO> subTypes;

	public ExpenditureTypeDTO(long id, String name) {
		this.id = id;
		this.name = name;
		this.subTypes = new ArrayList<>();
	}

	public ExpenditureTypeDTO(int id, String name, List<ExpenditureTypeDTO> subTypes) {
		this.id = id;
		this.name = name;
		this.subTypes = subTypes;
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

	public List<ExpenditureTypeDTO> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<ExpenditureTypeDTO> subTypes) {
		this.subTypes = subTypes;
	}

}
