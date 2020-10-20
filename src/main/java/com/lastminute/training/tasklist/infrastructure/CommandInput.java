package com.lastminute.training.tasklist.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandInput
{
  private final BufferedReader in;

  public CommandInput(BufferedReader in)
  {
    this.in = in;
  }

  public String get()
  {
    try
    {
      return in.readLine();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}
