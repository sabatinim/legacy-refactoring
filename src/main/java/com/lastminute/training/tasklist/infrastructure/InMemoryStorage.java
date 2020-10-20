package com.lastminute.training.tasklist.infrastructure;

import com.lastminute.training.tasklist.domain.Task;
import com.lastminute.training.tasklist.domain.Tasks;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryStorage
{
  private final Map<String, List<Task>> tasks;

  public InMemoryStorage(Map<String, List<Task>> tasks)
  {
    this.tasks = tasks;
  }

  public Optional<Tasks> getProject(String projectName)
  {
    List<Task> tasks = this.tasks.get(projectName);
    if (tasks == null)
    {
      return Optional.empty();
    }
    return Optional.of(new Tasks(tasks));
  }
}
