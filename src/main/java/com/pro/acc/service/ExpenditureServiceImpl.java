package com.pro.acc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.acc.dto.ExpenditureDTO;
import com.pro.acc.dto.MapDTO;
import com.pro.acc.entity.Expenditure;
import com.pro.acc.entity.ExpenditureType;
import com.pro.acc.repository.ExpenditureRepository;
import com.pro.acc.util.AccConstant;
import com.pro.acc.util.ResultJson;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {

	@Autowired
	private ExpenditureRepository expenditureRepository;

	@Override
	public ResultJson<String, String> save(ExpenditureDTO expenditureDTO) throws ParseException {

		ExpenditureType expenditureType = new ExpenditureType();
		expenditureType.setId(expenditureDTO.getTypeId());

		Expenditure expenditure = new Expenditure();

		expenditure.setDate(getMyDateFormat().parse(expenditureDTO.getDate()));
		expenditure.setAmount(expenditureDTO.getAmount());
		expenditure.setType(expenditureType);
		expenditure.setNote(expenditureDTO.getNote());

		expenditureRepository.save(expenditure);

		return (new ResultJson<String, String>(AccConstant.STATUS_OK));
	}

	@Override
	public ResultJson<String, List<ExpenditureDTO>> getByDate(String date) throws ParseException {

		List<ExpenditureDTO> expenditureDTOList = expenditureRepository
				.findByExpenditureDate(getMyDateFormat().parse(date)).stream().map(expenditure -> {
					ExpenditureDTO expenditureDTO = new ExpenditureDTO();
					expenditureDTO.setId(expenditure.getId());
					expenditureDTO.setDate(expenditure.getDate().toString());
					expenditureDTO.setTypeId(expenditure.getType().getId());
					expenditureDTO.setNote(expenditure.getNote());
					expenditureDTO.setAmount(expenditure.getAmount());
					return expenditureDTO;
				}).sorted(Comparator.comparing(ExpenditureDTO::getId).reversed()).collect(Collectors.toList());

		return (new ResultJson<String, List<ExpenditureDTO>>(AccConstant.STATUS_OK, expenditureDTOList));
	}

	@Override
	public ResultJson<String, List<MapDTO<String, Double>>> getByMonth(String month) throws ParseException {
		List<MapDTO<String, Double>> days = new ArrayList<>();
		boolean isExist;

		for (Expenditure expenditure : expenditureRepository.findByExpenditureDateBetween(
				getMyDateFormat().parse(month + "-01"), getMyDateFormat().parse(getMonthEndDate(month)))) {
			isExist = false;
			for (MapDTO<String, Double> day : days) {
				if (day.getKey().equals(expenditure.getDate().toString())) {
					day.setValue(day.getValue() + expenditure.getAmount());
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				days.add(new MapDTO<>(expenditure.getDate().toString(), expenditure.getAmount()));
			}
		}

		days.sort(Comparator.comparing(MapDTO::getKey));

		return (new ResultJson<String, List<MapDTO<String, Double>>>(AccConstant.STATUS_OK, days));
	}

	@Override
	public ResultJson<String, List<MapDTO<String, Double>>> getByYear(String year) throws ParseException {
		List<MapDTO<String, Double>> months = new ArrayList<>();
		boolean isExist;

		for (String monthNo : AccConstant.months) {
			for (Expenditure expenditure : expenditureRepository.findByExpenditureDateBetween(
					getMyDateFormat().parse(String.join("-", year, monthNo, "01")),
					getMyDateFormat().parse(getMonthEndDate(String.join("-", year, monthNo))))) {
				isExist = false;
				for (MapDTO<String, Double> month : months) {
					if (month.getKey().equals(getMyMonthFormat().format(expenditure.getDate()))) {
						month.setValue(month.getValue() + expenditure.getAmount());
						isExist = true;
						break;
					}
				}

				if (!isExist) {
					months.add(new MapDTO<>(getMyMonthFormat().format(expenditure.getDate()), expenditure.getAmount()));
				}
			}
		}

		months.sort(Comparator.comparing(MapDTO::getKey));

		return (new ResultJson<String, List<MapDTO<String, Double>>>(AccConstant.STATUS_OK, months));
	}

	@Override
	public ResultJson<String, List<MapDTO<String, Double>>> getAll() throws ParseException {
		List<MapDTO<String, Double>> years = new ArrayList<>();
		boolean isExist;

		for (Expenditure expenditure : expenditureRepository.findAll()) {
			isExist = false;
			for (MapDTO<String, Double> year : years) {
				if (year.getKey().equals(getMyYearFormat().format(expenditure.getDate()))) {
					year.setValue(year.getValue() + expenditure.getAmount());
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				years.add(new MapDTO<>(getMyYearFormat().format(expenditure.getDate()), expenditure.getAmount()));
			}
		}

		years.sort(Comparator.comparing(MapDTO::getKey));

		return (new ResultJson<String, List<MapDTO<String, Double>>>(AccConstant.STATUS_OK, years));
	}

	@Override
	public ResultJson<String, String> deleteById(long id) {
		expenditureRepository.deleteById(id);
		return (new ResultJson<String, String>(AccConstant.STATUS_OK));
	}

	private SimpleDateFormat getMyDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	private SimpleDateFormat getMyMonthFormat() {
		return new SimpleDateFormat("yyyy-MM");
	}

	private SimpleDateFormat getMyYearFormat() {
		return new SimpleDateFormat("yyyy");
	}

	private String getMonthEndDate(String month) {
		int year = Integer.parseInt(month.substring(0, 4));
		int monthVal = Integer.parseInt(month.substring(5, 7));

		if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(monthVal)) {
			return month + "-31";
		} else if (2 == monthVal) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				return month + "-29";
			} else {
				return month + "-28";
			}
		} else {
			return month + "-30";
		}
	}

}
