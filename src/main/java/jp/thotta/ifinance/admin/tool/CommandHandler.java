package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.CommonEntityManager;
import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.MarketIndexCollector;
import jp.thotta.ifinance.common.entity.Scraper;

import java.util.Scanner;

public class CommandHandler {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CommonEntityManager.getFactory();
        do {
            System.out.print("ifinance> ");
            String line = scan.nextLine();
            line = line.replaceAll(" +", " ")
                    .replaceAll("^ ", "")
                    .replaceAll(" $", "");
            String[] commands = line.split(" ");
            if (commands.length >= 2) {
                if ("industry".equals(commands[0])) {
                    new MasterDataHandler<Industry>(Industry.class).exec(commands);
                } else if ("scraper".equals(commands[0])) {
                    new MasterDataHandler<Scraper>(Scraper.class).exec(commands);
                } else if ("subscription".equals(commands[0])) {
                    new SubscriptionHandler().exec(commands);
                } else if ("market_index_collector".equals(commands[0])) {
                    new MasterDataHandler<MarketIndexCollector>(MarketIndexCollector.class).exec(commands);
                }
            }
        } while (scan.hasNextLine());
        CommonEntityManager.closeFactory();
    }
}
