package com.example.db.mongodb;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.db.Assignee;
import com.example.db.Project;

public class TaskRepositoryImpl implements TaskRepositoryCustom {

  @Autowired
  private TaskRepository taskRepository; 

  @Override
  public void addTask(String taskId, String title, String description, Project p, Assignee a) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addTask(String taskId, String title, String description, Project p) {
    // TODO Auto-generated method stub

  }

  @Override
  public void assignTo(String userName) {

  }

  @Override
  public void reAssignTo(String taskId, String userName) {

  }

  @Override
  public void setCompleted(String taskId) {

  }
}
