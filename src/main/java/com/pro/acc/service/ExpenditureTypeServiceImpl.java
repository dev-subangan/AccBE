package com.pro.acc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.acc.dto.ExpenditureMasterTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTO;
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
	public ResultJson<String, List<ExpenditureMasterTypeDTO>> getRemainingMasterTypesForType(long typeId) {
		Set<Long> existMasterTypeIdList = expenditureTypeRepository
				.findByType(expenditureTypeRepository.findById(typeId).get()).stream().map(expenditureType -> {
					return expenditureType.getSubType().getId();
				}).collect(Collectors.toSet());

		List<ExpenditureMasterTypeDTO> remainingMasterTypeList = expenditureMasterTypeRepository.findAll().stream()
				.filter(expenditureMasterType -> !existMasterTypeIdList.contains(expenditureMasterType.getId()))
				.map(expenditureMasterType -> {
					return new ExpenditureMasterTypeDTO(expenditureMasterType.getId(), expenditureMasterType.getName());
				}).collect(Collectors.toList());

		return new ResultJson<>(AccConstant.STATUS_OK, remainingMasterTypeList);
	}

	@Override
	public ResultJson<String, String> addType(long parentTypeId, long masterTypeId) {

		ExpenditureType expenditureType = new ExpenditureType();
		expenditureType.setType(expenditureTypeRepository.findById(parentTypeId).get());
		expenditureType.setSubType(expenditureMasterTypeRepository.findById(masterTypeId).get());

		expenditureTypeRepository.save(expenditureType);

		return new ResultJson<>(AccConstant.STATUS_OK);
	}

	@Override
	public List<ExpenditureTypeDTO> geyAll() {

		List<ExpenditureTypeDTO> list = new ArrayList<>();

		for (ExpenditureType expenditureType : expenditureTypeRepository.findAll()) {
			if (expenditureType.getType() == null) {
				list.add(new ExpenditureTypeDTO(expenditureType.getId(), expenditureType.getSubType().getName()));
			} else {
				travel(list, expenditureType);
			}
		}

		return Arrays.asList(new ExpenditureTypeDTO(0, "Expenditure", list));
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

	private void travel(List<ExpenditureTypeDTO> list, ExpenditureType expenditureType) {

		for (ExpenditureTypeDTO expenditureTypeDTO : list) {
			if (expenditureTypeDTO.getId() == expenditureType.getType().getId()) {
				expenditureTypeDTO.getSubTypes()
						.add(new ExpenditureTypeDTO(expenditureType.getId(), expenditureType.getSubType().getName()));
				break;
			} else {
				travel(expenditureTypeDTO.getSubTypes(), expenditureType);
			}
		}
	}

}
