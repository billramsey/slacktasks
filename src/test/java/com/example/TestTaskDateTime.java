package com.example;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;



public class TestTaskDateTime {
  @Test
  public void testTaskTimeMonth() {
    TaskDateTime taskTime = new TaskDateTime("month", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    
    DateTimeFormatter fmt =
        DateTimeFormat.forPattern("dd/MM/yyyy:HH:mm:ss");

    assertEquals("01/03/2015:00:00:00", fmt.print(taskTime.getStart()));
    assertEquals("31/03/2015:23:59:59", fmt.print(taskTime.getEnd()));
    
    System.out.println(fmt.print(taskTime.getStart()));
  }
  @Test
  public void testTaskDateQuarterCurrentYear() {
    
    DateTimeFormatter fmt =
        DateTimeFormat.forPattern("dd/MM/yyyy:HH:mm:ss");
    
    TaskDateTime taskTime = new TaskDateTime("q1", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    assertEquals("01/01/2015:00:00:00", fmt.print(taskTime.getStart()));
    assertEquals("31/03/2015:23:59:59", fmt.print(taskTime.getEnd()));
    
    TaskDateTime taskTime2 = new TaskDateTime("q2", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    assertEquals("01/04/2015:00:00:00", fmt.print(taskTime2.getStart()));
    assertEquals("30/06/2015:23:59:59", fmt.print(taskTime2.getEnd()));
    
    TaskDateTime taskTime3 = new TaskDateTime("q3", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    assertEquals("01/07/2015:00:00:00", fmt.print(taskTime3.getStart()));
    assertEquals("30/09/2015:23:59:59", fmt.print(taskTime3.getEnd()));
      
    TaskDateTime taskTime4 = new TaskDateTime("q4", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    assertEquals("01/10/2015:00:00:00", fmt.print(taskTime4.getStart()));
    assertEquals("31/12/2015:23:59:59", fmt.print(taskTime4.getEnd()));
      
    
    System.out.println(fmt.print(taskTime.getStart()));
  }
  @Test
  public void testTaskDateQuarterSetYear() {
    TaskDateTime taskTime = new TaskDateTime("q12014", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    
    DateTimeFormatter fmt =
        DateTimeFormat.forPattern("dd/MM/yyyy:HH:mm:ss");

    assertEquals("01/01/2014:00:00:00", fmt.print(taskTime.getStart()));
    assertEquals("31/03/2014:23:59:59", fmt.print(taskTime.getEnd()));
    
    System.out.println(fmt.print(taskTime.getStart()));
  }
  
  
  @Test
  public void testTaskDateMonth3CharNoYear() {
    TaskDateTime taskTime = new TaskDateTime("Mar", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    
    DateTimeFormatter fmt =
        DateTimeFormat.forPattern("dd/MM/yyyy:HH:mm:ss");

    assertEquals("01/03/2015:00:00:00", fmt.print(taskTime.getStart()));
    assertEquals("31/03/2015:23:59:59", fmt.print(taskTime.getEnd()));
    
    System.out.println(fmt.print(taskTime.getStart()));
  }
  
  @Test
  public void testTaskDateMonth3CharWithYear() {
    TaskDateTime taskTime = new TaskDateTime("Mar/2014", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    
    DateTimeFormatter fmt =
        DateTimeFormat.forPattern("dd/MM/yyyy:HH:mm:ss");

    assertEquals("01/03/2014:00:00:00", fmt.print(taskTime.getStart()));
    assertEquals("31/03/2014:23:59:59", fmt.print(taskTime.getEnd()));
    
    System.out.println(fmt.print(taskTime.getStart()));
  }
  
  @Test
  public void testBadDates() {
    boolean gotIllegalArgumentException = false;
    try {
      TaskDateTime taskTime = new TaskDateTime("q72014", new DateTime(2015, 3, 25, 12, 0, 0, 0));
    } catch (IllegalArgumentException ex) {
      gotIllegalArgumentException = true;
    }
    assertTrue(gotIllegalArgumentException);
    
  }
  
  
}
