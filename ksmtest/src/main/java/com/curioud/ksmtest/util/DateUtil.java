package com.curioud.ksmtest.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtil {
  public String getZoneTime() {
    ZonedDateTime zone = ZonedDateTime.now();
    String date = zone.toString();
    return date;
  }

  public String getZoneTimeDateFormat(String date) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
    ZonedDateTime zone = ZonedDateTime.parse(date);
    String dateFormat = zone.format(format);
    return dateFormat;
  }
}
