package com.pro.acc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTOO;
import com.pro.acc.entity.ExpenditureMasterType;
import com.pro.acc.entity.ExpenditureType;
import com.pro.acc.repository.ExpenditureMasterTypeRepository;
import com.pro.acc.repository.ExpenditureTypeRepository;

@Service
public class ExpenditureTypeServiceImpl implements ExpenditureTypeService {

	@Autowired
	private ExpenditureMasterTypeRepository expenditureMasterTypeRepository;

	@Autowired
	private ExpenditureTypeRepository expenditureTypeRepository;

	@Override
	public String test() {

		expenditureTypeRepository.deleteAll();
		expenditureMasterTypeRepository.deleteAll();

		ExpenditureMasterType Food = new ExpenditureMasterType("Food");
		ExpenditureMasterType Breakfast = new ExpenditureMasterType("Breakfast");
		ExpenditureMasterType Lunch = new ExpenditureMasterType("Lunch");
		ExpenditureMasterType dinner = new ExpenditureMasterType("dinner");

		ExpenditureMasterType Mobile = new ExpenditureMasterType("Mobile");
		ExpenditureMasterType Reload = new ExpenditureMasterType("Reload");
		ExpenditureMasterType Telephone = new ExpenditureMasterType("Telephone");
		ExpenditureMasterType Internet = new ExpenditureMasterType("Internet");

		expenditureMasterTypeRepository
				.saveAll(Arrays.asList(Food, Breakfast, Lunch, dinner, Mobile, Reload, Telephone, Internet));

//		ExpenditureType Food_t=new ExpenditureType(null,Food);
//		ExpenditureType Breakfast_t=new ExpenditureType(null,Breakfast);
//		ExpenditureType Lunch_t=new ExpenditureType(null,Lunch);
//		ExpenditureType dinner_t=new ExpenditureType(null,dinner);
//		ExpenditureType Mobile_t=new ExpenditureType(null,Mobile);
//		ExpenditureType Reload_t=new ExpenditureType(null,Reload);
//		ExpenditureType Telephone_t=new ExpenditureType(null,Telephone);
//		ExpenditureType Internet_t=new ExpenditureType(null,Internet);
//		
//		expenditureTypeRepository.saveAll(Arrays.asList(Food_t,Breakfast_t,Lunch_t,dinner_t,Mobile_t,Reload_t,Telephone_t,Internet_t));
//		
//		
//		ExpenditureType et21=new ExpenditureType(et1,emt2);
//		ExpenditureType et22=new ExpenditureType(et1,emt2);
//		ExpenditureType et23=new ExpenditureType(et1,emt2);
//		
//		ExpenditureType et24=new ExpenditureType(et5,emt4);
//		ExpenditureType et25=new ExpenditureType(null,emt5);
//		ExpenditureType et26=new ExpenditureType(null,emt6);
//		ExpenditureType et27=new ExpenditureType(null,emt7);
//		ExpenditureType et28=new ExpenditureType(null,emt8);

		ExpenditureType Food_t = new ExpenditureType(null, Food);
		ExpenditureType Breakfast_t = new ExpenditureType(Food_t, Breakfast);
		ExpenditureType Lunch_t = new ExpenditureType(Food_t, Lunch);
		ExpenditureType dinner_t = new ExpenditureType(Food_t, dinner);

		ExpenditureType Mobile_t = new ExpenditureType(null, Mobile);
		ExpenditureType Reload_t = new ExpenditureType(Mobile_t, Reload);
		ExpenditureType Telephone_t = new ExpenditureType(Reload_t, Telephone);
		ExpenditureType Internet_t = new ExpenditureType(Reload_t, Internet);

		expenditureTypeRepository.saveAll(
				Arrays.asList(Food_t, Breakfast_t, Lunch_t, dinner_t, Mobile_t, Reload_t, Telephone_t, Internet_t));

		return "ttt";
	}

	@Override
	public List<ExpenditureTypeDTO> getTypes(long id) {

		if (id == -1) {
			return expenditureTypeRepository.findByTypeIsNull().stream().map(e -> {
				return new ExpenditureTypeDTO(e.getId(), e.getSubType().getName());
			}).collect(Collectors.toList());
		} else {
			return expenditureTypeRepository.findByType(expenditureTypeRepository.findById(id)).stream().map(e -> {
				return new ExpenditureTypeDTO(e.getId(), e.getSubType().getName());
			}).collect(Collectors.toList());
		}
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
