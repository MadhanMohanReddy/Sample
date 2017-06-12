package org.mine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by madhan.reddy on 3/21/2017.
 */
public class TestList {
  public static void main(String[] args) {
    List list1 = new ArrayList();
    list1.add("m");
    list1.add("a");
    list1.add("d");
    list1.add("d");
    list1.add("y");
    for (Object obj: list1) {
      System.out.println(obj);
    }
    Set set1 = (Set) new HashSet();
    set1.add("m");
    set1.add("a");
    set1.add("d");
    set1.add("d");
    set1.add("y");
    for (Object obj: set1) {
      System.out.println(obj);
    }

  }
}
