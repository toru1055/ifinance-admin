package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.entity.MasterData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Created by thotta on 2016/07/24.
 */
public class MasterDataHandler<T extends MasterData> extends BaseEntityHandler {
    MasterDataManager<T> manager;
    Class<T> clazz;

    public MasterDataHandler(Class<T> clazz) {
        this.clazz = clazz;
        this.manager = new MasterDataManager<T>(clazz);
    }

    @Override
    protected void add() {
        System.out.println("Add " + tableName + ".");
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String name = scan.next();
        System.out.println("Input Name: " + name);
        try {
            T t = clazz.newInstance();
            t.setName(name);
            if (manager.add(t)) {
                System.out.println("Success.");
            } else {
                System.out.println("Failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    @Override
    protected void list() {
        List<T> dataList = manager.selectAll();
        System.out.println("List " + tableName + ".");
        System.out.println("id\tname");
        for (T t : dataList) {
            System.out.println(t.getId() + "\t" + t.getName());
        }
    }

    @Override
    protected void show(Integer id) {
        System.out.println("Show " + tableName + ".");
        T t = manager.find(id);
        if (t != null) {
            System.out.println("id: " + t.getId());
            System.out.println("name: " + t.getName());
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void update(Integer id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Update " + tableName + ".");
        T t = manager.find(id);
        if (t != null) {
            System.out.print("name(" + t.getName() + "): ");
            String name = scan.next();
            if (name != null && name.length() > 0) {
                t.setName(name);
                if (manager.update(t)) {
                    System.out.println("Success.");
                } else {
                    System.out.println("Failed.");
                }
            }
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void remove(Integer id) {
        System.out.println("Remove " + tableName + ".");
        try {
            manager.remove(id);
            System.out.println("Success.");
        } catch (Exception e) {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void _export(String filePath) {
        System.out.println("Export " + tableName + ".");
        try {
            List<T> dataList = manager.selectAll();
            ObjectOutputStream oos
                    = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(dataList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void _import(String filePath) {
        System.out.println("Import " + tableName + ".");
        try {
            ObjectInputStream ios
                    = new ObjectInputStream(new FileInputStream(filePath));
            List<T> dataList = (List<T>) ios.readObject();
            if (manager._import(dataList)) {
                System.out.println("Success.");
            } else {
                System.out.println("Failed.");
            }
            ios.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
