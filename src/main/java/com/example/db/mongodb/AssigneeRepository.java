package com.example.db.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.db.Assignee;



public interface AssigneeRepository extends 
MongoRepository<Assignee, String>, AssigneeRepositoryCustom {

  public Assignee findByUserName(String userName);
  public Assignee findById(String userId);
}
