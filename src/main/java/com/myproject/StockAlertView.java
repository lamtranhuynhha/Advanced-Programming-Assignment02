package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        double currentPrice = stockPrice.getAvgPrice();
        String stockCode = stockPrice.getCode();
    
        if (currentPrice >= alertThresholdHigh) {
            if (lastAlertedPrices.get(stockCode) == null || !lastAlertedPrices.get(stockCode).equals(currentPrice)) {
                alertAbove(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            }
        } 

        else if (currentPrice <= alertThresholdLow) {
            if (lastAlertedPrices.get(stockCode) == null || !lastAlertedPrices.get(stockCode).equals(currentPrice)) {
                alertBelow(stockCode, currentPrice);
                lastAlertedPrices.put(stockCode, currentPrice);
            }
        }
    }

    private void alertAbove(String stockCode, double price) {
        //  Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        //  Call Logger to log the alert
        Logger.logAlert(stockCode, price);
    }
}
