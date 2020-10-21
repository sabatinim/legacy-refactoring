package com.lastminute.training.tasklist.infrastructure;

import com.lastminute.training.tasklist.domain.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryProjectStorage
{
  private final Map<String, List<Task>> tasks;

  public InMemoryProjectStorage(Map<String, List<Task>> tasks)
  {
    this.tasks = tasks;
  }

  public void create(String projectName)
  {
    tasks.put(projectName, new ArrayList<>());
  }
}
