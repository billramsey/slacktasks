package com.example.db.mongodb;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.db.Assignee;
import com.example.db.Project;
import com.example.db.Task;


public class AssigneeRepositoryImpl implements AssigneeRepositoryCustom {
  @Autowired
  private AssigneeRepository assigneeRepository;

  @Override
  public List<Project> getProjectList(String userId) {
    LinkedList<Project> projects = new LinkedList<Project>();
    return projects;
  }

  @Override
  public void addProjectToAssignee(Assignee assignee, Project project) {
    assignee.addProject(project);
    assigneeRepository.save(assignee);
  }

  @Override
  public void addTaskToAssignee(Assignee assignee, Task task) {
    assignee.addTask(task);
  }
}
