package org.mine;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by madhan.reddy on 3/7/2017.
 */
public class TestB {
  final static Logger logger = Logger.getLogger(TestB.class);
  public static String testB() throws IOException {
    return TestC.testC();
  }
}
