package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.MessageByLocaleService;
import com.example.db.Assignee;
import com.example.db.DatabaseService;

public class UserService {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;

  public Assignee findAssignee(String userName) {
    Assignee assignee = databaseService.getAssignee(userName);
    /*
    if (assignee == null) {

    }
     */


    return assignee;
  }



}
