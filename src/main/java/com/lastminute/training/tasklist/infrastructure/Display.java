package com.lastminute.training.tasklist.infrastructure;

import java.io.PrintWriter;

public class Display
{
  private final PrintWriter out;

  public Display(PrintWriter out)
  {
    this.out = out;
  }

  public void printf(String format, String value)
  {
    this.out.printf(format, value);
  }

  public void printf(String format, int value)
  {
    this.out.printf(format, value);
  }

  public void printf(String template, char checked, long taskId, String description)
  {
    out.printf(template, checked, taskId, description);
  }

  public void println(String value)
  {
    out.println(value);
  }

  public void print(String value)
  {
    out.print(value);
  }

  public void println()
  {
    out.println();
  }

  public void flush()
  {
    out.flush();
  }
}
