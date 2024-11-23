package br.com.maxwell.medalhista.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDate convertDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, dtf);
    }
}
