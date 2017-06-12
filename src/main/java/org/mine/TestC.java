package org.mine;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by madhan.reddy on 3/7/2017.
 */
public class TestC {
  final static Logger logger = Logger.getLogger(TestC.class);
  public static String testC() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("abc.txt"));
    return "testC";
  }
}
