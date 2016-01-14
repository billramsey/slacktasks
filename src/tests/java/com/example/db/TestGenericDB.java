package com.example.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.MessageByLocaleService;
import com.example.TestSlackApplication;
import com.example.db.mongodb.AssigneeRepository;
import com.example.db.mongodb.ProjectRepository;
import com.example.db.mongodb.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;






@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestSlackApplication.class)
@EnableAutoConfiguration
@ComponentScan
public class TestGenericDB {
  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;

  @Before
  public void setup() {
    assigneeRepository.deleteAll();
    projectRepository.deleteAll();
    taskRepository.deleteAll();
  }

  @Test
  public void testCreateUser() {
    databaseService.createAssignee("U0C28L66P", "billramsey");

    Assignee user = databaseService.getAssignee("billramsey");
    assertNotNull(user);
  }
  
  @Test
  public void testCreateProject() {
    databaseService.createProject("channelId", "channelName");

    Project project = databaseService.getProject("channelId");

    assertNotNull(project);
  }

  @Test
  public void testAssignUserToProject() {
    Project project = databaseService.createProject("channelId", "channelName");
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");

    assertFalse(databaseService.doesAssigneeHaveProject(assignee, project));

    databaseService.assignUserToProject(assignee, project);

    assertTrue(databaseService.doesAssigneeHaveProject(assignee, project));
  }

  public boolean findChannelIdInProjects(List<Project> projects, String channelId) {
    boolean foundProject = false;
    for (Project p : projects) {
      if (p.getChannelId().equals(channelId)) {
        foundProject = true;
      }
    }
    return foundProject;
  }
  
  public boolean findAssigneeInAssigneeSet(Set<Assignee> assignees, String userId) {
    boolean foundAssignee = false;
    for (Assignee a : assignees) {
      if (a.getId().equals(userId)) {
        foundAssignee = true;
      }
    }
    return foundAssignee;
  }

  @Test
  public void testUserGetsAssignedProject() {
    Project project = databaseService.createProject("channelId", "channelName");
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");

    databaseService.assignUserToProject(assignee, project);

    assignee = databaseService.getAssignee("billramsey");
    List<Project> projects = assignee.getProjects();
    assertTrue(findChannelIdInProjects(projects, "channelId"));

    project = databaseService.getProject("channelId");
    Set<Assignee> assignees = project.getAssignees();
    assertTrue(findAssigneeInAssigneeSet(assignees, assignee.getId()));
  }

  @Test
  public void testIsUserInProject() {
    Project project = databaseService.createProject("channelId", "channelName");
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");

    assertFalse(databaseService.doesAssigneeHaveProject(assignee, project));

    databaseService.assignUserToProject(assignee, project);

    assignee = databaseService.getAssignee("billramsey");    
    project = databaseService.getProject("channelId");


    assertTrue(databaseService.doesAssigneeHaveProject(assignee, project));
  }


  @Test
  public void testCreateTask() {
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");
    Project project = databaseService.createProject("channelId", "channelName");
    databaseService.assignUserToProject(assignee, project);

    assertNull(databaseService.findByTaskId("taskId"));

    databaseService.createTask("taskId", "title", 
        "description", project, assignee);

    Task task = databaseService.findByTaskId("taskId");
    assertEquals(task.getTaskId(), "taskId");

    project = databaseService.getProject("channelId");
    assignee = databaseService.getAssignee("billramsey");

    assertTrue(databaseService.doesProjectHaveTask(project, task));
    assertTrue(databaseService.doesAssigneeHaveTask(assignee, task));
  }

  @Test
  public void testTasksProject() {
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");
    Project project = databaseService.createProject("channelId", "channelName");
    databaseService.assignUserToProject(assignee, project);

    Task task = 
        databaseService.createTask("taskId", "title", "description");


    project = databaseService.getProject("channelId");

    assertFalse(databaseService.doesProjectHaveTask(project, task));

    databaseService.addProjectToTask(task, project);

    task = databaseService.findByTaskId("taskId");
    project = databaseService.getProject("channelId");
    assertEquals(task.getProject().getChannelId(), "channelId");

    assertTrue(databaseService.doesProjectHaveTask(project, task));


  }

  @Test
  public void testTasksAssignee() {
    Assignee assignee = databaseService.createAssignee("U0C28L66P", "billramsey");
    Project project = databaseService.createProject("channelId", "channelName");
    databaseService.assignUserToProject(assignee, project);

    Task task = 
        databaseService.createTask("taskId", "title", "description");

    assertFalse(databaseService.doesAssigneeHaveTask(assignee, task));

    databaseService.addAssigneeToTask(task, assignee);

    task = databaseService.findByTaskId("taskId");

    assertTrue(databaseService.doesAssigneeHaveTask(assignee, task));
  }


  @Test
  public void testNullCreationFields() {
    //TODO
  }
  
  @Test
  public void testBadTasksProject() {
    //TODO
  }
  
  @Test
  public void testAssignUserToBadProject() {
    //TODO
  }

  @Test
  public void testAssignNonExistentUser() {
    //TODO
  }

}
