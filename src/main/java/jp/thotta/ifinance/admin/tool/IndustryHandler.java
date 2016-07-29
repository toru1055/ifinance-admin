package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.IndustryManager;
import jp.thotta.ifinance.common.entity.Industry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

@Deprecated
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
        if (industryManager.add(industry)) {
            System.out.println("Success.");
        } else {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void list() {
        List<Industry> industries = industryManager.selectAll();
        System.out.println("List " + tableName + ".");
        System.out.println("id\tname");
        for (Industry industry : industries) {
            System.out.println(industry.getId() + "\t" +
                    industry.getName());
        }
    }

    @Override
    protected void show(Integer id) {
        System.out.println("Show " + tableName + ".");
        Industry industry = industryManager.find(id);
        if (industry != null) {
            System.out.println("id: " + industry.getId());
            System.out.println("name: " + industry.getName());
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void update(Integer id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Update " + tableName + ".");
        Industry industry = industryManager.find(id);
        if (industry != null) {
            System.out.print("name(" + industry.getName() + "): ");
            String name = scan.next();
            if (name != null && name.length() > 0) {
                industry.setName(name);
                if (industryManager.update(industry)) {
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
            industryManager.remove(id);
            System.out.println("Success.");
        } catch (Exception e) {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void _export(String filePath) {
        System.out.println("Export " + tableName + ".");
        try {
            List<Industry> industries = industryManager.selectAll();
            ObjectOutputStream oos
                    = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(industries);
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
            List<Industry> industries = (List<Industry>) ios.readObject();
            if (industryManager._import(industries)) {
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
