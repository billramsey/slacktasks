package com.example.db.mongodb;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.MessageByLocaleService;
import com.example.db.Assignee;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.db.Task;
import com.example.outgoing.SlackService;


@Service
public class MongoDatabaseService implements DatabaseService {

  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  MongoTemplate mongoTemplate;



  @Autowired
  SlackService slackService;

  //Assignee related Database Services go here.
  @Override
  public Assignee getAssignee(String userName) {
    return assigneeRepository.findByUserName(userName);
  }

  @Override
  public Assignee createAssignee(String userId, String userName) {
    Assignee assignee = new Assignee(userId, userName);
    return assigneeRepository.save(assignee);
  }

  @Override
  public Assignee createAssignee(Assignee assignee) {
    return assigneeRepository.insert(assignee);
  }
  //Project related Database Services go here.

  @Override
  public Project getProject(String channelId) {
    return projectRepository.getProjectByChannelId(channelId);
  }

  @Override
  public Project createProject(String channelId, String channelName) {
    return projectRepository.save(new Project(channelId, channelName));
  }

  //Task related Database Services go here.
  @Override
  public Task findByTaskId(String taskId) {
    return taskRepository.findByTaskId(taskId);
  }

  @Override
  public Task createTask(String taskId, String title, String description,
      Project project, Assignee assignee) {
    Task task = new Task(taskId, title, description, project, assignee);
    task = taskRepository.insert(task);

    if (project != null) {
      project.addTask(task);
      projectRepository.save(project);
    }
    if (assignee != null) {
      assignee.addTask(task);
      assigneeRepository.save(assignee);
    }

    return task;
  }

  @Override
  public Task createTask(String taskId, String title, String description) {
    return createTask(taskId, title, description, null, null);
  }

  @Override
  public void addProjectToTask(Task task, Project project) {

    mongoTemplate.findAndModify(
        new Query(Criteria.where("taskId").is(task.getTaskId())),
        new Update().set("project", project),
        new FindAndModifyOptions().returnNew(true),
        Task.class);

    mongoTemplate.findAndModify(
        new Query(Criteria.where("channelId").is(project.getChannelId())),
        new Update().addToSet("tasks", task),
        new FindAndModifyOptions().returnNew(true),
        Project.class);


  }

  @Override
  public void addAssigneeToTask(Task task, Assignee assignee) {

    mongoTemplate.findAndModify(
        new Query(Criteria.where("taskId").is(task.getTaskId())),
        new Update().set("assignee", assignee),
        new FindAndModifyOptions().returnNew(true),
        Task.class);

    assignee.addTask(task);
    assigneeRepository.save(assignee);

  }


  @Override
  public void assignUserToProject(Assignee assignee, Project project) {
    assigneeRepository.addProjectToAssignee(assignee, project);
    projectRepository.addAssigneeToProject(assignee, project);
  }

  @Override
  public boolean doesAssigneeHaveProject(Assignee assignee, Project project) {

    List<Project> projects = assignee.getProjects();

    if (projects.size() < 1) {
      return false;
    }

    for (Project p : projects) {
      if (p.getChannelId().equals(project.getChannelId())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean doesProjectHaveAssignee(Assignee assignee, Project project) {

    Set<Assignee> assignees = project.getAssignees();

    if (assignees.size() < 1) {
      return false;
    }

    for (Assignee a : assignees) {
      if (a.getId().equals(assignee.getId())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean doesProjectHaveTask(Project project, Task task) {

    List<Task> tasks = project.getTasks();
    if (tasks.size() < 1) {
      return false;
    }

    for (Task a : tasks) {
      if (a.getTaskId().equals(task.getTaskId())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean doesAssigneeHaveTask(Assignee assignee, Task task) {
    List<Task> tasks = assignee.getTasks();

    if (tasks.size() < 1) {
      return false;
    }

    for (Task a : tasks) {
      if (a.getTaskId().equals(task.getTaskId())) {
        return true;
      }
    }

    return false;
  }
  @Override
  public List<Task> getUnassignedTasks(Project project) {
    return taskRepository.findByProjectAndAssignee(project, null);
  }
}
