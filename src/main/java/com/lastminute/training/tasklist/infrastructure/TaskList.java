package com.lastminute.training.tasklist.infrastructure;

import com.lastminute.training.tasklist.domain.CommandInput;
import com.lastminute.training.tasklist.domain.Display;
import com.lastminute.training.tasklist.domain.Task;
import com.lastminute.training.tasklist.domain.TasklistCommandExecutor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable
{
  private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

  private final CommandInput commandInput;
  private final Display display;

//  private long lastId = 0; //

  public TaskList(CommandInput commandInput, Display display)
  {
    this.commandInput = commandInput;
    this.display = display;
  }

  public void run()
  {
    TasklistCommandExecutor executor = new TasklistCommandExecutor(commandInput, display, tasks);
    while (true)
    {
      if (executor.execute()) break;
    }
  }

}