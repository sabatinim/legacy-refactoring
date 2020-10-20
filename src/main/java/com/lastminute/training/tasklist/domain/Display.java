package com.lastminute.training.tasklist.domain;

public interface Display
{
  void printf(String format, String value);

  void printf(String format, int value);

  void printf(String template, char checked, long taskId, String description);

  void println(String value);

  void print(String value);

  void println();

  void flush();
}
