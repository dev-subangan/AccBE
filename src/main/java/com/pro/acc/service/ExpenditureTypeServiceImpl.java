package com.pro.acc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.acc.dto.ExpenditureTypeDTOO;
import com.pro.acc.entity.ExpenditureMasterType;
import com.pro.acc.entity.ExpenditureType;
import com.pro.acc.repository.ExpenditureMasterTypeRepository;
import com.pro.acc.repository.ExpenditureTypeRepository;
import com.pro.acc.util.AccConstant;
import com.pro.acc.util.ResultJson;

@Service
public class ExpenditureTypeServiceImpl implements ExpenditureTypeService {

	@Autowired
	private ExpenditureMasterTypeRepository expenditureMasterTypeRepository;

	@Autowired
	private ExpenditureTypeRepository expenditureTypeRepository;

	@Override
	public ResultJson<String, String> addMasterType(String masterType) {
		expenditureMasterTypeRepository.save(new ExpenditureMasterType(masterType));
		return new ResultJson<>(AccConstant.STATUS_OK, masterType);
	}

	@Override
	public List<ExpenditureTypeDTOO> geyAll() {

		List<ExpenditureTypeDTOO> list = new ArrayList<>();

		for (ExpenditureType expenditureType : expenditureTypeRepository.findAll()) {
			if (expenditureType.getType() == null) {
				list.add(new ExpenditureTypeDTOO(expenditureType.getId(), expenditureType.getSubType().getName()));
			} else {
				travel(list, expenditureType);
			}
		}

		return Arrays.asList(new ExpenditureTypeDTOO(0, "Expenditure", list));
	}

	@Override
	public ResultJson<String, String> getTypeById(long id) {
		return new ResultJson<>(AccConstant.STATUS_OK,
				getType(expenditureTypeRepository.findById(id).get(), "").replaceAll(".$", ""));
	}

	private String getType(ExpenditureType expenditureType, String type) {
		type = expenditureType.getSubType().getName() + "." + type;
		if (expenditureType.getType() == null) {
			return type;
		} else {
			return getType(expenditureType.getType(), type);
		}
	}

	private void travel(List<ExpenditureTypeDTOO> list, ExpenditureType expenditureType) {

		for (ExpenditureTypeDTOO expenditureTypeDTO : list) {
			if (expenditureTypeDTO.getId() == expenditureType.getType().getId()) {
				expenditureTypeDTO.getSubTypes()
						.add(new ExpenditureTypeDTOO(expenditureType.getId(), expenditureType.getSubType().getName()));
				break;
			} else {
				travel(expenditureTypeDTO.getSubTypes(), expenditureType);
			}
		}
	}

}
