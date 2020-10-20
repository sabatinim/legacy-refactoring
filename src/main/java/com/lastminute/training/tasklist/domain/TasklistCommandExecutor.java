package com.lastminute.training.tasklist.domain;

import com.lastminute.training.tasklist.infrastructure.InMemoryStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TasklistCommandExecutor
{
  private static final String QUIT = "quit";

  private final CommandInput commandInput;
  private final Display display;
  private final Map<String, List<Task>> tasks;

  private Id idGenerator = new Id(); //

  public TasklistCommandExecutor(
    CommandInput commandInput,
    Display display,
    Map<String, List<Task>> tasks)
  {
    this.commandInput = commandInput;
    this.display = display;
    this.tasks = tasks;
  }

  public boolean execute()
  {
    display.print("> ");
    display.flush();

    String command = commandInput.get();

    if (command.equals(QUIT))
    {
      return true;
    }
    String[] commandRest = command.split(" ", 2);
    String command1 = commandRest[0];
    switch (command1)
    {
      case "show":
        show();
        break;
      case "add":
        add(commandRest[1]);
        break;
      case "check":
        check(commandRest[1]);
        break;
      case "uncheck":
        uncheck(commandRest[1]);
        break;
      case "help":
        help();
        break;
      default:
        error(command1);
        break;
    }
    return false;
  }

  private void addProject(String name)
  {
    tasks.put(name, new ArrayList<>());
  }

  private void setDone(String idString, boolean done)
  {
    int id = Integer.parseInt(idString);
    for (Map.Entry<String, List<Task>> project : tasks.entrySet())
    {
      for (Task task : project.getValue())
      {
        if (task.getId() == id)
        {
          task.setDone(done);
          return;
        }
      }
    }
    display.printf("Could not find a task with an ID of %d.", id);
    display.println();
  }

  private void show()
  {
    for (Map.Entry<String, List<Task>> project : tasks.entrySet())
    {
      display.println(project.getKey());
      for (Task task : project.getValue())
      {
        char checked = (task.isDone() ? 'x' : ' ');
        display.printf("    [%c] %d: %s%n", checked, task.getId(), task.getDescription());
      }
      display.println();
    }
  }

  private void add(String commandLine)
  {
    String[] subcommandRest = commandLine.split(" ", 2);
    String subcommand = subcommandRest[0];

    if (subcommand.equals("project"))
    {
      addProject(subcommandRest[1]);
    }
    else if (subcommand.equals("task"))
    {
      String[] projectTask = subcommandRest[1].split(" ", 2);
      new AddTaskCommand(
        projectTask[0],
        projectTask[1],
        new InMemoryStorage(tasks,idGenerator),
        display,
        idGenerator)
        .execute();
    }
  }

  private void check(String idString)
  {
    setDone(idString, true);
  }

  private void uncheck(String idString)
  {
    setDone(idString, false);
  }

  private void help()
  {
    display.println("Commands:");
    display.println("  show");
    display.println("  add project <project name>");
    display.println("  add task <project name> <task description>");
    display.println("  check <task ID>");
    display.println("  uncheck <task ID>");
    display.println();
  }

  private void error(String command)
  {
    display.printf("I don't know what the command \"%s\" is.", command);
    display.println();
  }

  public class AddTaskCommand
  {
    private String project;
    private String description;
    private final InMemoryStorage storage;
    private final Display display;
    private final Id idGenerator;

    public AddTaskCommand(String project, String description, InMemoryStorage storage,
      Display display, Id idGenerator)
    {
      this.project = project;
      this.description = description;
      this.storage = storage;
      this.display = display;
      this.idGenerator = idGenerator;
    }

    public void execute()
    {

      storage
        .saveTaskTo(project, description)
        .mapLeft(
          e -> {
          display.printf("Could not find a project with the name \"%s\".\n", project);
          return InMemoryStorage.Unit.instance();
        });


    }
  }

}
