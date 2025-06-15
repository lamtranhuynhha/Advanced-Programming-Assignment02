package com.myproject;
import java.util.ArrayList; //Added 
import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        //  Implement constructor
        this.hoseLib=hoseLib;
        this.stockCodes=stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // Fetch stock data and convert it to StockPrice list
        List<StockPrice> stockPrices = new ArrayList<>();
        
        List<HoseData> hosedataList=hoseLib.getPrices(stockCodes);
        for (HoseData hoseData: hosedataList){
            stockPrices.add(convertToStockPrice(hoseData));
        }
        return stockPrices;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // Convert HoseData to StockPrice
        return new StockPrice(hoseData.getStockCode(), hoseData.getPrice(), hoseData.getVolume(), hoseData.getTimestamp());
    }
}
