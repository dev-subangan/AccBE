package com.pro.acc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.service.ExpenditureTypeService;

@RestController
@RequestMapping("/acc/expenditure/type")
@CrossOrigin
public class ExpenditureTypeController {

	@Autowired
	private ExpenditureTypeService expenditureTypeService;

	@GetMapping("/test")
	public String test(){
		return this.expenditureTypeService.test();
	}
	
	@GetMapping("/type/{id}")
	public List<ExpenditureTypeDTO> getType(@PathVariable Long id){
		return this.expenditureTypeService.getTypes(id);
	}
}
