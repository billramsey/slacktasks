package com.example.db;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Task {
  @Id
  private String taskId;

  @DBRef(lazy = true)
  private Project project;

  @DBRef(lazy = true)
  private Assignee assignee;
  private String title;
  private String description;
  private DateTime creationDate;

  public Task() {
  }

  public Task(String taskId, String title, String description) {
    this.taskId = taskId;
    this.description = description;
    this.title = title;
    this.creationDate = new DateTime();
  }

  public Task(String taskId, String title, String description, Project project, Assignee assignee) {
    this.taskId = taskId;
    this.project = project;
    this.assignee = assignee;
    this.description = description;
    this.title = title;
    this.creationDate = new DateTime();
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Assignee getAssignee() {
    return assignee;
  }

  public void setAssignee(Assignee assignee) {
    this.assignee = assignee;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public DateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(DateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public String getTaskId() {
    return taskId;
  }
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }
}