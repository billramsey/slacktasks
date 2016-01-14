package com.example.db.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.db.Assignee;
import com.example.db.Project;
import com.example.db.Task;

public interface TaskRepository extends MongoRepository<Task, String>, TaskRepositoryCustom {
  public Task findByTaskId(String taskId);
  public List<Task> findByProjectAndAssignee(Project project, Assignee assignee);
}
