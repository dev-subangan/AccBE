package com.pro.acc.service;

import java.text.ParseException;
import java.util.List;

import com.pro.acc.dto.ExpenditureDTO;
import com.pro.acc.dto.MapDTO;
import com.pro.acc.util.ResultJson;

public interface ExpenditureService {

	ResultJson<String, String> save(ExpenditureDTO expenditure) throws ParseException;

	ResultJson<String, List<ExpenditureDTO>> getByDate(String date) throws ParseException;

	ResultJson<String, List<MapDTO<String, Double>>> getByMonth(String month) throws ParseException;

	ResultJson<String, String> deleteById(long id);

	ResultJson<String, List<MapDTO<String, Double>>> getByYear(String month) throws ParseException;

	ResultJson<String, List<MapDTO<String, Double>>> getAll() throws ParseException;
}
