package jp.thotta.ifinance.admin.tool;

import java.util.List;
import java.util.Scanner;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.common.entity.*;

public class IndustryHandler extends BaseEntityHandler {
  IndustryManager industryManager;

  public IndustryHandler() {
    industryManager = new IndustryManager();
  }

  @Override
  protected void add() {
    System.out.println("Add " + tableName + ".");
    Scanner scan = new Scanner(System.in);
    System.out.print("name: ");
    String name = scan.next();
    System.out.println("Input Name: " + name);
    Industry industry = new Industry(name);
    if(industryManager.add(industry)) {
        System.out.println("Success.");
    } else {
        System.out.println("Failed: " + name + " has already existed.");
    }
  }

  @Override
  protected void list() {
    List<Industry> industries = industryManager.selectAll();
    System.out.println("List " + tableName + ".");
    System.out.println("id\tname");
    for(Industry industry : industries) {
      System.out.println(industry.getId() + "\t" +
          industry.getName());
    }
  }

  @Override
  protected void show(Integer id) {
  }

  @Override
  protected void update(Integer id) {
  }

  @Override
  protected void _export(String filePath) {
  }

  @Override
  protected void _import(String filePath) {
  }
}
