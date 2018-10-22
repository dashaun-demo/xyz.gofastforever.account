package xyz.gofastforever.account;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TestUtil {

  public static Date getDate(int year, int month, int date){
    Calendar utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    utcCal.set(year, month, date);
    utcCal.set(Calendar.HOUR_OF_DAY, 14);
    utcCal.set(Calendar.MINUTE, 48);
    utcCal.set(Calendar.SECOND, 47);
    return utcCal.getTime();
  }

}
