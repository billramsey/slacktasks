package com.example.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.LinkedList;
import java.util.List;

public class Assignee {
  @Id
  private String id;
  
  private String userName;
  
  @DBRef(lazy = true)
  private List<Project> projects = new LinkedList<Project>();
  
  @DBRef(lazy = true)
  private List<Task> tasks = new LinkedList<Task>();
  
  
  public Assignee() {
  }
  
  public Assignee(String id, String userName) {
    this.id = id;
    this.userName = userName;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public List<Project> getProjects() {
    return projects;
  }
  
  public void setProjects(List<Project> projects) {
    this.projects = projects;
  }
  
  public void addProject(Project p) {
    this.projects.add(p);
  }
  
  public List<Task> getTasks() {
    return tasks;
  }
  
  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }
  
  public void addTask(Task task) {
    this.tasks.add(task);
  }
}
