package com.pro.acc.service;

import java.util.List;

import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTOO;
import com.pro.acc.util.ResultJson;

public interface ExpenditureTypeService {

	String test();

	List<ExpenditureTypeDTO> getTypes(long id);
	
	ResultJson<String, String> addMasterType(String masterType);

	List<ExpenditureTypeDTOO> geyAll();

}
