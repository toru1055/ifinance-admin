package jp.thotta.ifinance.admin.tool;

import java.util.Scanner;

public abstract class BaseEntityHandler {
    String tableName;

    public void exec(String[] args) {
        if (args.length >= 2) {
            this.tableName = args[0];
            String method = args[1];
            if ("add".equals(method)) {
                this.add();
            } else if ("list".equals(method)) {
                this.list();
            }
            if (args.length >= 3) {
                String option = args[2];
                if ("show".equals(method)) {
                    this.show(Integer.parseInt(option));
                } else if ("update".equals(method)) {
                    this.update(Integer.parseInt(option));
                } else if ("remove".equals(method)) {
                    this.remove(Integer.parseInt(option));
                } else if ("export".equals(method)) {
                    this._export(option);
                } else if ("import".equals(method)) {
                    this._import(option);
                } else {
                    System.err.println("wrong statement.");
                }
            }
        }
    }

    protected Integer readInt(Scanner scan) {
        String line = scan.nextLine();
        if (line == null) {
            return null;
        }
        Integer i = null;
        if (line.length() > 0) {
            try {
                i = Integer.parseInt(line);
            } catch (Exception e) {
                i = null;
            }
        }
        return i;
    }

    protected String readString(Scanner scan) {
        String line = scan.nextLine();
        if (line == null) {
            return null;
        }
        if (line.length() > 0) {
            return line;
        } else {
            return null;
        }
    }

    protected Boolean readBoolean(Scanner scan) {
        String line = scan.nextLine();
        if (line == null) {
            return null;
        }
        Boolean b = null;
        if (line.length() > 0) {
            if ("true".equals(line)) {
                b = true;
            } else if ("false".equals(line)) {
                b = false;
            }
        }
        return b;
    }

    abstract protected void add();

    abstract protected void list();

    abstract protected void show(Integer id);

    abstract protected void update(Integer id);

    abstract protected void remove(Integer id);

    abstract protected void _export(String filePath);

    abstract protected void _import(String filePath);
}
