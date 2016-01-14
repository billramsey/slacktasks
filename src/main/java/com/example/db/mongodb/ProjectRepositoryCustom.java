package com.example.db.mongodb;

import com.example.db.Assignee;
import com.example.db.Project;





public interface ProjectRepositoryCustom {
  public void addAssigneeToProject(Assignee assignee, Project project);
}
