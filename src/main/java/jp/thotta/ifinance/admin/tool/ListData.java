package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.*;
import jp.thotta.ifinance.common.entity.*;

public class ListData {
  String tableName;

  public ListData(String tableName) {
    this.tableName = tableName;
  }

  public void exec() {
    if("subscription".equals(tableName)) {
    } else if("industry".equals(tableName)) {
    } else if("scraper".equals(tableName)) {
    }
  }
}
