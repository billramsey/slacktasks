package com.example.db.mongodb;

import com.example.db.Assignee;
import com.example.db.Project;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
  @Autowired
  private ProjectRepository projectRepository;
  
  @Override
  public void addAssigneeToProject(Assignee assignee, Project project) {
    project.addAssignee(assignee);
    projectRepository.save(project);
  }

}
