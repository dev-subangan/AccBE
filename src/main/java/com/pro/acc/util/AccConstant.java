package com.pro.acc.util;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AccConstant {

	private AccConstant() {

	}

	public static final String STATUS_OK = "Ok";

	public static final String STATUS_FAILED = "Failed";

	public static final String DESCRIPTION_NOT_FOUND = "Not found";

	public static final DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static final DateTimeFormatter myMonthFormat = DateTimeFormatter.ofPattern("yyyy-MM");

	public static final List<String> months = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
			"11", "12");

}
