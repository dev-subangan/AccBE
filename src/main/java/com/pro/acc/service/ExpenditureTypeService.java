package com.pro.acc.service;

import java.util.List;

import com.pro.acc.dto.ExpenditureMasterTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.util.ResultJson;

public interface ExpenditureTypeService {

	ResultJson<String, String> addMasterType(String masterType);

	ResultJson<String, List<ExpenditureMasterTypeDTO>> getRemainingMasterTypesForType(long typeId);

	ResultJson<String, String> addType(long parentTypeId, long masterTypeId);

	List<ExpenditureTypeDTO> geyAll();

	ResultJson<String, String> getTypeById(long id);

}
