package org.mine;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by madhan.reddy on 3/7/2017.
 */
public class TestA {
  final static Logger logger = Logger.getLogger(TestA.class);
  public static String testA() throws IOException {
    return TestB.testB();
  }
}
