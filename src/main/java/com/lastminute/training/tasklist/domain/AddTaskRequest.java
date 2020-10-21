package com.lastminute.training.tasklist.domain;

public class AddTaskRequest
{
  public final String projectName;
  public final String taskDescription;

  AddTaskRequest(String projectName, String taskDescription)
  {
    this.projectName = projectName;
    this.taskDescription = taskDescription;
  }
}
