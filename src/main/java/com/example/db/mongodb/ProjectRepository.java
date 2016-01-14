package com.example.db.mongodb;


import com.example.db.Assignee;
import com.example.db.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends 
    MongoRepository<Project, String>, ProjectRepositoryCustom {
  Project getProjectByChannelId(String channelId);
  public long countByChannelId(String channelId);
  List<Project> getProjectsByAssigneesContaining(Assignee a);
  //List<Assignee> findAssignees();
  //void addAssignee(Assignee a);
}
