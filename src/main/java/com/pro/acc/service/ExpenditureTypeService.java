package com.pro.acc.service;

import java.util.List;

import com.pro.acc.dto.ExpenditureTypeDTOO;
import com.pro.acc.util.ResultJson;

public interface ExpenditureTypeService {

	ResultJson<String, String> addMasterType(String masterType);

	List<ExpenditureTypeDTOO> geyAll();

	ResultJson<String, String> getTypeById(long id);

}
