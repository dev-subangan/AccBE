package com.pro.acc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
	public ResultJson<String, ExpenditureMasterTypeDTO> addMasterType(String masterType) {

		if (!expenditureMasterTypeRepository.findByName(masterType).isPresent()) {
			ExpenditureMasterType expenditureMasterType = expenditureMasterTypeRepository
					.save(new ExpenditureMasterType(masterType));
			return new ResultJson<>(AccConstant.STATUS_OK,
					new ExpenditureMasterTypeDTO(expenditureMasterType.getId(), expenditureMasterType.getName()));
		} else {
			return new ResultJson<>(AccConstant.STATUS_FAILED);
		}
	}

	@Override
	public ResultJson<String, List<ExpenditureMasterTypeDTO>> getRemainingMasterTypesForType(long typeId) {

		Optional<ExpenditureType> expenditureTypeOpt = expenditureTypeRepository.findById(typeId);
		Set<Long> existMasterTypeIdList;

		if (expenditureTypeOpt.isPresent()) {
			existMasterTypeIdList = expenditureTypeRepository.findByType(expenditureTypeOpt.get()).stream()
					.map(et -> et.getMasterType().getId()).collect(Collectors.toSet());
		} else if (typeId == 0) {
			existMasterTypeIdList = expenditureTypeRepository.findByTypeIsNull().stream()
					.map(et -> et.getMasterType().getId()).collect(Collectors.toSet());
		} else {
			return new ResultJson<>(AccConstant.STATUS_FAILED, new ArrayList<>());
		}

		List<ExpenditureMasterTypeDTO> remainingMasterTypeList = expenditureMasterTypeRepository.findAll().stream()
				.filter(expenditureMasterType -> !existMasterTypeIdList.contains(expenditureMasterType.getId()))
				.map(expenditureMasterType -> new ExpenditureMasterTypeDTO(expenditureMasterType.getId(),
						expenditureMasterType.getName()))
				.collect(Collectors.toList());

		return new ResultJson<>(AccConstant.STATUS_OK, remainingMasterTypeList);
	}

	@Override
	public ResultJson<String, String> addType(long parentTypeId, long masterTypeId) {

		Optional<ExpenditureMasterType> expenditureMasterTypeOpt = expenditureMasterTypeRepository
				.findById(masterTypeId);
		Optional<ExpenditureType> expenditureTypeOpt = expenditureTypeRepository.findById(parentTypeId);

		ExpenditureType expenditureType = new ExpenditureType();

		if (expenditureTypeOpt.isPresent()) {
			expenditureType.setType(expenditureTypeOpt.get());
		}

		if (expenditureMasterTypeOpt.isPresent()) {
			expenditureType.setMasterType(expenditureMasterTypeOpt.get());
			ExpenditureType savedExpenditureType = expenditureTypeRepository.save(expenditureType);
			return new ResultJson<>(AccConstant.STATUS_OK, savedExpenditureType.getId().toString());
		}

		return new ResultJson<>(AccConstant.STATUS_FAILED);
	}

	@Override
	public List<ExpenditureTypeDTO> geyAll() {

		List<ExpenditureTypeDTO> list = new ArrayList<>();

		for (ExpenditureType expenditureType : expenditureTypeRepository.findAll()) {
			if (expenditureType.getType() == null) {
				list.add(new ExpenditureTypeDTO(expenditureType.getId(), expenditureType.getMasterType().getName()));
			} else {
				travel(list, expenditureType);
			}
		}

		return Arrays.asList(new ExpenditureTypeDTO(0, "Expenditure", list));
	}

	@Override
	public ResultJson<String, String> getTypeById(long id) {
		return new ResultJson<>(AccConstant.STATUS_OK, getType(expenditureTypeRepository.findById(id).get(), ""));
	}

	private String getType(ExpenditureType expenditureType, String type) {
		type = expenditureType.getMasterType().getName() + "." + type;
		if (expenditureType.getType() == null) {
			return type;
		} else {
			return getType(expenditureType.getType(), type);
		}
	}

	private void travel(List<ExpenditureTypeDTO> list, ExpenditureType expenditureType) {

		for (ExpenditureTypeDTO expenditureTypeDTO : list) {
			if (expenditureTypeDTO.getId() == expenditureType.getType().getId()) {
				expenditureTypeDTO.getSubTypes().add(
						new ExpenditureTypeDTO(expenditureType.getId(), expenditureType.getMasterType().getName()));
				break;
			} else {
				travel(expenditureTypeDTO.getSubTypes(), expenditureType);
			}
		}
	}

}
