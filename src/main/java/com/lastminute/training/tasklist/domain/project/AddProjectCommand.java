package com.lastminute.training.tasklist.domain.project;

import com.lastminute.training.tasklist.domain.Command;
import com.lastminute.training.tasklist.infrastructure.InMemoryProjectStorage;

public class AddProjectCommand implements Command
{
  private final InMemoryProjectStorage inMemoryProjectStorage;
  private final String projectName;

  public AddProjectCommand(
    InMemoryProjectStorage inMemoryProjectStorage, String projectName)
  {
    this.inMemoryProjectStorage = inMemoryProjectStorage;
    this.projectName = projectName;
  }

  @Override
  public void execute()
  {
    inMemoryProjectStorage.create(projectName);
  }
}
