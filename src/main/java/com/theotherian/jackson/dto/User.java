package com.theotherian.jackson.dto;

import java.util.List;

public class User {
  
  private String name;
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  private List<Thing> things;
  
  public List<Thing> getThings() { return things; }
  public void setThings(List<Thing> things) { this.things = things; }

}
