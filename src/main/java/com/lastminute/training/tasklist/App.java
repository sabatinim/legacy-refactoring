package com.lastminute.training.tasklist;

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
