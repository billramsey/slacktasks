package com.example.db.mongodb;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.db.Assignee;
import com.example.db.Project;

public interface ProjectRepository extends 
MongoRepository<Project, String>, ProjectRepositoryCustom {
  Project getProjectByChannelId(String channelId);
  public long countByChannelId(String channelId);
  List<Project> getProjectsByAssigneesContaining(Assignee a);
  //List<Assignee> findAssignees();
  //void addAssignee(Assignee a);
}
