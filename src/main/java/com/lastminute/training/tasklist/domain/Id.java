package com.lastminute.training.tasklist.domain;

public class Id
{
  private int startFrom;

  public Id()
  {
    this.startFrom = 0;
  }

  public long generate()
  {
    return ++startFrom;
  }
}
