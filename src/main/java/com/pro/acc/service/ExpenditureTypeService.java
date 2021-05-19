package com.pro.acc.service;

import java.util.List;

import com.pro.acc.dto.ExpenditureTypeDTO;

public interface ExpenditureTypeService {

	String test();

	List<ExpenditureTypeDTO> getTypes(long id);
}
