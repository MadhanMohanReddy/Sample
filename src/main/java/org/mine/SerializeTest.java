package org.mine;

import java.io.*;

/**
 * Created by madhan.reddy on 3/28/2017.
 */
public class SerializeTest {
  public static void main(String[] args) {
    Employee s1 =new Employee(211,"ravi");

    try {
      FileOutputStream fout=new FileOutputStream("abcd.txt");
      ObjectOutputStream out= new ObjectOutputStream(fout);
      out.writeObject(s1);
      out.flush();
      System.out.println("success");

      ObjectInputStream in=new ObjectInputStream(new FileInputStream("abcd.txt"));
      Employee s=(Employee)in.readObject();
      System.out.println(s.id+" "+s.name);

      in.close();


    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }


  }
}
