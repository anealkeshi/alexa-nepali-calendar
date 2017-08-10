package me.anilkc.alexa;

import java.util.Calendar;

public class NepaliDate {

  private int year;
  private int month;
  private int dayOfMonth;

  public NepaliDate(int year, int month, int dayOfMonth) {
    this.year = year;
    this.month = month;
    this.dayOfMonth = dayOfMonth;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDayOfMonth() {
    return dayOfMonth;
  }

  public void setDayOfMonth(int dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + dayOfMonth;
    result = prime * result + month;
    result = prime * result + year;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NepaliDate other = (NepaliDate) obj;
    if (dayOfMonth != other.dayOfMonth)
      return false;
    if (month != other.month)
      return false;
    if (year != other.year)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "NepaliDate [year=" + year + ", month=" + month + ", dayOfMonth=" + dayOfMonth + "]";
  }

  public int getDayOfWeek() {

    if (year != 0 && month != 0 && dayOfMonth != 0) {
      Calendar dateCal = Calendar.getInstance();
      dateCal.set(year, month, dayOfMonth);
      return dateCal.get(Calendar.DAY_OF_WEEK);
    }

    return 0;
  }

}
