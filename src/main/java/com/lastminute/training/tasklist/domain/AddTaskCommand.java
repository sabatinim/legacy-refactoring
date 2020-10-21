package com.lastminute.training.tasklist.domain;

import com.lastminute.training.tasklist.infrastructure.InMemoryStorage;

public class AddTaskCommand
{
  private final InMemoryStorage storage;
  private final Display display;

  public AddTaskCommand(
    InMemoryStorage storage,
    Display display)
  {
    this.storage = storage;
    this.display = display;
  }

  public void execute(AddTaskRequest addTaskRequest)
  {
    storage
      .saveTaskTo(addTaskRequest)
      .fold(
        e -> {
          display.println(e.getMessage());
          return InMemoryStorage.Unit.instance();
        }, u -> u);
  }

}
