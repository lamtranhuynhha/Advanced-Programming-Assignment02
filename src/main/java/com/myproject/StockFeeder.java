package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;
    
    //  Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // Implement Singleton logic
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;   
    }

    public void addStock(Stock stock) {
        if (stock != null) {

            for (int i = 0; i < stockList.size(); i++) {
                Stock existingStock = stockList.get(i);
                if (existingStock.getCode().equals(stock.getCode())) {
                    stockList.set(i, new Stock(stock.getCode(), stock.getName()));
                    viewers.putIfAbsent(stock.getCode(), new ArrayList<>());
                    return;
                }
            }
    
            stockList.add(stock);
            viewers.putIfAbsent(stock.getCode(), new ArrayList<>());
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        if (viewers.containsKey(code)) {
            List<StockViewer> stockViewers = viewers.get(code);
    
            for (StockViewer viewer : stockViewers) {
                if (viewer.getClass().equals(stockViewer.getClass())) {
                    Logger.errorRegister(code);
                    return;
                }
            }
            stockViewers.add(stockViewer);
        } 
        else {
            Logger.errorRegister(code);
        }
    }  

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // Implement unregister logic, including error logging
        if (viewers.containsKey(code)) {
            List<StockViewer> stockViewers = viewers.get(code);
            if (!stockViewers.remove(stockViewer)) {
                Logger.errorUnregister(code);
            }
        } else {
            Logger.errorUnregister(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        //  Implement notifying registered viewers about price updates
        if (stockPrice != null) {
            String code = stockPrice.getCode();
            if (viewers.containsKey(code)) {
                for (StockViewer viewer : viewers.get(code)) {
                    viewer.onUpdate(stockPrice);
                }
            } else {
                System.err.println("No viewers registered for stock code " + code + ".");
            }
        }
    }
}
