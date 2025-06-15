package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) { 
        // Implement logic to check if price has changed and log it
        String stockCode = stockPrice.getCode();
        double newPrice = stockPrice.getAvgPrice();

        if (!lastPrices.containsKey(stockCode)) {
            lastPrices.put(stockCode, newPrice);
            return;
        }
        if (lastPrices.get(stockCode) != newPrice){
            Logger.logRealtime(stockCode, newPrice);
            lastPrices.put(stockCode, newPrice);
        }
    }
}
