package object;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
  public static void main(String[] args) {
//    Account account = new Account("lgc","lgc1","1");
//    PrintWriter out = openWriter("account.txt");
//    writeMovie(account, out);
//    out.close();
  }

  public static PrintWriter openWriter(String name) {
    try {
      File file = new File(name);
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)), true);
      return out;
    } catch (IOException e) {
      System.out.println("I/O Error");
      System.exit(0);
    }
    return null;
  }

  public static void writeMovie(Account m, PrintWriter out) {
    String line = m.id;
    line += "\t" + m.password;
    line += "\t" + m.isAutoSave;
    out.println(line);
  }


}
