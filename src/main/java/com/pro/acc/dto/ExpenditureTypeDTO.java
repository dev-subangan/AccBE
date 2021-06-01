package com.pro.acc.dto;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureTypeDTO {

	private long id;

	private String name;

	private List<ExpenditureTypeDTO> masterTypes;

	public ExpenditureTypeDTO(long id, String name) {
		this.id = id;
		this.name = name;
		this.masterTypes = new ArrayList<>();
	}

	public ExpenditureTypeDTO(int id, String name, List<ExpenditureTypeDTO> masterTypes) {
		this.id = id;
		this.name = name;
		this.masterTypes = masterTypes;
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

	public List<ExpenditureTypeDTO> getMasterTypes() {
		return masterTypes;
	}

	public void setMasterTypes(List<ExpenditureTypeDTO> masterTypes) {
		this.masterTypes = masterTypes;
	}

}
