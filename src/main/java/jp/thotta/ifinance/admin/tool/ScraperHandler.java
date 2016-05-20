package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.ScraperManager;
import jp.thotta.ifinance.common.entity.Scraper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;

public class ScraperHandler extends BaseEntityHandler {
    ScraperManager scraperManager;

    public ScraperHandler() {
        scraperManager = new ScraperManager();
    }

    @Override
    protected void add() {
        System.out.println("Add " + tableName + ".");
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String name = scan.next();
        System.out.println("Input Name: " + name);
        Scraper scraper = new Scraper(name);
        if (scraperManager.add(scraper)) {
            System.out.println("Success.");
        } else {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void list() {
        List<Scraper> scrapers = scraperManager.selectAll();
        System.out.println("List " + tableName + ".");
        System.out.println("id\tname");
        for (Scraper scraper : scrapers) {
            System.out.println(scraper.getId() + "\t" +
                    scraper.getName());
        }
    }

    @Override
    protected void show(Integer id) {
        System.out.println("Show " + tableName + ".");
        Scraper scraper = scraperManager.find(id);
        if (scraper != null) {
            System.out.println("id: " + scraper.getId());
            System.out.println("name: " + scraper.getName());
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void update(Integer id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Update " + tableName + ".");
        Scraper scraper = scraperManager.find(id);
        if (scraper != null) {
            System.out.print("name(" + scraper.getName() + "): ");
            String name = scan.next();
            if (name != null && name.length() > 0) {
                scraper.setName(name);
                if (scraperManager.update(scraper)) {
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
            scraperManager.remove(id);
            System.out.println("Success.");
        } catch (Exception e) {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void _export(String filePath) {
        System.out.println("Export " + tableName + ".");
        try {
            List<Scraper> scrapers = scraperManager.selectAll();
            ObjectOutputStream oos
                    = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(scrapers);
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
            List<Scraper> scrapers = (List<Scraper>) ios.readObject();
            if (scraperManager._import(scrapers)) {
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
