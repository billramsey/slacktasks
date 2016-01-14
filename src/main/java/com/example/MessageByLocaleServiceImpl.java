package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService {

  @Autowired
  private MessageSource messageSource;

  @Override
  public String getMessage(String id) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(id,null,locale);
  }

  @Override
  public String getMessage(String id, String parameter1) {
    Locale locale = LocaleContextHolder.getLocale();
    String[] args = new String[] {parameter1};

    return messageSource.getMessage(id, args, locale);   
  }

  @Override
  public String getMessage(String id, String parameter1, String parameter2) {
    Locale locale = LocaleContextHolder.getLocale();
    String[] args = new String[] {parameter1, parameter2};

    return messageSource.getMessage(id, args, locale);   
  }

  @Override
  public String getMessage(String id, String[] args) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(id, args, locale);   
  }
}
