package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;


public class TaskDateTime {
  /*
   * anything less than 2000 or greater than 4000 should fail.
   *  if you are using this in the past or in 4001 I'm sorry.
   */
  public static final int MAX_YEAR = 4000;
  public static final int MIN_YEAR = 2000;
  
  
  private DateTime start;
  private DateTime end;

  
  /*  Take the input year string and convert to a year.
   *  Throws IllegalInputException on bad input
   *  TODO : 15 could convert to 2015
   *  
   */
  private static int convertYear(String inputYear) {
    int year = 0;
    try {
      year = Integer.parseInt(inputYear);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException("Received inputyear: " + inputYear); 
    }
    if (year < MIN_YEAR || year > MAX_YEAR) {
      throw new IllegalArgumentException();
    }
    return year;
  }
  
  /*
   * Get the start of a quarter based on the calendar year.
   */
  private DateTime getStartOfQuarter(int quarterNumber, int year ) {
    DateTime dt = new DateTime().withDayOfMonth(1).withMonthOfYear(quarterNumber * 3 - 2)
        .withYear(year).withTime(0, 0, 0, 0);
    return dt;
  }
  
  /*
   * Get the end of a quarter based on the calendar year.
   */
  private DateTime getEndOfQuarter(int quarterNumber, int year ) {
    DateTime dt = new DateTime().withMonthOfYear(quarterNumber * 3).dayOfMonth().withMaximumValue()
        .withYear(year).withTime(23, 59, 59, 999);
    return dt;
  }
  
  /*
   * TaskDateTime takes input from the command text.  Valid text
   * now | q3 | q32015 | Jan | Mar/2015
   * Anything out of bounds throws an IllegalArgumentException()
   * Only years 2000 - 4000 are acceptable
   * 
   * At a class level, currently takes a current time to allow testing
   * of current year when unspecified.
   * 
   */
  //
  public TaskDateTime() {
    
  }
  public TaskDateTime(String timeframe, DateTime now) {
    
    if (timeframe.equals("month")) {
      start = now.withDayOfMonth(1).withTime(0,0,0,0);
      end = now.dayOfMonth().withMaximumValue().withTime(23,59,59,999);
    } else if(timeframe.startsWith("q")) {
      Pattern p = Pattern.compile("q([0-4]{1})([0-9]*)");
      Matcher m = p.matcher(timeframe);
      
      if (m.find()) {
        if (m.group(1) == null) {
          throw new IllegalArgumentException();
        }
        
        int quarter = Integer.parseInt(m.group(1));
        
        int year = now.getYear();
        if(m.group(2) != null && !m.group(2).equals("")) {
          year = convertYear(m.group(2));
        }

        start = getStartOfQuarter(quarter, year);
        end = getEndOfQuarter(quarter, year);
      } else {
        throw new IllegalArgumentException();
      }
    } else {

      
      DateTime monthOnlyDateTime = null;
      
      /* Try the MMM/year
       * if that fails, try MMM with current year.
       * if that fails, throw the IllegalArgumentException  
       */
      try {
        DateTimeFormatter shortMonthSlashYear = new DateTimeFormatterBuilder().
            appendMonthOfYearShortText().appendLiteral('/').appendYear(4, 4).toFormatter();
        monthOnlyDateTime = shortMonthSlashYear.parseDateTime(timeframe);
      } catch (IllegalArgumentException ex) {
        DateTimeFormatter shortMonth = new DateTimeFormatterBuilder().
            appendMonthOfYearShortText().toFormatter();
        monthOnlyDateTime = shortMonth.parseDateTime(timeframe).withYear(now.getYear());
      }
      
      
      
      start = monthOnlyDateTime.withDayOfMonth(1).withTime(0,0,0,0);
      end = monthOnlyDateTime.dayOfMonth().withMaximumValue().withTime(23,59,59,999);
    }
    
    
    
    
    
    
  }
  

  public DateTime getStart() {
    return start;
  }
  
  public DateTime getEnd() {
    return end;
  }
  
}
