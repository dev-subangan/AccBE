package com.pro.acc.dto;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureTypeDTOO {
	
	private long id;
	
	private String name;
	
	private List<ExpenditureTypeDTOO> subTypes;
	
	public ExpenditureTypeDTOO(long id, String name) {
		this.id = id;
		this.name = name;
		this.subTypes = new ArrayList<>();
	}

	public ExpenditureTypeDTOO(int id, String name, List<ExpenditureTypeDTOO> subTypes) {
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

	public List<ExpenditureTypeDTOO> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<ExpenditureTypeDTOO> subTypes) {
		this.subTypes = subTypes;
	}
	
	

}
