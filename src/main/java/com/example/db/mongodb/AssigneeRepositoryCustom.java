package com.example.db.mongodb;

import com.example.db.Assignee;
import com.example.db.Project;
import com.example.db.Task;

import java.util.List;

public interface AssigneeRepositoryCustom {
  public List<Project> getProjectList(String userName);
  public void addProjectToAssignee(Assignee assignee, Project project);
  public void addTaskToAssignee(Assignee assignee, Task task);
}
