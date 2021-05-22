package com.pro.acc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.acc.dto.ExpenditureMasterTypeDTO;
import com.pro.acc.dto.ExpenditureTypeDTO;
import com.pro.acc.service.ExpenditureTypeService;
import com.pro.acc.util.ResultJson;

@RestController
@RequestMapping("/acc/expenditure/type")
@CrossOrigin
public class ExpenditureTypeController {

	@Autowired
	private ExpenditureTypeService expenditureTypeService;

	@PostMapping("/addMasterType")
	public ResultJson<String, String> addMasterType(@RequestBody Map<String, String> masterTypeObj) {
		return this.expenditureTypeService.addMasterType(masterTypeObj.get("masterType"));
	}

	@GetMapping("/remainingMasterType/{typeId}")
	public ResultJson<String, List<ExpenditureMasterTypeDTO>> getRemainingMasterTypesForType(
			@PathVariable long typeId) {
		return this.expenditureTypeService.getRemainingMasterTypesForType(typeId);
	}

	@PostMapping("/addType")
	public ResultJson<String, String> addType(@RequestBody Map<String, Long> typeObj) {
		return this.expenditureTypeService.addType(typeObj.get("parentTypeId"), typeObj.get("masterTypeId"));
	}

	@GetMapping("/all")
	public List<ExpenditureTypeDTO> getAllTypes() {
		return this.expenditureTypeService.geyAll();
	}

	@GetMapping("/{id}")
	public ResultJson<String, String> getAllTypes(@PathVariable long id) {
		return this.expenditureTypeService.getTypeById(id);
	}
}
