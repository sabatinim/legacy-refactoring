package com.lastminute.training.tasklist.domain.task;

import com.lastminute.training.tasklist.domain.Command;
import com.lastminute.training.tasklist.domain.Display;
import com.lastminute.training.tasklist.infrastructure.task.InMemoryTaskStorage;

public class AddTaskCommand implements Command
{
  private final InMemoryTaskStorage storage;
  private final Display display;
  private final AddTaskRequest addTaskRequest;

  public AddTaskCommand(
    InMemoryTaskStorage storage,
    Display display, AddTaskRequest addTaskRequest)
  {
    this.storage = storage;
    this.display = display;
    this.addTaskRequest = addTaskRequest;
  }
  
  @Override
  public void execute()
  {
    storage
      .saveTaskTo(addTaskRequest)
      .fold(
        e -> {
          display.println(e.getMessage());
          return InMemoryTaskStorage.Unit.instance();
        }, u -> u);
  }

}
