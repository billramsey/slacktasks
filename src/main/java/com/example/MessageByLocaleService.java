package com.example;

public interface MessageByLocaleService {

  public String getMessage(String id);
  public String getMessage(String id, String parameter1);
  public String getMessage(String id, String parameter1, String parameter2);
  public String getMessage(String id, String[] args);
}
