package com.example.db.mongodb;

import com.example.db.Assignee;
import com.example.db.Project;

public interface TaskRepositoryCustom {
  public void addTask(String assigmentId, String title, String description, Project p, Assignee a);
  public void addTask(String taskId, String title, String description, Project p);
  public void assignTo(String userName);
  public void reAssignTo(String taskId, String userName);
  public void setCompleted(String taskId);
}
