package com.example.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Project {
  @Id
  private String channelId;

  private String channelName;
  private boolean deleted;

  @DBRef(lazy = true)
  private Set<Assignee> assignees = new HashSet<Assignee>();
  
  @DBRef(lazy = true)
  private List<Task> tasks = new LinkedList<Task>();
  
  public Project(String channelId, String channelName) {
    this.channelId = channelId;
    this.channelName = channelName;
  }


  public String getChannelId() {
    return channelId;
  }


  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }


  public String getChannelName() {
    return channelName;
  }


  public void setChannelName(String channelName) {
    this.channelName = channelName;
  }


  public boolean isDeleted() {
    return deleted;
  }


  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }


  public Set<Assignee> getAssignees() {
    return assignees;
  }


  public void setAssignees(Set<Assignee> assignees) {
    this.assignees = assignees;
  }
  
  public void addAssignee(Assignee assignee) {
    assignees.add(assignee);
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
