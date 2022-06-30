package com.ace.studentmanagement.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentDate {
	public static String now() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY.MM.dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		return date;
	}
}
