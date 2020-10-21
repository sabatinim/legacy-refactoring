package com.lastminute.training.tasklist.domain.task;

public class AddTaskRequest
{
  public final String projectName;
  public final String taskDescription;

  public AddTaskRequest(String projectName, String taskDescription)
  {
    this.projectName = projectName;
    this.taskDescription = taskDescription;
  }
}
