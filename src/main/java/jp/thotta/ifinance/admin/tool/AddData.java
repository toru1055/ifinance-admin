package jp.thotta.ifinance.admin.tool;

import java.util.Scanner;
import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.common.entity.*;

public class AddData {
  IndustryManager industryManager;
  ScraperManager scraperManager;
  SubscriptionManager subscriptionManager;
  String tableName;

  public AddData(String tableName) {
    this.tableName = tableName;
  }

  public void exec() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Add " + tableName + ".");
    if("subscription".equals(tableName)) {
      subscriptionManager = new SubscriptionManager();
    } else if("industry".equals(tableName)) {
      industryManager = new IndustryManager();
    } else if("scraper".equals(tableName)) {
      System.out.print("name: ");
      String name = scan.next();
      System.out.println("Input Name: " + name);
      scraperManager = new ScraperManager();
      Scraper scraper = new Scraper(name);
      if(scraperManager.add(scraper)) {
        System.out.println("Success.");
      } else {
        System.out.println("Failed: " + name + " has already existed.");
      }
    }
    CommonEntityManager.closeFactory();
  }

}
