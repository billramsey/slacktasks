package com.example.db;

import java.util.List;

public interface DatabaseService {
  //Assignees
  public Assignee getAssignee(String userName);
  public Assignee createAssignee(String userId, String userName);
  public Assignee createAssignee(Assignee assignee);
  //Projects
  public Project getProject(String channelId);
  public Project createProject(String channelId, String channelName);
  //Tasks
  public Task findByTaskId(String taskId);
  public Task createTask(String taskId, String title, String description, Project project, Assignee assignee);
  public Task createTask(String taskId, String title, String description);
  //Cross
  public boolean doesAssigneeHaveProject(Assignee assignee, Project project);
  public boolean doesProjectHaveAssignee(Assignee assignee, Project project); 
  public void assignUserToProject(Assignee assignee, Project project);
  public void addProjectToTask(Task task, Project project);
  public void addAssigneeToTask(Task task, Assignee assignee);
  public boolean doesProjectHaveTask(Project project, Task task);
  public boolean doesAssigneeHaveTask(Assignee assignee, Task task);
  public List<Task> getUnassignedTasks(Project project);
}

