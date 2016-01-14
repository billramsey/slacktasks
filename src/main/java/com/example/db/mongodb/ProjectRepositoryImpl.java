package com.example.db.mongodb;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.db.Assignee;
import com.example.db.Project;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public void addAssigneeToProject(Assignee assignee, Project project) {
    project.addAssignee(assignee);
    projectRepository.save(project);
  }

}
