package org.mine;

import java.io.Serializable;

/**
 * Created by madhan.reddy on 3/28/2017.
 */
public class Employee implements Serializable {
  int id;
  String name;
  static String company="SSS IT Pvt Ltd";//it won't be serialized
  public Employee(int id, String name) {
    this.id = id;
    this.name = name;
  }
}