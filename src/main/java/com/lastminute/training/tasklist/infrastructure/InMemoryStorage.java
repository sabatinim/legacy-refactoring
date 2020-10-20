package com.lastminute.training.tasklist.infrastructure;

import io.vavr.control.Either;

import com.lastminute.training.tasklist.domain.Id;
import com.lastminute.training.tasklist.domain.Task;
import com.lastminute.training.tasklist.domain.Tasks;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryStorage
{
  private final Map<String, List<Task>> tasks;
  private final Id idGenerator;

  public InMemoryStorage(Map<String, List<Task>> tasks, Id idGenerator)
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

  public Either<Exception,Unit> saveTaskTo(String projectName,String taskDescription)
  {
    List<Task> tasks = this.tasks.get(projectName);
    if (tasks == null)
    {
      return Either.left(new RuntimeException());
    }

    tasks.add(new Task(idGenerator.generate(),taskDescription,false));
    return Either.right(Unit.instance());
  }
}
