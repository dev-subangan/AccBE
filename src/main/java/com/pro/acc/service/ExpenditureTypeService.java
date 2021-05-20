package com.pro.acc.service;

import java.util.List;

import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTOO;

public interface ExpenditureTypeService {

	String test();

	List<ExpenditureTypeDTO> getTypes(long id);

	List<ExpenditureTypeDTOO> geyAll();
}
