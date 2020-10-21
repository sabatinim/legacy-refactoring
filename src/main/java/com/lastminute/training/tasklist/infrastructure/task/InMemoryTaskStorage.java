package com.lastminute.training.tasklist.infrastructure.task;

import io.vavr.control.Either;

import com.lastminute.training.tasklist.domain.task.AddTaskRequest;
import com.lastminute.training.tasklist.domain.task.Id;
import com.lastminute.training.tasklist.domain.task.Task;

import java.util.List;
import java.util.Map;

public class InMemoryTaskStorage
{
  private final Map<String, List<Task>> tasks;
  private final Id idGenerator;

  public InMemoryTaskStorage(Map<String, List<Task>> tasks, Id idGenerator)
  {
    this.tasks = tasks;
    this.idGenerator = idGenerator;
  }



  public static class Unit{
    private Unit(){}

    public static Unit instance(){
      return new Unit();
    }
  }

  class ProjectNotFound extends RuntimeException{
    public ProjectNotFound(String message)
    {
      super(message);
    }
  }

  public Either<Exception,Unit> saveTaskTo(String projectName,String taskDescription)
  {
    List<Task> tasks = this.tasks.get(projectName);
    if (tasks == null)
    {
      return Either.left(new ProjectNotFound(String.format("Could not find a project with the name \"%s\".\n", projectName)));
    }

    tasks.add(new Task(idGenerator.generate(),taskDescription,false));
    return Either.right(Unit.instance());
  }

  public Either<Exception,Unit> saveTaskTo(AddTaskRequest request)
  {
    return saveTaskTo(request.projectName,request.taskDescription);
  }
}
