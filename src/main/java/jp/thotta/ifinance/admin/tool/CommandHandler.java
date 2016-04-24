package jp.thotta.ifinance.admin.tool;

import java.util.Scanner;
import jp.thotta.ifinance.common.dao.CommonEntityManager;

public class CommandHandler {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    CommonEntityManager.getFactory();
    if(args.length >= 2) {
      if("industry".equals(args[0])) {
        new IndustryHandler().exec(args);
      }
      return;
    }
    do {
      System.out.print("ifinance> ");
      String line = scan.nextLine();
      line = line.replaceAll(" +", " ")
        .replaceAll("^ ", "")
        .replaceAll(" $", "");
      String[] commands = line.split(" ");
      if(commands.length >= 2) {
        if("industry".equals(commands[0])) {
          new IndustryHandler().exec(commands);
        }
      }
    } while(scan.hasNextLine());
    CommonEntityManager.closeFactory();
  }
}
