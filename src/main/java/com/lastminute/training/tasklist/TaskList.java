package com.lastminute.training.tasklist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class TaskList implements Runnable
{
  private static final String QUIT = "quit";

  private final Map<String, List<Task>> tasks = new LinkedHashMap<>();

  private final CommandInput commandInput;
  private final Display display;

  private long lastId = 0; //

  public static void main(String[] args)
  {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    new TaskList(new Display(out), new CommandInput(in)).run();
  }

  public TaskList(Display display, CommandInput commandInput)
  {
    this.commandInput = commandInput;
    this.display = display;
  }

  public void run()
  {
    while (true)
    {
      display.print("> ");
      display.flush();

      String command = commandInput.get();

      if (command.equals(QUIT))
      {
        break;
      }
      execute(command);
    }
  }

  private void execute(String commandLine)
  {
    String[] commandRest = commandLine.split(" ", 2);
    String command = commandRest[0];
    switch (command)
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
        error(command);
        break;
    }
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
      addTask(projectTask[0], projectTask[1]);
    }
  }

  private void addProject(String name)
  {
    tasks.put(name, new ArrayList<>());
  }

  private void addTask(String project, String description)
  {
    List<Task> projectTasks = tasks.get(project);
    if (projectTasks == null)
    {
      display.printf("Could not find a project with the name \"%s\".", project);
      display.println();
      return;
    }
    projectTasks.add(new Task(nextId(), description, false));
  }

  private void check(String idString)
  {
    setDone(idString, true);
  }

  private void uncheck(String idString)
  {
    setDone(idString, false);
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

  private long nextId()
  {
    return ++lastId;
  }

}