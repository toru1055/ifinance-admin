package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.MarketIndexMasterManager;
import jp.thotta.ifinance.common.dao.MasterDataManager;
import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.MarketIndexMaster;

import java.util.List;
import java.util.Scanner;

/**
 * Created by thotta on 2016/07/25.
 */
public class MarketIndexMasterHandler extends BaseEntityHandler {
    MarketIndexMasterManager miMasterManager;
    MasterDataManager<MarketIndexCollector> miCollectorManager;

    public MarketIndexMasterHandler() {
        miMasterManager = new MarketIndexMasterManager();
        miCollectorManager = new MasterDataManager<MarketIndexCollector>(MarketIndexCollector.class);
    }

    @Override
    protected void add() {
        MarketIndexMaster miMaster = new MarketIndexMaster();
        System.out.println("Add " + tableName + ".");
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String name = readString(scan);
        System.out.print("collector_id: ");
        Integer collector_id = readInt(scan);
        MarketIndexCollector miCollector
                = collector_id != null ?
                miCollectorManager.find(collector_id) : null;
        System.out.print("interval(sec): ");
        Integer interval = readInt(scan);
        System.out.print("active(true/false): ");
        Boolean activeFlag = readBoolean(scan);
        if (name != null) {
            miMaster.setName(name);
        }
        if (miCollector != null) {
            miMaster.setMarketIndexCollector(miCollector);
        }
        if (interval != null) {
            miMaster.setInterval(interval);
        }
        if (activeFlag != null) {
            miMaster.setActiveFlag(activeFlag);
        }
        if (miMasterManager.add(miMaster)) {
            System.out.println("Success.");
            return;
        }
        System.out.println("Failed.");
    }

    @Override
    protected void list() {
        List<MarketIndexMaster> miMasterList = miMasterManager.selectAll();
        System.out.println("List " + tableName + ".");
        System.out.println("id\tname\tcollector");
        for (MarketIndexMaster miMaster : miMasterList) {
            System.out.println(
                    miMaster.getId() + "\t" +
                            miMaster.getName() + "\t" +
                            miMaster.getMarketIndexCollector().getName()
            );
        }
    }

    @Override
    protected void show(Integer id) {
        System.out.println("Show " + tableName + ".");
        MarketIndexMaster miMaster = miMasterManager.find(id);
        if (miMaster != null) {
            System.out.println(miMaster);
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void update(Integer id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Update " + tableName + ".");
        MarketIndexMaster miMaster = miMasterManager.find(id);
        if (miMaster == null) {
            System.out.println("id = " + id + ": does not exist");
            return;
        }
        System.out.print("name(" + miMaster.getName() + "): ");
        String name = readString(scan);
        System.out.print("collector_id(" + miMaster.getMarketIndexCollector().getId() + "): ");
        Integer collector_id = readInt(scan);
        MarketIndexCollector miCollector
                = collector_id != null ?
                miCollectorManager.find(collector_id) : null;
        System.out.print("interval(" + miMaster.getInterval() + "): ");
        Integer interval = readInt(scan);
        System.out.print("active(" + miMaster.getActiveFlag() + "): ");
        Boolean activeFlag = readBoolean(scan);
        if (name != null) {
            miMaster.setName(name);
        }
        if (miCollector != null) {
            miMaster.setMarketIndexCollector(miCollector);
        }
        if (interval != null) {
            miMaster.setInterval(interval);
        }
        if (activeFlag != null) {
            miMaster.setActiveFlag(activeFlag);
        }
        if (miMasterManager.update(miMaster)) {
            System.out.println("Success.");
        } else {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void remove(Integer id) {
        System.out.println("Remove " + tableName + ".");
        try {
            miMasterManager.remove(id);
            System.out.println("Success.");
        } catch (Exception e) {
            System.out.println("Failed.");
        }
    }

    @Override
    protected void _export(String filePath) {
        System.out.println("Export " + tableName + " is not supported.");
    }

    @Override
    protected void _import(String filePath) {
        System.out.println("Import " + tableName + " is not supported.");
    }
}
