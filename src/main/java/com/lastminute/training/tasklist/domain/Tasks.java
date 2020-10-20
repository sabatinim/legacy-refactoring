package com.lastminute.training.tasklist.domain;

import java.util.List;

public class Tasks
{
  private final List<Task> tasks;

  public Tasks(List<Task> tasks)
  {
    this.tasks = tasks;
  }

  public void addTask(Task task)
  {
    tasks.add(task);
  }
}
