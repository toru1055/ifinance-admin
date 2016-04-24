package jp.thotta.ifinance.admin.tool;

public abstract class BaseEntityHandler {
  String tableName;

  public void exec(String[] args) {
    if(args.length >= 2) {
      this.tableName = args[0];
      String method = args[1];
      if("add".equals(method)) {
        this.add();
      } else if("list".equals(method)) {
        this.list();
      }
      if(args.length >= 3) {
        String option = args[2];
        if("show".equals(method)) {
          this.show(Integer.parseInt(option));
        } else if("update".equals(method)) {
          this.update(Integer.parseInt(option));
        } else if("remove".equals(method)) {
          this.remove(Integer.parseInt(option));
        } else if("export".equals(method)) {
          this._export(option);
        } else if("import".equals(method)) {
          this._import(option);
        } else {
          System.err.println("wrong statement.");
        }
      }
    }
  }

  abstract protected void add();
  abstract protected void list();
  abstract protected void show(Integer id);
  abstract protected void update(Integer id);
  abstract protected void remove(Integer id);
  abstract protected void _export(String filePath);
  abstract protected void _import(String filePath);
}
