package com.pro.acc.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pro.acc.dto.ExpenditureDTO;
import com.pro.acc.dto.MapDTO;
import com.pro.acc.service.ExpenditureService;
import com.pro.acc.util.ResultJson;

@RestController
@RequestMapping("/acc/expenditure")
@CrossOrigin
public class ExpenditureController {

	@Autowired
	private ExpenditureService expenditureService;

	@PostMapping("/save")
	public ResultJson<String, String> save(@RequestBody ExpenditureDTO expenditureDTO) throws ParseException {
		return this.expenditureService.save(expenditureDTO);
	}

	@GetMapping("/getByDate/{date}")
	public ResultJson<String, List<ExpenditureDTO>> getByDate(@PathVariable String date) throws ParseException {
		return this.expenditureService.getByDate(date);
	}

	@GetMapping("/getByMonth/{month}")
	public ResultJson<String, List<MapDTO<String, Double>>> getByMonth(@PathVariable String month)
			throws ParseException {
		return this.expenditureService.getByMonth(month);
	}

	@GetMapping("/getByYear/{year}")
	public ResultJson<String, List<MapDTO<String, Double>>> getByYear(@PathVariable String year) throws ParseException {
		return this.expenditureService.getByYear(year);
	}

	@GetMapping("/getAll")
	public ResultJson<String, List<MapDTO<String, Double>>> getAll() throws ParseException {
		return this.expenditureService.getAll();
	}

	@GetMapping("/delete/{id}")
	public ResultJson<String, String> deleteById(@PathVariable long id) {
		return this.expenditureService.deleteById(id);
	}

}
