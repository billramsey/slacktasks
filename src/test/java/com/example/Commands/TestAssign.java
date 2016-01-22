package com.example.Commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.MessageByLocaleService;
import com.example.TestSlackApplication;
import com.example.db.Assignee;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.db.Task;
import com.example.db.mongodb.AssigneeRepository;
import com.example.db.mongodb.ProjectRepository;
import com.example.db.mongodb.TaskRepository;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestSlackApplication.class)
@EnableAutoConfiguration
@ComponentScan
public class TestAssign {
  @Autowired
  CommandService commandService;

  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  DatabaseService databaseService;


  //Test projects, assignee, and task
  Project testProject;
  Assignee testAssignee;
  Task testTask;

  @Before
  public void setup() {
    assigneeRepository.deleteAll();
    projectRepository.deleteAll();
    taskRepository.deleteAll();


    testProject = new Project("test-project-id", "test-project-name");
    projectRepository.save(testProject);
    testAssignee = new Assignee("test-assignee-id", "test-assignee-name");
    assigneeRepository.save(testAssignee);
    testTask = new Task("test-task-id", "test-task-title", "test-task-description");
    taskRepository.save(testTask);
  }



  @Test
  public void testAssignCommandNoUserNotInChannel() {
    Arguments arg = new Arguments("assign test-task-id test-assignee-name");
    Command command = commandService.findCommand(arg.getCommand());
    assertNotNull(command);

    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("test-project-id");

    //Test without user assigned to channel
    SlackResponse response = command.execute(slackRequest, arg);
    assertEquals(messageByLocaleService.getMessage("user.not.assigned.to.channel", 
        testAssignee.getUserName(), testProject.getChannelId()),
        response.getText());

  }


  @Test
  public void testAssignUserCommandSuccess() {
    Arguments arg = new Arguments("assign test-task-id test-assignee-name");
    Command command = commandService.findCommand(arg.getCommand());
    assertNotNull(command);

    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("test-project-id");

    //Assign to channel assert success
    databaseService.assignUserToProject(testAssignee, testProject);

    SlackResponse response = command.execute(slackRequest, arg);
    assertEquals(messageByLocaleService.getMessage("task.assigned.user.success"),
        response.getText());

    //run it again, assure that already assigned
    response = command.execute(slackRequest, arg);

    assertEquals(messageByLocaleService.getMessage("task.already.assigned", 
        testAssignee.getUserName()), response.getText());
  }

  @Test
  public void testAssignUserCommandAlreadyAssigned() {
    Arguments arg = new Arguments("assign test-task-id test-assignee-name");
    Command command = commandService.findCommand(arg.getCommand());
    assertNotNull(command);

    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("test-project-id");

    //Assign to channel assert success
    databaseService.assignUserToProject(testAssignee, testProject);

    SlackResponse response = command.execute(slackRequest, arg);
    assertEquals(messageByLocaleService.getMessage("task.assigned.user.success"),
        response.getText());

    //run it again, assure that already assigned
    response = command.execute(slackRequest, arg);

    assertEquals(messageByLocaleService.getMessage("task.already.assigned", 
        testAssignee.getUserName()), response.getText());
  }

}
