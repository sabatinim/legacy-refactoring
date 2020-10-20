package com.lastminute.training.tasklist;

import com.lastminute.training.tasklist.infrastructure.CommandInput;
import com.lastminute.training.tasklist.infrastructure.Display;
import com.lastminute.training.tasklist.infrastructure.TaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class App
{
  public static void main(String[] args)
  {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    new TaskList(new CommandInput(in), new Display(out)).run();
  }
}
