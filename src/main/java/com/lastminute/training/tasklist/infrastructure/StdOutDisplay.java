package com.lastminute.training.tasklist.infrastructure;

import com.lastminute.training.tasklist.domain.Display;

import java.io.PrintWriter;

public class StdOutDisplay implements Display
{
  private final PrintWriter out;

  public StdOutDisplay(PrintWriter out)
  {
    this.out = out;
  }

  @Override
  public void printf(String format, String value)
  {
    this.out.printf(format, value);
  }

  @Override
  public void printf(String format, int value)
  {
    this.out.printf(format, value);
  }

  @Override
  public void printf(String template, char checked, long taskId, String description)
  {
    out.printf(template, checked, taskId, description);
  }

  @Override
  public void println(String value)
  {
    out.println(value);
  }

  @Override
  public void print(String value)
  {
    out.print(value);
  }

  @Override
  public void println()
  {
    out.println();
  }

  @Override
  public void flush()
  {
    out.flush();
  }
}
