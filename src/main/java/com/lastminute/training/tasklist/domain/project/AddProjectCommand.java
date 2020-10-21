package com.lastminute.training.tasklist.domain.project;

import com.lastminute.training.tasklist.infrastructure.InMemoryProjectStorage;

public class AddProjectCommand
{
  private final InMemoryProjectStorage inMemoryProjectStorage;
  private final String projectName;

  public AddProjectCommand(
    InMemoryProjectStorage inMemoryProjectStorage, String projectName)
  {
    this.inMemoryProjectStorage = inMemoryProjectStorage;
    this.projectName = projectName;
  }

  public void execute()
  {
    inMemoryProjectStorage.create(projectName);
  }
}
