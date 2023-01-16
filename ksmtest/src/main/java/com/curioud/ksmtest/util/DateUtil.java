package com.curioud.ksmtest.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {
  public String getLocalDate() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate localTime = LocalDate.now();
    String date = localTime.format(format);
    return date;
  }

  public void getZoneTime() {
    ZonedDateTime zone = ZonedDateTime.now();

    System.out.println(zone);
  }
}
