package com.lastminute.training.tasklist.infrastructure;

import com.lastminute.training.tasklist.domain.CommandInput;

import java.io.BufferedReader;
import java.io.IOException;

public class StdInCommandInput implements CommandInput
{
  private final BufferedReader in;

  public StdInCommandInput(BufferedReader in)
  {
    this.in = in;
  }

  @Override
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
