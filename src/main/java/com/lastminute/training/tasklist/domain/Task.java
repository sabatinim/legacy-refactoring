package com.lastminute.training.tasklist.domain;

import java.util.Objects;

public final class Task {
    private final long id;
    private final String description;
    private boolean done;

    public Task(long id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
          done == task.done &&
          Objects.equals(description, task.description);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, description, done);
    }

    @Override
    public String toString()
    {
        return "Task{" +
          "id=" + id +
          ", description='" + description + '\'' +
          ", done=" + done +
          '}';
    }
}
