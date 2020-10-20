package com.lastminute.training.tasklist;

import org.junit.Test;

import com.lastminute.training.tasklist.domain.CommandInput;
import com.lastminute.training.tasklist.domain.Display;
import com.lastminute.training.tasklist.domain.Task;
import com.lastminute.training.tasklist.domain.TasklistCommandExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class TaskListExecutorTest
{
  Display NOT_USED = display(new String[1]);

  @Test
  public void addProject()
  {
    CommandInput commandInput = () -> "add project A_PROJECT_NAME";

    Map<String, List<Task>> storage = new HashMap<>();

    new TasklistCommandExecutor(commandInput,NOT_USED,storage).execute();

    assertThat(storage.get("A_PROJECT_NAME"),is(Collections.EMPTY_LIST));
  }

  @Test
  public void addTask()
  {
    CommandInput commandInput = () -> "add task A_PROJECT_NAME A_TASK_NAME";

    Map<String, List<Task>> storage = new HashMap<String, List<Task>>(){{
      put("A_PROJECT_NAME",new ArrayList<>());
    }};

    new TasklistCommandExecutor(commandInput,NOT_USED,storage).execute();

    assertThat(storage.get("A_PROJECT_NAME"),containsInAnyOrder(new Task(1,"A_TASK_NAME",false)));
  }

  @Test
  public void projectNotFound()
  {
    CommandInput commandInput = () -> "add task A_PROJECT_NAME A_TASK_NAME";

    Map<String, List<Task>> storage = new HashMap<String, List<Task>>(){{
      put("ANOTHER_PROJECT_NAME",new ArrayList<>());
    }};

    final String[] expectedOutput = {""};
    Display display = display(expectedOutput);
    new TasklistCommandExecutor(commandInput, display, storage).execute();

    assertThat(expectedOutput[0],is("Could not find a project with the name \"A_PROJECT_NAME\".\n"));
  }

  private Display display(String[] expectedOutput)
  {
    return new Display()
    {
      @Override
      public void printf(String format, String value)
      {
        expectedOutput[0] = format.replaceAll("%s",value);
      }

      @Override
      public void printf(String format, int value)
      {

      }

      @Override
      public void printf(String template, char checked, long taskId, String description)
      {

      }

      @Override
      public void println(String value)
      {

      }

      @Override
      public void print(String value)
      {

      }

      @Override
      public void println()
      {

      }

      @Override
      public void flush()
      {

      }
    };
  }
}