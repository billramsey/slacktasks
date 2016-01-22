package com.example.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.db.mongodb.AssigneeRepository;
import com.example.db.mongodb.ProjectRepository;
import com.example.db.mongodb.TaskRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class TestMongodb {

  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;


  private String gUserId = "123456789";
  private String gUserName = "TestMan";
  private String gTaskId = "task888";
  private String gTaskDescription = "Here is my description";
  private String gTaskTitle = "TaskTitle";
  private String gProjectId = "23456789";
  private String gProjectName = "testproject888";

  @Before
  public void setup() {
    assigneeRepository.deleteAll();
    projectRepository.deleteAll();
    taskRepository.deleteAll();
  }

  public Assignee createUser(String userId, String userName) {
    Assignee assignee = new Assignee(userId, userName);
    return assigneeRepository.save(assignee);
  }

  public Project createProjectWithAssignee(String projectId, String projectName, Assignee e) {
    Project project = new Project(projectId, projectName);
    Set<Assignee> assignees = project.getAssignees();
    assignees.add(e);
    project.setAssignees(assignees);
    return projectRepository.save(project);
  }

  public Task createTask(String taskId, String title, String description, Project p, Assignee e) {
    Task task = new Task(taskId, title, description, p, e);
    return taskRepository.save(task);
  }


  @Test
  public void testRegisterUser() {
    createUser(gUserId, gUserName);
    Assignee assignee = assigneeRepository.findByUserName("TestMan");
    assertTrue(assignee != null);
  }

  @Test
  public void testCreateProject() {
    Assignee assignee = createUser(gUserId, gUserName);
    Project project = 
        createProjectWithAssignee(gProjectId, gProjectName, assignee);
    Set<Assignee> returnedAssignees = project.getAssignees();
    assertTrue(returnedAssignees.size() == 1);

    for (Assignee r : returnedAssignees) {
      assertEquals(r.getUserName(), gUserName);
    }

  }

  @Test
  public void testCreateTask() {
    Assignee assignee = createUser(gUserId, gUserName);
    Project project = createProjectWithAssignee(gProjectId, gProjectName, assignee);
    Task task = createTask(gTaskId, gTaskTitle, gTaskDescription, project, assignee);

    assertEquals(task.getAssignee().getUserName(), gUserName);
    assertEquals(task.getProject().getChannelName(), gProjectName);
  }

  /*

  testChangeTask()


   */
}
