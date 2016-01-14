package com.example;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.db.DatabaseService;
import com.example.db.mongodb.MongoDatabaseService;
import com.example.outgoing.SlackService;
import com.example.outgoing.SlackServiceImpl;

import java.util.Locale;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SlackApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SlackApplication.class, args);
  }
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SlackApplication.class);
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.US);
    return slr;
  }
  
  @Bean
  public ReloadableResourceBundleMessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = 
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:locale/messages");
    messageSource.setCacheSeconds(3600); //refresh cache once per hour
    return messageSource;
  }

  @Bean
  public DatabaseService databaseService() {
    return new MongoDatabaseService();
  }

  @Bean
  public SlackService slackService() {
    return new SlackServiceImpl();
  }

}