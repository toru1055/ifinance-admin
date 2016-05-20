package jp.thotta.ifinance.admin.tool;

import jp.thotta.ifinance.common.dao.IndustryManager;
import jp.thotta.ifinance.common.dao.ScraperManager;
import jp.thotta.ifinance.common.dao.SubscriptionManager;
import jp.thotta.ifinance.common.entity.Industry;
import jp.thotta.ifinance.common.entity.Scraper;
import jp.thotta.ifinance.common.entity.Subscription;

import java.util.List;
import java.util.Scanner;

public class SubscriptionHandler extends BaseEntityHandler {
    SubscriptionManager subscriptionManager;
    IndustryManager industryManager;
    ScraperManager scraperManager;

    public SubscriptionHandler() {
        subscriptionManager = new SubscriptionManager();
        industryManager = new IndustryManager();
        scraperManager = new ScraperManager();
    }

    @Override
    protected void add() {
        Subscription subscription = new Subscription();
        System.out.println("Add " + tableName + ".");
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String name = readString(scan);
        System.out.print("url: ");
        String url = readString(scan);
        System.out.print("private(true/false): ");
        Boolean privateFlag = readBoolean(scan);
        System.out.print("interval(sec): ");
        Integer interval = readInt(scan);
        System.out.print("scraper_id: ");
        Integer scraperId = readInt(scan);
        System.out.print("industry_id: ");
        Integer industryId = readInt(scan);
        Scraper scraper = scraperId != null ? scraperManager.find(scraperId) : null;
        Industry industry = industryId != null ? industryManager.find(industryId) : null;
        if (name != null) {
            subscription.setName(name);
        }
        if (url != null) {
            subscription.setUrl(url);
        }
        if (privateFlag != null) {
            subscription.setPrivateFlag(privateFlag);
        }
        if (interval != null) {
            subscription.setInterval(interval);
        }
        if (scraper != null) {
            subscription.setScraper(scraper);
        }
        if (industry != null) {
            subscription.setFixedIndustry(industry);
        }
        if (subscriptionManager.add(subscription)) {
            System.out.println("Success.");
            return;
        }
        System.out.println("Failed.");
    }

    @Override
    protected void list() {
        List<Subscription> subscriptions = subscriptionManager.selectAll();
        System.out.println("List " + tableName + ".");
        System.out.println("id\tname\turl");
        for (Subscription subscription : subscriptions) {
            System.out.println(subscription.getId() + "\t" +
                    subscription.getName() + "\t" +
                    subscription.getUrl());
        }
    }

    @Override
    protected void show(Integer id) {
        System.out.println("Show " + tableName + ".");
        Subscription subscription = subscriptionManager.find(id);
        if (subscription != null) {
            System.out.println("id: " + subscription.getId());
            System.out.println("name: " + subscription.getName());
            System.out.println("url: " + subscription.getUrl());
            System.out.println("private: " + subscription.getPrivateFlag());
            System.out.println("lastReadDate: " + subscription.getLastReadDate());
            System.out.println("interval(sec): " + subscription.getInterval());
            System.out.println("scraper: " + subscription.getScraper().getName());
            System.out.print("industry: ");
            if (subscription.getFixedIndustry() != null) {
                System.out.print(subscription.getFixedIndustry().getName());
            }
            System.out.println();
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void update(Integer id) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Update " + tableName + ".");
        Subscription subscription = subscriptionManager.find(id);
        if (subscription != null) {
            System.out.print("name(" + subscription.getName() + "): ");
            String name = readString(scan);
            System.out.print("url(" + subscription.getUrl() + "): ");
            String url = readString(scan);
            System.out.print("interval(" + subscription.getInterval() + ")[sec]: ");
            Integer interval = readInt(scan);
            System.out.print("private(" + subscription.getPrivateFlag() + ")[true/false]: ");
            Boolean privateFlag = readBoolean(scan);
            System.out.print("scraper_id(" + subscription.getScraper().getId() + "): ");
            Integer scraperId = readInt(scan);
            System.out.print("industry_id(" +
                    (subscription.getFixedIndustry() != null ? subscription.getFixedIndustry().getId() : "null") +
                    "): ");
            Integer industryId = readInt(scan);
            Scraper scraper = scraperId != null ? scraperManager.find(scraperId) : null;
            Industry industry = industryId != null ? industryManager.find(industryId) : null;
            if (name != null) {
                subscription.setName(name);
            }
            if (url != null) {
                subscription.setUrl(url);
            }
            if (privateFlag != null) {
                subscription.setPrivateFlag(privateFlag);
            }
            if (interval != null) {
                subscription.setInterval(interval);
            }
            if (scraper != null) {
                subscription.setScraper(scraper);
            }
            if (industry != null) {
                subscription.setFixedIndustry(industry);
            }
            if (subscriptionManager.update(subscription)) {
                System.out.println("Success.");
            } else {
                System.out.println("Failed.");
            }
        } else {
            System.out.println("id = " + id + ": does not exist");
        }
    }

    @Override
    protected void remove(Integer id) {
        System.out.println("Remove " + tableName + ".");
        try {
            subscriptionManager.remove(id);
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
