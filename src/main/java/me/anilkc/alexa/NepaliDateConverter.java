package me.anilkc.alexa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class NepaliDateConverter {


  private static Map<Integer, int[]> nepaliDateMap = new LinkedHashMap<Integer, int[]>();

  static {
    nepaliDateMap.put(2000, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2001, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2002, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2003, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2004, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2005, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2006, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2007, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2008, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
    nepaliDateMap.put(2009, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2010, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2011, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2012, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
    nepaliDateMap.put(2013, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2014, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2015, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2016, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
    nepaliDateMap.put(2017, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2018, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2019, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2020, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2021, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2022, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
    nepaliDateMap.put(2023, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2024, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2025, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2026, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2027, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2028, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2029, new int[] {0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2030, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2031, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2032, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2033, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2034, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2035, new int[] {0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
    nepaliDateMap.put(2036, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2037, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2038, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2039, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
    nepaliDateMap.put(2040, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2041, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2042, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2043, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
    nepaliDateMap.put(2044, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2045, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2046, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2047, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2048, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2049, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
    nepaliDateMap.put(2050, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2051, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2052, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2053, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
    nepaliDateMap.put(2054, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2055, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2056, new int[] {0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2057, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2058, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2059, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2060, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2061, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2062, new int[] {0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2063, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2064, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2065, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2066, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
    nepaliDateMap.put(2067, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2068, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2069, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2070, new int[] {0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
    nepaliDateMap.put(2071, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2072, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2073, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
    nepaliDateMap.put(2074, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2075, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2076, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
    nepaliDateMap.put(2077, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
    nepaliDateMap.put(2078, new int[] {0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2079, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
    nepaliDateMap.put(2080, new int[] {0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
    nepaliDateMap.put(2081, new int[] {0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2082, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2083, new int[] {0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2084, new int[] {0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2085, new int[] {0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2086, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2087, new int[] {0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2088, new int[] {0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2089, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
    nepaliDateMap.put(2090, new int[] {0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
  }

  public static LocalDate getBaseNepaliDate() {
    return LocalDate.of(2000, 1, 1);
  }

  public static LocalDate getBaseEnglishDate() {
    return LocalDate.of(1943, 4, 14);
  }

  public static LocalDate getMaxNepaliDate() {
    return LocalDate.of(2090, 12, 30);
  }

  public static LocalDate getMaxEnglishDate() {
    return LocalDate.of(2034, 4, 13);
  }

  /**
   * Converts English Date to Nepali Date
   * <ul>
   * <li>Calculates the days in between base English date and provided English Date</li>
   * <li>Subtract the days of month from difference days we found in step 1 for each month of each
   * year until we get the day of the particular month</li>
   * </ul>
   * 
   * @param date
   * @return
   */
  public static Date convertEnglishToNepalDate(Date date) {
    LocalDate englishDate = AlexaDateUtil.getLocalDateFromDate(date);
    int differenceInDays = (int) ChronoUnit.DAYS.between(getBaseEnglishDate(), englishDate);
    // including today's date
    differenceInDays++;
    Set<Integer> keySet = nepaliDateMap.keySet();
    Iterator<Integer> iterator = keySet.iterator();

    while (iterator.hasNext()) {
      int year = iterator.next();
      int[] daysInMonthInYear = nepaliDateMap.get(year);
      for (int month = 0; month < daysInMonthInYear.length; month++) {
        // if differenceInDays is greater than days in month that means we haven't found our month
        // Go to next month
        if (differenceInDays > daysInMonthInYear[month]) {
          // Subtract days in month from total difference
          differenceInDays = differenceInDays - daysInMonthInYear[month];
        } else {
          // Found the year and month and day
          return AlexaDateUtil.getDateFromLocalDate(LocalDate.of(year, month, differenceInDays));
        }
      }
    }
    throw new IllegalArgumentException("Something went unexpected");
  }

  /**
   * Converts Nepali date to English date.
   * <ul>
   * <li>Calculates the days in between base Nepali date and provided Nepali Date</li>
   * <li>Adds difference days in base English date to get converted English Date</li>
   * </ul>
   * 
   * @param nepaliDate
   * @return
   */
  public static Date convertNepaliToEnglishDate(Date date) {
    LocalDate nepaliDate = AlexaDateUtil.getLocalDateFromDate(date);
    return AlexaDateUtil.getDateFromLocalDate(getBaseEnglishDate().plusDays(getNepaliDaysBetween(nepaliDate)));
  }

  /**
   * Get the days between base Nepali date and provided Nepali date.
   * 
   * @param nepaliDate
   * @return
   */
  private static int getNepaliDaysBetween(LocalDate nepaliDate) {
    Set<Integer> keySet = nepaliDateMap.keySet();
    Iterator<Integer> iterator = keySet.iterator();
    int differenceDays = 0;
    while (iterator.hasNext()) {
      int year = iterator.next();
      int[] daysInMonthInYear = nepaliDateMap.get(year);
      for (int month = 1; month < daysInMonthInYear.length; month++) {
        if (year == nepaliDate.getYear() && month == nepaliDate.getMonthValue()) {
          differenceDays = differenceDays + nepaliDate.getDayOfMonth();
          return differenceDays - 1;
        }
        differenceDays = differenceDays + daysInMonthInYear[month];
      }
    }
    throw new IllegalArgumentException("Something went unexpected");
  }
}
